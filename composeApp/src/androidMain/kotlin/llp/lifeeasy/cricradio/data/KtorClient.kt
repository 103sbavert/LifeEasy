package llp.lifeeasy.cricradio.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments
import kotlinx.serialization.json.Json
import llp.lifeeasy.cricradio.data.models.scorecard.ScoreCard
import llp.lifeeasy.cricradio.data.models.venue.VenueEntity

class KtorClient {
    companion object {
        const val AUTH_TOKEN = "Basic Y3JpY2tldFJhZGlvOmNyaWNrZXRAJCUjUmFkaW8xMjM="
        const val MATCH_KEY = "SA_vs_SL_2024-12-05_1732276435.300452"
    }

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            Json {
                ignoreUnknownKeys = true
                isLenient = true
            }
        }

        defaultRequest {
            headers["Authorization"] = AUTH_TOKEN
            url(
                scheme = "http",
                host = "3.6.243.12",
                port = 5001,
            ) {
                appendPathSegments("api", "v2", "match", "")
            }
        }
    }

    suspend fun getScoreCard(): ScoreCard {
        return client.get {
            url {
                appendPathSegments("mini-match-card")
            }
            parameter("key", MATCH_KEY)
        }.body<ScoreCard>()
    }

    suspend fun getVenueDetails(): VenueEntity {
        return client.get {
            url {
                appendPathSegments("venue-info")
            }
            parameter("key", MATCH_KEY)
        }.body<VenueEntity>()
    }


}