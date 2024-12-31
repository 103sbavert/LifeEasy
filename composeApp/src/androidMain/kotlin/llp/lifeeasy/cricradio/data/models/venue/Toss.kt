package llp.lifeeasy.cricradio.data.models.venue


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Toss(
    @SerialName("decision")
    val decision: String,
    @SerialName("str")
    val str: String,
    @SerialName("won")
    val won: String
)