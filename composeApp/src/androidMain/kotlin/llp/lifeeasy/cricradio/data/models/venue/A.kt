package llp.lifeeasy.cricradio.data.models.venue


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class A(
    @SerialName("logo")
    val logo: String,
    @SerialName("name")
    val name: String,
    @SerialName("shortName")
    val shortName: String
)