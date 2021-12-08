package io.shortcut.android.xkcd.comics.explorer.data.api

import io.shortcut.android.xkcd.comics.explorer.data.entity.ComicEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ComicsApiService {

    @GET("info.0.json")
    fun getLastComics(): Call<ComicEntity>

    @GET("{number}/info.0.json")
    fun getComicByNumber(@Path("number") number: Int): Call<ComicEntity>

}