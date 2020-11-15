package es.iessaladillo.pedrojoya.intents.ui.battle

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.intents.R
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.Database.getPokemonById
import es.iessaladillo.pedrojoya.intents.data.local.Database.getRandomPokemon
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.databinding.BattleActivityBinding
import es.iessaladillo.pedrojoya.intents.ui.selection.SelectionActivity
import es.iessaladillo.pedrojoya.intents.ui.winner.WinnerActivity

private const val RC_POKEMON_SELECTION: Int = 1
private const val RC_POKEMON_FIGHT: Int = 1

class BattleActivity : AppCompatActivity() {

    private lateinit var pokemonA : Pokemon
    private lateinit var pokemonB : Pokemon

    private val binding: BattleActivityBinding by lazy {
        BattleActivityBinding.inflate(layoutInflater)
    }

    private val fightViewModel: FightViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()
        observePokemons()
    }


    private fun setupViews() {

        binding.buttonFight.setOnClickListener { fightViewModel.navigateToWinner(this) }

        binding.linearLayoutA.setOnClickListener { navigateToSelectionActivity(pokemonA, 1) }

        binding.linearLayoutB.setOnClickListener { navigateToSelectionActivity(pokemonB, 2) }

    }

    private fun observePokemons() {
        fightViewModel.pokemonA.observe(this) { showPokemonA(it) }
        fightViewModel.pokemonB.observe(this) { showPokemonB(it) }
    }
    private fun showPokemonA(poke: Pokemon) {
        binding.imageViewFightOne.setImageDrawable(getDrawable(poke.idIcon))
        binding.textFightOne.text = getText(poke.idName)
        pokemonA = fightViewModel.pokemonA.value!!
    }
    private fun showPokemonB(poke: Pokemon) {
        binding.imageViewFightTwo.setImageDrawable(getDrawable(poke.idIcon))
        binding.textFightTwo.text = getText(poke.idName)
        pokemonB = fightViewModel.pokemonB.value!!
    }



    private fun navigateToSelectionActivity(pokemon : Pokemon, screen : Int) {
        val intent = SelectionActivity.newIntent(this, pokemon, screen)
        startActivityForResult(intent, RC_POKEMON_FIGHT)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (resultCode == RESULT_OK && requestCode == RC_POKEMON_SELECTION && intent != null) {
            extractResult(intent)
        }

    }

    private fun extractResult(intent: Intent) {
        if (!intent.hasExtra(SelectionActivity.EXTRA_POKEMON) || !intent.hasExtra(SelectionActivity.EXTRA_SCREEN) ) {
            throw RuntimeException(
                "BattleActivity must receive pokemonId and screen in result intent")
        }

        if (intent.getIntExtra(SelectionActivity.EXTRA_SCREEN, 1)==1){
            fightViewModel.changePokemonA(
                intent.getParcelableExtra<Pokemon>(SelectionActivity.EXTRA_POKEMON)!!)
        }else{
            fightViewModel.changePokemonB(
                intent.getParcelableExtra<Pokemon>(SelectionActivity.EXTRA_POKEMON)!!)
        }


    }

}