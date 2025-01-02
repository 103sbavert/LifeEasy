package llp.lifeeasy.cricradio

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import llp.lifeeasy.cricradio.data.MainViewModel
import llp.lifeeasy.cricradio.data.models.Resource
import llp.lifeeasy.cricradio.data.models.common.Team
import llp.lifeeasy.cricradio.data.models.scorecard.LastCommentary
import llp.lifeeasy.cricradio.data.models.scorecard.Now
import llp.lifeeasy.cricradio.data.models.venue.Season
import llp.lifeeasy.cricradio.data.models.venue.VenueDetails
import llp.lifeeasy.cricradio.data.models.venue.VenueStats
import llp.lifeeasy.cricradio.data.models.venue.Weather
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App(viewModel: MainViewModel) {
    val scrollState = rememberScrollState()

    Column(
        Modifier.verticalScroll(scrollState)
    ) {
        Spacer(Modifier.statusBarsPadding())
        ScoreCard(Modifier.padding(12.dp), viewModel)
        Venue(Modifier.padding(12.dp), viewModel)
        Spacer(Modifier.navigationBarsPadding())
    }
}

@Composable
fun ScoreCard(modifier: Modifier, viewModel: MainViewModel) {
    val resultState by viewModel.scoreCardResult.collectAsState()
    if (resultState !is Resource.Success) {
        return
    }
    val battingTeam = viewModel.currentBattingTeam.collectAsState().value.data
    val bowlingTeam = viewModel.currentBowlingTeam.collectAsState().value.data
    val majorAnnouncement = resultState.data!!.announcement1
    val lastCommentary = resultState.data!!.lastCommentary
    val now = resultState.data!!.now

    Surface(modifier, color = MaterialTheme.colorScheme.primaryContainer, shape = MaterialTheme.shapes.medium) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            ScoreCardContent(Modifier, battingTeam!!, lastCommentary, bowlingTeam!!)

            HorizontalSeparator(Modifier.background(MaterialTheme.colorScheme.onPrimary))

            ScoreCardFooter(Modifier, now, majorAnnouncement)
        }
    }
}

@Composable
fun ScoreCardContent(modifier: Modifier = Modifier, battingTeam: Team, lastCommentary: LastCommentary, bowlingTeam: Team) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier.fillMaxWidth()) {
        BattingTeam(Modifier, battingTeam)
        LastCommentary(Modifier, lastCommentary)
        BowlingTeam(Modifier, bowlingTeam)
    }
}

@Composable
fun BattingTeam(modifier: Modifier = Modifier, battingTeam: Team) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(6.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            AsyncImage(battingTeam.logo, "${battingTeam.logo} Logo", Modifier.size(20.dp))
            Text(battingTeam.shortName, style = MaterialTheme.typography.bodyMedium, fontSize = 18.sp)
            AsyncImage("https://img.icons8.com/officel/80/cricket.png", "${battingTeam.logo} Logo", Modifier.size(16.dp))
        }
        Text("${battingTeam.firstScore!!.runs}/${battingTeam.firstScore!!.wickets}", style = MaterialTheme.typography.bodyMedium, fontSize = 22.sp, color = MaterialTheme.colorScheme.onSecondary)
        Text(battingTeam.firstScore!!.overs, style = MaterialTheme.typography.bodyMedium, fontSize = 16.sp, color = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.8F))
    }
}

@Composable
fun BowlingTeam(modifier: Modifier = Modifier, bowlingTeam: Team) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(6.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            AsyncImage(bowlingTeam.logo, "${bowlingTeam.logo} Logo", Modifier.size(20.dp))
            Text(bowlingTeam.shortName, style = MaterialTheme.typography.bodyMedium, fontSize = 18.sp)
        }
        Text("${bowlingTeam.firstScore!!.runs}/${bowlingTeam.firstScore!!.wickets}", style = MaterialTheme.typography.bodyMedium, fontSize = 16.sp, color = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.8F))
        Text(bowlingTeam.firstScore!!.overs, style = MaterialTheme.typography.bodyMedium, fontSize = 16.sp, color = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.8F))
    }
}

