package io.shortcut.android.xkcd.comicdetail.data.entity

import com.google.gson.annotations.SerializedName
import io.shortcut.android.xkcd.comicdetail.domain.model.ExplanationResponseModel

data class ExplanationEntity(
    val parse: Parse?
) {
    fun toModel(): ExplanationResponseModel {
        val rawContent = parse?.wikitext?.explanation
        val explanationLength = "==Explanation==".length
        val startExplanation = (rawContent?.indexOf("==Explanation==") ?: 0) + explanationLength
        val endExplanation = rawContent?.indexOf("==Transcript==") ?: 0
        val explanation = rawContent?.substring(
            startExplanation,
            endExplanation
        )?.trim()

        val normalizedExplanation =
            explanation?.replace("{{w|", "")?.
            replace("}}", "")?.
            replace("{{", "")?.
            replace("|", "")?.
            replace("[[", "")?.
            replace("]]", "")

        return ExplanationResponseModel(
            title = parse?.title ?: "",
            explanation = normalizedExplanation ?: ""
        )
    }
}

data class Parse(
    val title: String?,
    val wikitext: ComicExplanation?
)

data class ComicExplanation(
    @SerializedName("*")
    val explanation: String?
)