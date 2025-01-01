package llp.lifeeasy.cricradio.data.models.common


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Teams(
    @SerialName("a")
    val a: TeamA,
    @SerialName("b")
    val b: TeamB
)