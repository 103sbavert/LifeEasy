package llp.lifeeasy.cricradio.data.models.venue


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class VenueDetails(
    @SerialName("capacity")
    val capacity: Int,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("cricinfoId")
    val cricinfoId: Int,
    @SerialName("description")
    val description: String,
    @SerialName("ends1")
    val ends1: String,
    @SerialName("ends2")
    val ends2: String,
    @SerialName("floodLights")
    val floodLights: Int,
    @SerialName("homeTo")
    val homeTo: String,
    @SerialName("_id")
    val id: String,
    @SerialName("isDeleted")
    val isDeleted: String,
    @SerialName("knownAs")
    val knownAs: String,
    @SerialName("opened")
    val opened: Boolean?,
    @SerialName("photo")
    val photo: String,
    @SerialName("status")
    val status: String,
    @SerialName("timezone")
    val timezone: String,
    @SerialName("updatedAt")
    val updatedAt: String,
    @SerialName("venue_info")
    val venueInfo: VenueInfo,
    @SerialName("venueLocation")
    val venueLocation: String,
    @SerialName("venueScraptitle")
    val venueScraptitle: String
)