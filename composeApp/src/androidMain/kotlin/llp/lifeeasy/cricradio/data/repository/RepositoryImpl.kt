package llp.lifeeasy.cricradio.data.repository

import llp.lifeeasy.cricradio.data.KtorClient
import llp.lifeeasy.cricradio.data.models.Resource
import llp.lifeeasy.cricradio.data.models.common.APIResponse
import llp.lifeeasy.cricradio.data.models.common.Result

class RepositoryImpl(override val ktorClient: KtorClient) : Repository {
    override suspend fun getScoreCardDetails(): Resource<Result.ScoreCardResult> {
        return ktorClient.getScoreCard()
    }

    override suspend fun getVenueDetails(): Resource<Result.VenueResult> {
        return ktorClient.getVenueDetails()
    }
}