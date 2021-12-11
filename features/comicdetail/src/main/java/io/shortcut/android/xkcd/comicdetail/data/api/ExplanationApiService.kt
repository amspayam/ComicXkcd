package io.shortcut.android.xkcd.comicdetail.data.api

import io.shortcut.android.xkcd.comicdetail.data.entity.ExplanationEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ExplanationApiService {

    @GET("api.php")
    fun getComicExplanation(
        @Query("action") action: String = "parse",
        @Query("page") comicTitle: String,
        @Query("prop") prop: String = "wikitext",
        @Query("sectiontitle") sectionTitle: String = "Explanation",
        @Query("format") format: String = "json",
    ): Call<ExplanationEntity>

}