@Composable
fun LastCommentary(modifier: Modifier = Modifier, lastCommentary: LastCommentary) {
    Column(modifier = Modifier.wrapContentSize(), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(lastCommentary.primaryText, modifier, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
        lastCommentary.secondaryText?.let { Text(it, modifier, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, fontSize = 18.sp) }
        lastCommentary.tertiaryText?.let { Text(it, modifier, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.SemiBold, fontSize = 12.sp) }
    }
}

@Composable
fun HorizontalSeparator(modifier: Modifier = Modifier) {
    Spacer(
        modifier
            .height(1.dp)
            .fillMaxWidth()
    )
}

@Composable
fun ScoreCardFooter(modifier: Modifier = Modifier, now: Now, majorAnnouncement: String) {
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        val annotatedStringBuilder = AnnotatedString.Builder()
        if (now.runRate.isNotBlank()) {
            annotatedStringBuilder.append(AnnotatedString("CRR: ", SpanStyle(MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.5F))))
            annotatedStringBuilder.append(AnnotatedString(now.runRate, SpanStyle(MaterialTheme.colorScheme.onPrimary)))
        }

        if (now.reqRunRate.isNotBlank()) {
            if (annotatedStringBuilder.length != 0) annotatedStringBuilder.append(" | ")

            annotatedStringBuilder.append(AnnotatedString("RRR: ", SpanStyle(MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.5F))))
            annotatedStringBuilder.append(AnnotatedString(now.reqRunRate, SpanStyle(MaterialTheme.colorScheme.onPrimary)))
        }

        Text(annotatedStringBuilder.toAnnotatedString())

        val annotatedString = AnnotatedString(majorAnnouncement, SpanStyle(MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.5F)))
        Text(annotatedString)
    }
}

@Composable
fun Venue(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    val venueResult by viewModel.venueResult.collectAsState()
    if (venueResult !is Resource.Success) return

    val venueImage = venueResult.data!!.venueDetails.photo
    val season = venueResult.data!!.season
    val venueDetails = venueResult.data!!.venueDetails
    val timing = venueResult.data!!.startDate.iso
    val tossResult = venueResult.data!!.toss.str
    val umpires = arrayOf(venueResult.data!!.firstUmpire, venueResult.data!!.secondUmpire, venueResult.data!!.thirdUmpire)
    val referee = venueResult.data!!.matchReferee
    val venueWeather = venueResult.data!!.weather
    val venueStats = venueResult.data!!.venueStats

    Surface(modifier) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp), horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier) {
            VenueImage(Modifier, venueImage)
            VenueTextDetails(Modifier.fillMaxWidth(), venueDetails, season)
            Timing(Modifier.fillMaxWidth(), timing)
            TossDetails(Modifier.fillMaxWidth(), tossResult)
            UmpireDetails(Modifier, umpires, referee)
            WeatherInfo(Modifier, venueWeather)
            VenueStats(Modifier, venueStats)
        }
    }
}

@Composable
fun WeatherInfo(modifier: Modifier, venueWeather: Weather) {
    Column(modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("Weather", color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.labelLarge)
        Surface(Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.surfaceContainer, border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceContainerHighest), shape = MaterialTheme.shapes.medium) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(venueWeather.condition.icon, "Weather icon", modifier = Modifier.fillMaxHeight(0.6f))
                Column(
                    Modifier
                        .fillMaxHeight()
                        .weight(1F), verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(venueWeather.location, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.titleSmall)
                    Text("${venueWeather.tempC} C", style = MaterialTheme.typography.displaySmall, color = Color.Yellow)
                    Text(venueWeather.location, color = MaterialTheme.colorScheme.onPrimaryContainer, style = MaterialTheme.typography.titleMedium)
                }
                Column(
                    Modifier
                        .fillMaxHeight()
                        .weight(1F), verticalArrangement = Arrangement.Center
                ) {
                    Text("Last Updated", color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.titleSmall)
                    Text(venueWeather.lastUpdated, color = MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.titleSmall)


                }
            }
        }
    }
}

