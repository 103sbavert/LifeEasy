package llp.lifeeasy.cricradio.data.models.venue


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Result(
    @SerialName("firstUmpire")
    val firstUmpire: String,
    @SerialName("format")
    val format: String,
    @SerialName("_id")
    val id: String,
    @SerialName("key")
    val key: String,
    @SerialName("matchReferee")
    val matchReferee: String,
    @SerialName("related_name")
    val relatedName: String,
    @SerialName("season")
    val season: Season,
    @SerialName("secoundUmpire")
    val secoundUmpire: String,
    @SerialName("start_date")
    val startDate: StartDate,
    @SerialName("status")
    val status: String,
    @SerialName("teams")
    val teams: Teams,
    @SerialName("thirdUmpire")
    val thirdUmpire: String,
    @SerialName("toss")
    val toss: Toss,
    @SerialName("venue")
    val venue: String,
    @SerialName("venueDetails")
    val venueDetails: VenueDetails,
    @SerialName("venueStats")
    val venueStats: VenueStats,
    @SerialName("weather")
    val weather: Weather
)