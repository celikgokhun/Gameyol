package com.trendyol.celik.gokhun.base.extensions

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

object ResourceReactiveExtensions {

    fun <T : Any> Observable<T>.remote(startWithLoading: Boolean = true):
            Observable<Resource<T>> {
        return map<Resource<T>> { Resource.Success(it) }
            .onErrorReturn { Resource.Error(it) }
            .subscribeOn(Schedulers.io())
            .run { if (startWithLoading) { startWithItem(Resource.Loading()) } else this }
    }

    fun <T : Any> Single<T>.remote(startWithLoading: Boolean = true): Observable<Resource<T>> =
        toObservable().remote(startWithLoading)

    fun Completable.remote(startWithLoading: Boolean = true): Observable<Resource<Unit>> {
        return andThen(Observable.just(Unit)).remote(startWithLoading)
    }

    fun <T> Observable<Resource<T>>.subscribe(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit = {},
        onLoading: () -> Unit = {},
        onStatusChanged: (Status) -> Unit = {},
    ): Disposable =
        observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                onStatusChanged(Status.Content)
                onSuccess(it)
            }
            .doOnLoading {
                onStatusChanged(Status.Loading)
                onLoading()
            }
            .doOnResourceError { error ->
                onStatusChanged(Status.Error(error))
                onError(error)

            }
            .doOnError { error ->
                onStatusChanged(Status.Error(error))
                onError(error)
            }
            .subscribe({}, {})

    fun <T, R> Observable<Resource<T>>.flatMapOnData(mapper: (T) -> Observable<Resource<R>>): Observable<Resource<R>> {
        return this.flatMap { incomingResource ->
            when (incomingResource) {
                is Resource.Success -> mapper.invoke(incomingResource.data)
                is Resource.Error -> Observable.just(Resource.Error(incomingResource.exception))
                is Resource.Loading -> Observable.just(Resource.Loading())
            }
        }
    }

    fun <T, R> Observable<Resource<T>>.mapOnError(
        mapper: (T) -> R,
        onError: (Throwable) -> R,
    ): Observable<Resource<R>> {
        return this.map { incomingResource ->
            when (incomingResource) {
                is Resource.Success -> Resource.Success(mapper.invoke(incomingResource.data))
                is Resource.Error -> Resource.Success(onError.invoke(incomingResource.exception))
                is Resource.Loading -> Resource.Loading()
            }
        }
    }

    fun <T> Observable<Resource<T>>.mapOnError(
        onError: (Throwable) -> T,
    ): Observable<Resource<T>> {
        return this.map { incomingResource ->
            when (incomingResource) {
                is Resource.Success -> Resource.Success(incomingResource.data)
                is Resource.Error -> Resource.Success(onError.invoke(incomingResource.exception))
                is Resource.Loading -> Resource.Loading()
            }
        }
    }

    inline fun <T, reified R : Throwable> Observable<Resource<T>>.resolveResourceError(
        crossinline resolver: (R) -> Throwable,
    ): Observable<Resource<T>> = map { resource ->
        resource.resolveError(resolver)
    }

    /**
     * This function returns [Resource.Success.data] or throws [Resource.Error.exception]
     * by ignoring [Resource.Loading]s
     */

    suspend fun <T> ObservableSource<Resource<T>>.awaitData(): T = suspendCancellableCoroutine { cont ->
        subscribe(object : Observer<Resource<T>> {
            private lateinit var subscription: Disposable

            override fun onSubscribe(sub: Disposable) {
                subscription = sub
                cont.invokeOnCancellation { sub.dispose() }
            }

            override fun onNext(resource: Resource<T>) {
                when (resource) {
                    is Resource.Error -> {
                        cont.resumeWithException(resource.exception)
                        subscription.dispose()
                    }
                    is Resource.Success -> {
                        cont.resume(resource.data)
                        subscription.dispose()
                    }
                    is Resource.Loading -> {
                        // do nothing
                    }
                }
            }

            override fun onComplete() {
                if (cont.isActive) {
                    cont.resumeWithException(NoSuchElementException("No value received"))
                }
            }

            override fun onError(e: Throwable) {
                cont.resumeWithException(e)
            }
        })
    }

    fun <T> Observable<Resource<T>>.doAfterSuccess(onSuccess: (T) -> (Unit)): Observable<Resource<T>> {
        return this.doAfterNext {
            if (it is Resource.Success) {
                onSuccess(it.data)
            }
        }
    }
}
