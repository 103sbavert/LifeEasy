package llp.lifeeasy.cricradio.data.models.venue


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class VenueInfo(
    @SerialName("location")
    val location: String,
    @SerialName("longName")
    val longName: String,
    @SerialName("name")
    val name: String,
    @SerialName("smallName")
    val smallName: String,
    @SerialName("town")
    val town: String
)