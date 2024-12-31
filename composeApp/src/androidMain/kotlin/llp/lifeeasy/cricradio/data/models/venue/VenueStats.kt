package llp.lifeeasy.cricradio.data.models.venue


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class VenueStats(
    @SerialName("ballFirstWins")
    val ballFirstWins: Int,
    @SerialName("batFirstWins")
    val batFirstWins: Int,
    @SerialName("battingFirst")
    val battingFirst: BattingFirst,
    @SerialName("battingSecond")
    val battingSecond: BattingSecond,
    @SerialName("highestChased")
    val highestChased: Int,
    @SerialName("lowestDefended")
    val lowestDefended: Int,
    @SerialName("matchesPlayed")
    val matchesPlayed: Int
)