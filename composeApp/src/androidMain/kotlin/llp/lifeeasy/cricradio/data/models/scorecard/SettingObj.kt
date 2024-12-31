package llp.lifeeasy.cricradio.data.models.scorecard


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class SettingObj(
    @SerialName("currentInning")
    val currentInning: Int,
    @SerialName("currentTeam")
    val currentTeam: String
)