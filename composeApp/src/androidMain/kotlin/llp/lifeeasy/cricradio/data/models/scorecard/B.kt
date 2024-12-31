package llp.lifeeasy.cricradio.data.models.scorecard


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class B(
    @SerialName("b_1_score")
    val b1Score: B1Score,
    @SerialName("b_2_score")
    val b2Score: B1Score,
    @SerialName("logo")
    val logo: String,
    @SerialName("name")
    val name: String,
    @SerialName("shortName")
    val shortName: String
)