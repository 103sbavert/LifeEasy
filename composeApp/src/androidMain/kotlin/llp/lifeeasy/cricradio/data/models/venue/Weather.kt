package llp.lifeeasy.cricradio.data.models.venue


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Weather(
    @SerialName("chance_of_rain")
    val chanceOfRain: Int,
    @SerialName("condition")
    val condition: Condition,
    @SerialName("last_updated")
    val lastUpdated: String,
    @SerialName("location")
    val location: String,
    @SerialName("temp_c")
    val tempC: Double
)