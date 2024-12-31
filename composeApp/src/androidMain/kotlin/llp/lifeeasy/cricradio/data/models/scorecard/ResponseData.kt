package llp.lifeeasy.cricradio.data.models.scorecard


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ResponseData(
    @SerialName("message")
    val message: String,
    @SerialName("result")
    val result: Result
)