package llp.lifeeasy.cricradio.data.models.scorecard


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Now(
    @SerialName("req_run_rate")
    val reqRunRate: String,
    @SerialName("run_rate")
    val runRate: String,
    @SerialName("sessionLeft")
    val sessionLeft: String?
)