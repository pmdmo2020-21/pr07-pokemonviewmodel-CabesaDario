package es.iessaladillo.pedrojoya.intents.ui.battle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon

class FightViewModel : ViewModel() {
    private val _pokemonA: MutableLiveData<Pokemon> = MutableLiveData(Database.getRandomPokemon())
    val pokemonA: LiveData<Pokemon>
        get() = _pokemonA
    private val _pokemonB: MutableLiveData<Pokemon> = MutableLiveData(Database.getRandomPokemon())
    val pokemonB: LiveData<Pokemon>
        get() = _pokemonB

    fun changePokemonA(poke: Pokemon) {
        _pokemonA.value = poke
    }

    fun changePokemonB(poke: Pokemon) {
        _pokemonB.value = poke
    }




}