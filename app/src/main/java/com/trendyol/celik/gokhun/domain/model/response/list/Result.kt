package com.trendyol.celik.gokhun.domain.model.response.list

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("slug")
    val slug: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("released")
    val released: String?,

    @SerializedName("tba")
    val tba: Boolean?,

    @SerializedName("background_image")
    val backgroundImage: String?,

    @SerializedName("rating")
    val rating: Double?,

    @SerializedName("rating_top")
    val rating_top: Int?,

    @SerializedName("ratings")
    val ratings: List<Rating>?,

    @SerializedName("ratings_count")
    val ratingsCount: Int?,

    @SerializedName("reviews_text_count")
    val reviewsTextCount: Int?,

    @SerializedName("added")
    val added: Int?,

    @SerializedName("added_by_status")
    val addedByStatus: AddedByStatus?,

    @SerializedName("metacritic")
    val metaCritic: Int?,

    @SerializedName("playtime")
    val playtime: Int?,

    @SerializedName("suggestions_count")
    val suggestionsCount: Int?,

    @SerializedName("updated")
    val updated: String?,

    @SerializedName("user_game")
    val userGame: Any?,

    @SerializedName("reviews_count")
    val reviewsCount: Int?,

    @SerializedName("saturated_color")
    val saturatedColor: String?,

    @SerializedName("dominant_color")
    val dominantColor: String?,

    @SerializedName("platforms")
    val platforms: List<Platform>?,

    @SerializedName("parent_platforms")
    val parentPlatforms: List<ParentPlatform>?,

    @SerializedName("genres")
    val genres: List<Genre>?,

    @SerializedName("stores")
    val stores: List<Store>?,

    @SerializedName("clip")
    val clip: Any?,

    @SerializedName("tags")
    val tags: List<Tag>?,

    @SerializedName("esrb_rating")
    val esrbRating: EsrbRating?,

    @SerializedName("short_screenshots")
    val shortScreenshots: List<ShortScreenshots>?

    )