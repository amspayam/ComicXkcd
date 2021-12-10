package io.shortcut.android.xkcd.comics.explorer.data.entity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import io.shortcut.android.xkcd.comics.explorer.domain.model.ComicModel
import java.util.*

@Keep
data class ComicEntity(
    @SerializedName("num")
    val number: Int?,
    val link: String?,
    val news: String?,

    // Text
    @SerializedName("safe_title")
    val safeTitle: String?,
    val transcript: String?,
    val alt: String?,

    // Image
    @SerializedName("img")
    val imageLink: String?,

    // Date
    val year: Int?,
    val month: Int?,
    val day: Int?,
) {

    fun toModel() = ComicModel(
        number = this.number ?: 0,
        title = this.safeTitle ?: "",
        description = this.alt ?: "",
        imageLink = this.imageLink ?: "",
        date = run {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, this.year ?: 1900)
            calendar.set(Calendar.MONTH, this.month ?: 1)
            calendar.set(Calendar.DAY_OF_MONTH, this.day ?: 1)
            calendar
        }

    )
}