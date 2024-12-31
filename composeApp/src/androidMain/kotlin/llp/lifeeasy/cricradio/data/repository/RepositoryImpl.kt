package llp.lifeeasy.cricradio.data.repository

import llp.lifeeasy.cricradio.data.KtorClient
import llp.lifeeasy.cricradio.data.models.scorecard.ScoreCard
import llp.lifeeasy.cricradio.data.models.venue.VenueEntity

class RepositoryImpl(override val ktorClient: KtorClient) : Repository {
    override suspend fun getScoreCard(): ScoreCard {
        return ktorClient.getScoreCard()
    }

    override suspend fun getVenueDetails(): VenueEntity {
        return ktorClient.getVenueDetails()
    }

}