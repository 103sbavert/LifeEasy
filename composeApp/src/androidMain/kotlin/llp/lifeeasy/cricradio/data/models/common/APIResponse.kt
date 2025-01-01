package llp.lifeeasy.cricradio.data.models.common


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class APIResponse<T: Result>(
    @SerialName("responseData")
    val responseData: ResponseData<T>,
)