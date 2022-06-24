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

package com.trendyol.celik.gokhun.model.response.list

import com.google.gson.annotations.SerializedName

data class RawgData(
    @SerializedName("count")
    val count: Int?,

    @SerializedName("next")
    val next: String?,

    @SerializedName("previous")
    var previous: String?,

    @SerializedName("results")
    var result: List<Result>?,

    @SerializedName("seo_title")
    var seoTitle: String?,

    @SerializedName("seo_description")
    var seoDescription: String?,

    @SerializedName("seo_keywords")
    var seoKeywords: String?,

    @SerializedName("seo_h1")
    var seoH1: String?,

    @SerializedName("noindex")
    var noIndex: Boolean?,

    @SerializedName("nofollow")
    var noFollow: Boolean?,

    @SerializedName("description")
    var description: String?,

    @SerializedName("filters")
    var filters: Filter?,

    @SerializedName("nofollow_collections")
    var noFollowCollections: List<String>?
    )

