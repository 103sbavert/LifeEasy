package llp.lifeeasy.cricradio.data.models.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import llp.lifeeasy.cricradio.data.models.scorecard.LastCommentary
import llp.lifeeasy.cricradio.data.models.scorecard.Now
import llp.lifeeasy.cricradio.data.models.scorecard.SettingObj
import llp.lifeeasy.cricradio.data.models.venue.Season
import llp.lifeeasy.cricradio.data.models.venue.StartDate
import llp.lifeeasy.cricradio.data.models.venue.Toss
import llp.lifeeasy.cricradio.data.models.venue.VenueDetails
import llp.lifeeasy.cricradio.data.models.venue.VenueStats
import llp.lifeeasy.cricradio.data.models.venue.Weather

@Serializable
sealed interface Result {

    @Serializable
    data class ScoreCardResult(
        @SerialName("announcement1")
        val announcement1: String,
        @SerialName("announcement2")
        val announcement2: String?,
        @SerialName("currentBattingOrder")
        val currentBattingOrder: Int,
        @SerialName("format")
        val format: String,
        @SerialName("key")
        val key: String,
        @SerialName("lastCommentary")
        val lastCommentary: LastCommentary,
        @SerialName("now")
        val now: Now,
        @SerialName("powerplay")
        val powerPlay: String,
        @SerialName("powerplayOver")
        val powerPlayOver: Int,
        @SerialName("settingObj")
        val settingObj: SettingObj,
        @SerialName("status")
        val status: String,
        @SerialName("teams")
        val teams: Teams
    ) : Result

    @Serializable
    data class VenueResult(
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
    ) : Result
}
