package llp.lifeeasy.cricradio.data.models.common


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ResponseData<T : Result>(
    @SerialName("message")
    val message: String,
    @SerialName("result")
    val result: T
)