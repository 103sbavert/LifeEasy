package llp.lifeeasy.cricradio.data.models.venue


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class StartDate(
    @SerialName("iso")
    val iso: String,
    @SerialName("sky_check_ts")
    val skyCheckTs: Int,
    @SerialName("str")
    val str: String,
    @SerialName("timestamp")
    val timestamp: Int
)