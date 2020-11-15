package es.iessaladillo.pedrojoya.intents.ui.battle

import android.content.Context
import android.content.Intent
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.ui.winner.WinnerActivity

private const val STATE_POKEMON_A = "STATE_POKEMON_A"
private const val STATE_POKEMON_B = "STATE_POKEMON_B"
class FightViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _pokemonA: MutableLiveData<Pokemon> = savedStateHandle.getLiveData(STATE_POKEMON_A, Database.getRandomPokemon())
    val pokemonA: LiveData<Pokemon>
        get() = _pokemonA
    private val _pokemonB: MutableLiveData<Pokemon> = savedStateHandle.getLiveData(STATE_POKEMON_B, Database.getRandomPokemon())
    val pokemonB: LiveData<Pokemon>
        get() = _pokemonB

    fun changePokemonA(poke: Pokemon) {
        _pokemonA.value = poke
    }

    fun changePokemonB(poke: Pokemon) {
        _pokemonB.value = poke
    }

    fun navigateToWinner(context: Context) {
        val intent: Intent = if(pokemonA.value!!.forsa>=pokemonB.value!!.forsa){
            WinnerActivity.newIntent(context, pokemonA.value!!)
        }else{
            WinnerActivity.newIntent(context, pokemonB.value!!)
        }
        context.startActivity(intent)


    }



}