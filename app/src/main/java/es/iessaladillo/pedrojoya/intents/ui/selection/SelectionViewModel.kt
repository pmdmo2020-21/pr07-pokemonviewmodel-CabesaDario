package es.iessaladillo.pedrojoya.intents.ui.selection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon


private const val STATE_POKEMON_CHECKED = "STATE_POKEMON_CHECKED"
private const val STATE_SCREEN = "STATE_SCREEN"
class SelectionViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _pokemonChecked: MutableLiveData<Pokemon> = savedStateHandle.getLiveData(
        STATE_POKEMON_CHECKED, Database.getRandomPokemon())
    val pokemonChecked: LiveData<Pokemon>
        get() = _pokemonChecked

    fun changePokemon(poke: Pokemon) {
        _pokemonChecked.value = poke
    }
    private val _screen: MutableLiveData<Int>  = savedStateHandle.getLiveData(
        STATE_SCREEN, 1)
    val screen: LiveData<Int>
        get() = _screen
    fun changeScreen(screen: Int) {
        _screen.value = screen
    }

}