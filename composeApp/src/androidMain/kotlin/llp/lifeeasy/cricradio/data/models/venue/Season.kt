package llp.lifeeasy.cricradio.data.models.venue


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Season(
    @SerialName("key")
    val key: String,
    @SerialName("name")
    val name: String
)