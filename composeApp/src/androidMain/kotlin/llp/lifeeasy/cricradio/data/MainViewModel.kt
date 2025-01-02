package llp.lifeeasy.cricradio.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import llp.lifeeasy.cricradio.data.models.Resource
import llp.lifeeasy.cricradio.data.models.common.Result
import llp.lifeeasy.cricradio.data.models.common.Team
import llp.lifeeasy.cricradio.data.repository.Repository

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val _scoreCardResult: MutableStateFlow<Resource<Result.ScoreCardResult>> = MutableStateFlow(Resource.Loading())
    private val _venueResult: MutableStateFlow<Resource<Result.VenueResult>> = MutableStateFlow(Resource.Loading())
    val scoreCardResult get() = _scoreCardResult as StateFlow<Resource<Result.ScoreCardResult>>
    val venueResult get() = _venueResult as StateFlow<Resource<Result.VenueResult>>
    private val _currentBattingTeam = MutableStateFlow<Resource<Team>>(Resource.Loading())
    val currentBattingTeam get() = _currentBattingTeam as StateFlow<Resource<Team>>
    private val _currentBowlingTeam = MutableStateFlow<Resource<Team>>(Resource.Loading())
    val currentBowlingTeam get() = _currentBowlingTeam as StateFlow<Resource<Team>>

    init {
        viewModelScope.launch {
            _scoreCardResult.emit(repository.getScoreCardDetails())
        }

        viewModelScope.launch {
            _venueResult.emit(repository.getVenueDetails())
        }

        viewModelScope.launch {
            scoreCardResult.collectLatest {
                when (it) {
                    is Resource.Failure -> throw IllegalStateException("Looks like the server is messed up lol! Here are some details ${(scoreCardResult.value as Resource.Failure<Result.ScoreCardResult>).message}")
                    is Resource.Success -> {
                        when (it.data.settingObj.currentTeam) {
                            "a" -> {
                                _currentBattingTeam.emit(Resource.Success(it.data.teams.a))
                                _currentBowlingTeam.emit(Resource.Success(it.data.teams.b))
                            }

                            "b" -> {
                                _currentBattingTeam.emit(Resource.Success(it.data.teams.b))
                                _currentBowlingTeam.emit(Resource.Success(it.data.teams.a))
                            }
                        }
                    }
                    else -> {}
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            repeat(1000) {
                delay(2000L)
                repository.sendWSEcho("message")
            }
        }
    }

}