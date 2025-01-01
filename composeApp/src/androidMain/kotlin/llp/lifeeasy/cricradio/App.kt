package llp.lifeeasy.cricradio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import llp.lifeeasy.cricradio.data.MainViewModel
import llp.lifeeasy.cricradio.data.models.Resource
import llp.lifeeasy.cricradio.data.models.common.Team
import llp.lifeeasy.cricradio.data.models.scorecard.LastCommentary
import llp.lifeeasy.cricradio.data.models.scorecard.Now
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App(viewModel: MainViewModel) {
    ScoreCard(Modifier, viewModel)
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

    Surface(modifier.padding(12.dp), color = MaterialTheme.colors.primarySurface, shape = RoundedCornerShape(10.dp)) {
        Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            ScoreCardContent(Modifier, battingTeam!!, lastCommentary, bowlingTeam!!)

            Separator(Modifier.padding(vertical = 4.dp))

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
            Text(battingTeam.shortName, style = MaterialTheme.typography.subtitle1, fontSize = 18.sp)
            AsyncImage("https://img.icons8.com/officel/80/cricket.png", "${battingTeam.logo} Logo", Modifier.size(16.dp))
        }
        Text("${battingTeam.firstScore!!.runs}/${battingTeam.firstScore!!.wickets}", style = MaterialTheme.typography.subtitle1, fontSize = 20.sp, color = MaterialTheme.colors.onPrimary)
        Text(battingTeam.firstScore!!.overs, style = MaterialTheme.typography.subtitle1, fontSize = 16.sp, color = MaterialTheme.colors.onPrimary.copy(0.8F))
    }
}

@Composable
fun BowlingTeam(modifier: Modifier = Modifier, bowlingTeam: Team) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(6.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            AsyncImage(bowlingTeam.logo, "${bowlingTeam.logo} Logo", Modifier.size(20.dp))
            Text(bowlingTeam.shortName, style = MaterialTheme.typography.subtitle1, fontSize = 18.sp)
        }
        Text("${bowlingTeam.firstScore!!.runs}/${bowlingTeam.firstScore!!.wickets}", style = MaterialTheme.typography.subtitle1, fontSize = 16.sp, color = MaterialTheme.colors.onPrimary.copy(0.8F))
        Text(bowlingTeam.firstScore!!.overs, style = MaterialTheme.typography.subtitle1, fontSize = 16.sp, color = MaterialTheme.colors.onPrimary.copy(0.8F))
    }
}

@Composable
fun LastCommentary(modifier: Modifier = Modifier, lastCommentary: LastCommentary) {
    Column(modifier = Modifier.wrapContentSize(), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(lastCommentary.primaryText, modifier, style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold, fontSize = 22.sp)
        lastCommentary.secondaryText?.let { Text(it, modifier, style = MaterialTheme.typography.h3, fontWeight = FontWeight.Bold, fontSize = 18.sp) }
        lastCommentary.tertiaryText?.let { Text(it, modifier, style = MaterialTheme.typography.h1, fontWeight = FontWeight.Bold, fontSize = 12.sp) }
    }
}

@Composable
fun Separator(modifier: Modifier = Modifier) {
    Spacer(
        modifier
            .background(MaterialTheme.colors.onPrimary)
            .height(1.dp)
            .fillMaxWidth()
    )
}

@Composable
fun ScoreCardFooter(modifier: Modifier = Modifier, now: Now, majorAnnouncement: String) {
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        val annotatedStringBuilder = AnnotatedString.Builder()
        if (now.runRate.isNotBlank()) {
            annotatedStringBuilder.append(AnnotatedString("CRR: ", SpanStyle(MaterialTheme.colors.onPrimary.copy(alpha = 0.5F))))
            annotatedStringBuilder.append(now.runRate)
        }

        if (now.reqRunRate.isNotBlank()) {
            if (annotatedStringBuilder.length != 0) annotatedStringBuilder.append(" | ")

            annotatedStringBuilder.append(AnnotatedString("RRR: ", SpanStyle(MaterialTheme.colors.onPrimary.copy(alpha = 0.5F))))
            annotatedStringBuilder.append(now.reqRunRate)
        }

        Text(annotatedStringBuilder.toAnnotatedString())

        val annotatedString = AnnotatedString(majorAnnouncement, SpanStyle(MaterialTheme.colors.onPrimary.copy(alpha = 0.5F)))
        Text(annotatedString)
    }
}