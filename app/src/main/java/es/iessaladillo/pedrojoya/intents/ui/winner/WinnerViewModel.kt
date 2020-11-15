package es.iessaladillo.pedrojoya.intents.ui.winner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon


private const val STATE_WINNER = "STATE_WINNER"
class WinnerViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _winner: MutableLiveData<Pokemon> = savedStateHandle.getLiveData(
        STATE_WINNER, Database.getRandomPokemon())
    val winner: LiveData<Pokemon>
        get() = _winner

    fun changePokemon(poke: Pokemon) {
        _winner.value = poke
    }
}