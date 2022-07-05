package com.trendyol.celik.gokhun.data.gamedetail.source.remote.model.response.detail

import com.google.gson.annotations.SerializedName
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.response.list.*

data class GameDetailResponse(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("slug")
    val slug: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("name_original")
    val nameOriginal: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("metacritic")
    val metaCritic: Int?,

    @SerializedName("metacritic_platforms")
    val metaCriticPlatforms: List<MetaCriticPlatforms>?,

    @SerializedName("released")
    val released: String?,

    @SerializedName("tba")
    val tba: Boolean?,

    @SerializedName("updated")
    val updated: String?,

    @SerializedName("background_image")
    val backgroundImage: String?,

    @SerializedName("background_image_additional")
    val backgroundImageAdditional: String?,

    @SerializedName("website")
    val website: String?,

    @SerializedName("rating")
    val rating: Double?,

    @SerializedName("rating_top")
    val ratingTop: Int?,

    @SerializedName("ratings")
    val ratings: List<Rating>?,

    @SerializedName("reactions")
    val reactions: Any?, /// required array but not handled !

    @SerializedName("added")
    val added: Int?,

    @SerializedName("added_by_status")
    val addedByStatus: AddedByStatus?,

    @SerializedName("playtime")
    val playtime: Int?,

    @SerializedName("screenshots_count")
    val screenshotsCount: Int?,

    @SerializedName("movies_count")
    val moviesCount: Int?,

    @SerializedName("creators_count")
    val creatorsCount: Int?,

    @SerializedName("achievements_count")
    val achievementsCount: Int?,

    @SerializedName("parent_achievements_count")
    val parentAchievementsCount: Int?,

    @SerializedName("reddit_url")
    val redditUrl: String?,

    @SerializedName("reddit_name")
    val redditName: String?,

    @SerializedName("reddit_description")
    val redditDescription: String?,

    @SerializedName("reddit_logo")
    val redditLogo: String?,

    @SerializedName("reddit_count")
    val redditCount: Int?,

    @SerializedName("twitch_count")
    val twitchCount: Int?,

    @SerializedName("youtube_count")
    val youtubeCount: Int?,

    @SerializedName("reviews_text_count")
    val reviewsTextCount: Int?,

    @SerializedName("ratings_count")
    val ratingsCount: Int?,

    @SerializedName("suggestions_count")
    val suggestionsCount: Int?,

    @SerializedName("alternative_names")
    val alternativeNames: List<String>?,

    @SerializedName("metacritic_url")
    val metaCriticUrl: String?,

    @SerializedName("parents_count")
    val parentsCount: Int?,

    @SerializedName("additions_count")
    val additionsCount: Int?,

    @SerializedName("game_series_count")
    val gameSeriesCount: Int?,

    @SerializedName("user_game")
    val userGame: Any?,

    @SerializedName("reviews_count")
    val reviewsCount: Int?,

    @SerializedName("saturated_color")
    val saturatedColor: String?,

    @SerializedName("dominant_color")
    val dominantColor: String?,

    @SerializedName("parent_platforms")
    val parentPlatforms: List<ParentPlatform>?,

    @SerializedName("platforms")
    val platforms: List<PlatformInDetails>?,

    @SerializedName("stores")
    val stores: List<StoreInDetails>?,

    @SerializedName("developers")
    val developers: List<Developer>?,

    @SerializedName("genres")
    val genres: List<Genre>?,

    @SerializedName("tags")
    val tags: List<Tag>?,

    @SerializedName("publishers")
    val publishers: List<Publisher>?,

    @SerializedName("esrb_rating")
    val esrbRating: EsrbRating?,

    @SerializedName("clip")
    val clip: Any?,

    @SerializedName("description_raw")
    val descriptionRaw: String?

    )
