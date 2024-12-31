package llp.lifeeasy.cricradio.data.models.scorecard


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class LastCommentary(
    @SerialName("isDone")
    val isDone: Boolean,
    @SerialName("primaryText")
    val primaryText: String,
    @SerialName("secondaryText")
    val secondaryText: String?,
    @SerialName("tertiaryText")
    val tertiaryText: String?
)