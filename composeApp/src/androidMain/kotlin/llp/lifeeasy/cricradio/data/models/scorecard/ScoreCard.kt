package llp.lifeeasy.cricradio.data.models.scorecard


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ScoreCard(
    @SerialName("requestParams")
    val requestParams: RequestParams,
    @SerialName("responseData")
    val responseData: ResponseData,
    @SerialName("statusCode")
    val statusCode: Int,
    @SerialName("time")
    val time: String
)