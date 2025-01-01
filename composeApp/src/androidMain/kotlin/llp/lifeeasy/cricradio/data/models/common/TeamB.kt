package llp.lifeeasy.cricradio.data.models.common


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import llp.lifeeasy.cricradio.data.models.scorecard.Score

@Keep
@Serializable
data class TeamB(
    @SerialName("b_1_score")
    override val firstScore: Score? = null,
    @SerialName("b_2_score")
    override val secondScore: Score? = null,
    @SerialName("logo")
    override val logo: String,
    @SerialName("name")
    override val name: String,
    @SerialName("shortName")
    override val shortName: String
) : Team()