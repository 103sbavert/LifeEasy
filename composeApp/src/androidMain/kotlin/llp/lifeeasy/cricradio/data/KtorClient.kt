package llp.lifeeasy.cricradio.data

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.pingInterval
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.get
import io.ktor.client.request.host
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import io.ktor.http.appendPathSegments
import io.ktor.http.authority
import io.ktor.http.isSuccess
import io.ktor.serialization.JsonConvertException
import io.ktor.serialization.kotlinx.json.json
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketExtensionsConfig
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import kotlinx.serialization.json.Json
import llp.lifeeasy.cricradio.data.models.Resource
import llp.lifeeasy.cricradio.data.models.common.APIResponse
import llp.lifeeasy.cricradio.data.models.common.Result
import java.lang.Thread.sleep

class KtorClient {
    companion object {
        const val WS_HOST = "ws.postman-echo.com"
        const val HTTP_HOST = "3.6.243.12"
        const val PORT = 5001
        const val AUTH_TOKEN = "Basic Y3JpY2tldFJhZGlvOmNyaWNrZXRAJCUjUmFkaW8xMjM="
        const val MATCH_KEY = "SA_vs_SL_2024-12-05_1732276435.300452"
    }


    private val httpClient = HttpClient(CIO) {
        defaultRequest {
            headers["Authorization"] = AUTH_TOKEN
            url(
                scheme = "http",
                host = HTTP_HOST,
                port = PORT,
            ) {
                appendPathSegments("api", "v2", "match", "")
            }
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    private val webSocketClient = HttpClient(CIO) {
        WebSockets {
            pingIntervalMillis = 2000
        }
    }

    private val webSocketSession: Flow<WebSocketSession> = flow {
        emit(webSocketClient.webSocketSession {
            url("wss://ws.postman-echo.com/raw")
        })
    }

    suspend fun websocketEcho(message: String = "Echo'd message") {
        webSocketSession.collectLatest {
            if (it.isActive) {
                it.send(Frame.Text(message))
                Log.e("WEBSOCKET", "websocketEcho: " + (it.incoming.receive() as? Frame.Text)?.readText())
            }
        }
    }

    suspend fun getScoreCard(): Resource<Result.ScoreCardResult> {
        val result = httpClient.get {
            url {
                appendPathSegments("mini-match-card")
            }
            parameter("key", MATCH_KEY)
        }

        if (!result.status.isSuccess()) return Resource.Failure(result.status.description)
        return Resource.Success(result.body<APIResponse<Result.ScoreCardResult>>().responseData.result)
    }

    suspend fun getVenueDetails(): Resource<Result.VenueResult> {
        try {
            val result = httpClient.get {
                url {
                    appendPathSegments("venue-info")
                }
                parameter("key", MATCH_KEY)
            }

            if (!result.status.isSuccess()) return Resource.Failure(result.status.description)

            return Resource.Success(result.body<APIResponse<Result.VenueResult>>().responseData.result)
        } catch (e: Exception) {
            Log.e("VenueDetails", "The following exception occurred\n${e.stackTrace}")
            return Resource.Failure(e.message!!)
        }
    }


}