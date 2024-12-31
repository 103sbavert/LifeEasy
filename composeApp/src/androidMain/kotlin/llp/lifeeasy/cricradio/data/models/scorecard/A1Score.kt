package llp.lifeeasy.cricradio.data.models.scorecard


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class A1Score(
    @SerialName("declare")
    val declare: Boolean,
    @SerialName("overs")
    val overs: String,
    @SerialName("runs")
    val runs: Int,
    @SerialName("wickets")
    val wickets: Int
)