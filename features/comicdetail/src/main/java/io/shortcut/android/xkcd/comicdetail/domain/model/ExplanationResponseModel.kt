package io.shortcut.android.xkcd.comicdetail.domain.model

data class ExplanationResponseModel(
    val title: String,
    val explanation: String,
    var imageUrl: String = "",
    var descripton: String = ""
)
