package llp.lifeeasy.cricradio.data.repository

import llp.lifeeasy.cricradio.data.KtorClient
import llp.lifeeasy.cricradio.data.models.scorecard.ScoreCard
import llp.lifeeasy.cricradio.data.models.venue.VenueEntity

interface Repository {
    val ktorClient: KtorClient

    suspend fun getScoreCard(): ScoreCard

    suspend fun getVenueDetails(): VenueEntity

}