package llp.lifeeasy.cricradio.data.models.common

import kotlinx.serialization.SerialName
import llp.lifeeasy.cricradio.data.models.scorecard.Score

abstract class Team {
    abstract val firstScore: Score?
    abstract val secondScore: Score?

    @SerialName("logo")
    abstract val logo: String

    @SerialName("name")
    abstract val name: String

    @SerialName("shortName")
    abstract val shortName: String
}