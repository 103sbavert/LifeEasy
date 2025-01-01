package llp.lifeeasy.cricradio.data.repository

import llp.lifeeasy.cricradio.data.KtorClient
import llp.lifeeasy.cricradio.data.models.Resource
import llp.lifeeasy.cricradio.data.models.common.Result

interface Repository {
    val ktorClient: KtorClient

    suspend fun getScoreCardDetails(): Resource<Result.ScoreCardResult>

    suspend fun getVenueDetails(): Resource<Result.VenueResult>

}