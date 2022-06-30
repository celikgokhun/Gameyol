/*
 * Copyright 2019 Evstafiev Konstantin
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.response.list

import com.google.gson.annotations.SerializedName

data class GameListingResponse(
    @SerializedName("count")
    val count: Int?,

    @SerializedName("next")
    val next: String?,

    @SerializedName("previous")
    val previous: String?,

    @SerializedName("results")
    val results: List<GameResultResponse>?,

    @SerializedName("seo_title")
    val seoTitle: String?,

    @SerializedName("seo_description")
    val seoDescription: String?,

    @SerializedName("seo_keywords")
    val seoKeywords: String?,

    @SerializedName("seo_h1")
    val seoH1: String?,

    @SerializedName("noindex")
    val noIndex: Boolean?,

    @SerializedName("nofollow")
    val noFollow: Boolean?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("filters")
    val filters: Filter?,

    @SerializedName("nofollow_collections")
    val noFollowCollections: List<String>?
    )

