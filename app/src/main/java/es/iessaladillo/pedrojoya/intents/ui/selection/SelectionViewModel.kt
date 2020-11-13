package es.iessaladillo.pedrojoya.intents.ui.selection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon

class SelectionViewModel : ViewModel() {
    private val _pokemonChecked: MutableLiveData<Pokemon> = MutableLiveData(Database.getRandomPokemon())
    val pokemonChecked: LiveData<Pokemon>
        get() = _pokemonChecked

    fun changePokemon(poke: Pokemon) {
        _pokemonChecked.value = poke
    }
    private val _screen: MutableLiveData<Int> = MutableLiveData(1)
    val screen: LiveData<Int>
        get() = _screen
    fun changeScreen(screen: Int) {
        _screen.value = screen
    }

}