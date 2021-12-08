package io.shortcut.android.xkcd.comics.explorer.domain.model

import java.util.*

data class ComicModel(

    val number: Int,

    // Text
    val title: String,
    val transcript: String,

    // Image
    val imageLink: String,
    var imageUri: String? = null, // Address for save image in mobile device
    val imageAlt: String,

    // Date
    val date: Calendar
)