package llp.lifeeasy.cricradio.data.models.scorecard


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Teams(
    @SerialName("a")
    val a: A,
    @SerialName("b")
    val b: B
)