package io.shortcut.android.xkcd.comicdetail.data.entity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import io.shortcut.android.xkcd.comicdetail.domain.model.ExplanationResponseModel

@Keep
data class ExplanationEntity(
    val parse: ParseEntity?
) {
    fun toModel(): ExplanationResponseModel {
        val explanationIdentifier = "==Explanation=="
        val transcriptIdentifier = "==Transcript=="

        // normalizing comic explanation from raw response
        val rawContent = parse?.wikitext?.explanation
        val explanationLength = explanationIdentifier.length
        val startExplanation = (rawContent?.indexOf(explanationIdentifier) ?: 0) + explanationLength
        val endExplanation = rawContent?.indexOf(transcriptIdentifier) ?: 0
        val explanation = rawContent?.substring(
            startExplanation,
            endExplanation
        )?.trim()

        val normalizedExplanation =
            explanation?.replace("{{w|", "")?.replace("}}", "")?.replace("{{", "")?.replace("|", "")
                ?.replace("[[", "")?.replace("]]", "")

        return ExplanationResponseModel(
            title = parse?.title ?: "",
            explanation = normalizedExplanation ?: ""
        )
    }
}

@Keep
data class ParseEntity(
    val title: String?,
    val wikitext: ComicExplanationEntity?
)

@Keep
data class ComicExplanationEntity(
    @SerializedName("*")
    val explanation: String?
)
