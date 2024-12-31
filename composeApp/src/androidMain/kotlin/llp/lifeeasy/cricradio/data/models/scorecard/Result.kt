package llp.lifeeasy.cricradio.data.models.scorecard


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Result(
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
    val powerplay: String,
    @SerialName("powerplayOver")
    val powerplayOver: Int,
    @SerialName("settingObj")
    val settingObj: SettingObj,
    @SerialName("status")
    val status: String,
    @SerialName("teams")
    val teams: Teams
)