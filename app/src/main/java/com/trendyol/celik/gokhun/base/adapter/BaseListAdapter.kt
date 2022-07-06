package com.trendyol.celik.gokhun.base.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<T, VH : RecyclerView.ViewHolder> constructor(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, VH>(diffCallback) {

    override fun getItemCount(): Int = currentList.size

    @JvmOverloads
    fun submitItem(position: Int = itemCount, item: T) {
        val newList = getMutableList()
        newList.add(position, item)
        submitList(newList)
    }

    fun removeItem(index: Int) {
        val newList = getMutableList()
        newList.removeAt(index)
        submitList(newList)
    }

    fun removeItem(item: T): Boolean {
        val currentList = getMutableList()
        return currentList.remove(item).also { submitList(currentList) }
    }

    fun clearItems() {
        submitList(mutableListOf())
    }

    fun getItems(): List<T> = currentList

    private fun getMutableList(): MutableList<T> = currentList.toMutableList()
}