@Composable
fun UmpireDetails(modifier: Modifier, umpires: Array<String>, referee: String) {
    Column(modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("Umpires", color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.labelLarge)
        Surface(border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceContainerHighest), color = MaterialTheme.colorScheme.surfaceContainer, shape = MaterialTheme.shapes.medium) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Row(Modifier.fillMaxWidth()) {
                    UmpireDetailCell(Modifier.weight(1F), "Umpire 1", umpires[0])
                    UmpireDetailCell(Modifier.weight(1F), "Umpire 2", umpires[1])
                }

                HorizontalSeparator(Modifier.background(MaterialTheme.colorScheme.onSurfaceVariant))

                Row(Modifier.fillMaxWidth()) {
                    UmpireDetailCell(Modifier.weight(1F), "Umpire 3", umpires[0])
                    UmpireDetailCell(Modifier.weight(1F), "Referee", referee)
                }
            }


        }
    }
}

@Composable
fun UmpireDetailCell(modifier: Modifier, label: String, name: String) {
    Column(modifier) {
        Text(label, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodySmall)
        Text(name, color = MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun TossDetails(modifier: Modifier, tossResult: String) {
    Surface(modifier, shape = RoundedCornerShape(4.dp), color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5F), border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary)) {
        Text(tossResult, modifier.padding(8.dp), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary)
    }
}

@Composable
fun Timing(modifier: Modifier, timing: String) {
    Text(timing, modifier, style = MaterialTheme.typography.bodyMedium)
}

@Composable
fun VenueTextDetails(modifier: Modifier, venueDetails: VenueDetails, season: Season) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(6.dp)) {
        VenueName(Modifier, venueDetails.venueInfo.longName)

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            VenueScrapTitle(Modifier, venueDetails.venueScraptitle)
            VenueSeason(Modifier, season)
        }
    }
}

@Composable
fun VenueSeason(modifier: Modifier, season: Season) {
    Text(season.name, modifier, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
}

@Composable
fun VenueScrapTitle(modifier: Modifier, scrapTitle: String) {
    Text(scrapTitle, modifier, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
}


@Composable
fun VenueName(modifier: Modifier, venueName: String) {
    Text(venueName, modifier, MaterialTheme.colorScheme.onPrimary, style = MaterialTheme.typography.headlineSmall, textDecoration = TextDecoration.Underline)
}

@Composable
fun VenueImage(modifier: Modifier = Modifier, image: String) {
    AsyncImage(
        image, "venue image details", contentScale = ContentScale.FillBounds, clipToBounds = true, modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .height(200.dp)
    )
}

@Composable
fun VenueStats(modifier: Modifier, venueStats: VenueStats) {
    val venueStatsMap = remember {
        mapOf(
            "Matches Played" to venueStats.matchesPlayed.toString(),
            "Ball First Wins" to venueStats.ballFirstWins.toString(),
            "Bat First Wins" to venueStats.batFirstWins.toString(),
            "Highest Chased" to venueStats.highestChased.toString(),
            "Lowest Defended" to venueStats.lowestDefended.toString(),
        )
    }

    Column(modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("Venue Stats", color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.labelLarge)
        Surface(shape = MaterialTheme.shapes.medium, border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceContainerHighest)) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                for (entry in venueStatsMap) {
                    VenueStatsRow(Modifier, entry.key, entry.value)
                }
            }
        }
    }
}

@Composable
fun VenueStatsRow(modifier: Modifier, label: String, value: String) {
    Row(
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, modifier = Modifier.weight(3F), textAlign = TextAlign.Start, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(value, modifier = Modifier.weight(1F), textAlign = TextAlign.Center)
    }
}