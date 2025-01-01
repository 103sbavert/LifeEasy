package llp.lifeeasy.cricradio.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinproject.composeapp.generated.resources.Res
import kotlinx.serialization.json.Json
import llp.lifeeasy.cricradio.data.models.Resource
import llp.lifeeasy.cricradio.data.models.common.APIResponse
import llp.lifeeasy.cricradio.data.models.common.Result

class KtorClient {
    companion object {
        const val AUTH_TOKEN = "Basic Y3JpY2tldFJhZGlvOmNyaWNrZXRAJCUjUmFkaW8xMjM="
        const val MATCH_KEY = "SA_vs_SL_2024-12-05_1732276435.300452"
    }

    private val client = HttpClient(CIO) {
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
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }

    }

    suspend fun getScoreCard(): Resource<Result.ScoreCardResult> {
        val result = client.get {
            url {
                appendPathSegments("mini-match-card")
            }
            parameter("key", MATCH_KEY)
        }

        if (!result.status.isSuccess()) return Resource.Failure(result.status.description)
        return Resource.Success(result.body<APIResponse<Result.ScoreCardResult>>().responseData.result)
    }

    suspend fun getVenueDetails(): Resource<Result.VenueResult> {
        val result = client.get {
            url {
                appendPathSegments("venue-info")
            }
            parameter("key", MATCH_KEY)
        }

        if (!result.status.isSuccess()) return Resource.Failure(result.status.description)
        return Resource.Success(result.body<APIResponse<Result.VenueResult>>().responseData.result)
    }


}