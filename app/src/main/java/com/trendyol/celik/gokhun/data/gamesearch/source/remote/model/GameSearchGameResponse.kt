package com.trendyol.celik.gokhun.data.gamesearch.source.remote.model

import com.google.gson.annotations.SerializedName
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.*

data class GameSearchGameResponse(
    @SerializedName("slug")
    val slug: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("playtime")
    val playtime: Int?,

    @SerializedName("platforms")
    val platforms: List<PlatformGameSearch?>?,

    @SerializedName("stores")
    val stores: List<StoreGameSearch?>?,

    @SerializedName("released")
    val released: String?,

    @SerializedName("tba")
    val tba: Boolean?,

    @SerializedName("background_image")
    val backgroundImage: String?,

    @SerializedName("rating")
    val rating: Any?,

    @SerializedName("rating_top")
    val rating_top: Int?,

    @SerializedName("ratings")
    val ratings: List<Any?>?,

    @SerializedName("ratings_count")
    val ratingsCount: Int?,

    @SerializedName("reviews_text_count")
    val reviewsTextCount: Int?,

    @SerializedName("added")
    val added: Int?,

    @SerializedName("added_by_status")
    val addedByStatus: AddedByStatusGameSearch?,

    @SerializedName("metacritic")
    val metaCritic: Int?,

    @SerializedName("suggestions_count")
    val suggestionsCount: Int?,

    @SerializedName("updated")
    val updated: String?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("score")
    val score: Double?,

    @SerializedName("clip")
    val clip: Any?,

    @SerializedName("tags")
    val tags: List<Tag>?,

    @SerializedName("esrb_rating")
    val esrbRating: EsrbRating?,

    @SerializedName("user_game")
    val userGame: Any?,

    @SerializedName("reviews_count")
    val reviewsCount: Int?,

    @SerializedName("community_rating")
    val communityRating: Int?,

    @SerializedName("saturated_color")
    val saturatedColor: String?,

    @SerializedName("dominant_color")
    val dominantColor: String?,

    @SerializedName("short_screenshots")
    val shortScreenshots: List<ShortScreenshots?>?,

    @SerializedName("parent_platforms")
    val parentPlatforms: List<ParentPlatformDetail?>?,

    @SerializedName("genres")
    val genres: List<Genre?>?
)