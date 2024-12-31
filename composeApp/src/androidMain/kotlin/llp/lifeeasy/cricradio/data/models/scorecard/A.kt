package llp.lifeeasy.cricradio.data.models.scorecard


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class A(
    @SerialName("a_1_score")
    val a1Score: A1Score,
    @SerialName("a_2_score")
    val a2Score: A1Score,
    @SerialName("logo")
    val logo: String,
    @SerialName("name")
    val name: String,
    @SerialName("shortName")
    val shortName: String
)