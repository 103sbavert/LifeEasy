package llp.lifeeasy.cricradio.data.models.venue


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class BattingSecond(
    @SerialName("averageScore")
    val averageScore: Int,
    @SerialName("highestScore")
    val highestScore: Int,
    @SerialName("lowestScore")
    val lowestScore: Int,
    @SerialName("paceWickets")
    val paceWickets: Int,
    @SerialName("spinWickets")
    val spinWickets: Int
)