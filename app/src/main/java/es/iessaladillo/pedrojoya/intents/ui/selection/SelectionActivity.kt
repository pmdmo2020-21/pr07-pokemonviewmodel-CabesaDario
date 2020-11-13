package es.iessaladillo.pedrojoya.intents.ui.selection

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.loader.ResourcesProvider
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.get
import es.iessaladillo.pedrojoya.intents.R
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.Database.getAllPokemons

import es.iessaladillo.pedrojoya.intents.data.local.Database.getPokemonById
import es.iessaladillo.pedrojoya.intents.data.local.Database.getRandomPokemon
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.databinding.BattleActivityBinding
import es.iessaladillo.pedrojoya.intents.databinding.SelectionActivityBinding
import es.iessaladillo.pedrojoya.intents.ui.battle.FightViewModel

class SelectionActivity : AppCompatActivity() {

    companion object {

        const val EXTRA_POKEMON = "EXTRA_POKEMON"
        const val EXTRA_SCREEN = "EXTRA_SCREEN"

        fun newIntent(context: Context, poke: Pokemon, screen: Int): Intent =
            Intent(context, SelectionActivity::class.java)
                .putExtras(bundleOf(
                    EXTRA_POKEMON to poke, EXTRA_SCREEN to screen))

    }
    private lateinit var arrayButton: Array <RadioButton>
    private val binding: SelectionActivityBinding by lazy {
        SelectionActivityBinding.inflate(layoutInflater)
    }

    private val selectionViewModel: SelectionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpViews()
        observePokemon()
        getIntentData()
    }

    private fun setUpViews() {
        binding.rdbttPikachu.tag = Database.pikasho
        binding.rdbttGiratina.tag = Database.giratina
        binding.rdbttCubone.tag = Database.cubone
        binding.rdbttGyarados.tag = Database.gyarados
        binding.rdbttFeebas.tag = Database.feebas
        binding.rdbttBulbasaur.tag = Database.bulbasur

        arrayButton = arrayOf(binding.rdbttBulbasaur, binding.rdbttCubone, binding.rdbttPikachu,
            binding.rdbttGiratina, binding.rdbttGyarados, binding.rdbttFeebas)

        binding.rdbttFeebas.setOnClickListener { updateViews(binding.rdbttFeebas) }
        binding.rdbttGyarados.setOnClickListener { updateViews(binding.rdbttGyarados) }
        binding.rdbttGiratina.setOnClickListener { updateViews(binding.rdbttGiratina) }
        binding.rdbttPikachu.setOnClickListener { updateViews(binding.rdbttPikachu) }
        binding.rdbttBulbasaur.setOnClickListener { updateViews(binding.rdbttBulbasaur) }
        binding.rdbttCubone.setOnClickListener { updateViews(binding.rdbttCubone) }

    }

    private fun updateViews(radioButton: RadioButton) {
        selectionViewModel.changePokemon(radioButton.tag as Pokemon)
    }

    private fun observePokemon() {
        selectionViewModel.pokemonChecked.observe(this) { checkPokemon(it) }
    }

    private fun checkPokemon(poke: Pokemon) {
        for (button in arrayButton){
            button.isChecked = (button.tag as Pokemon) == poke
        }
    }





    override fun onBackPressed() {
        val result = Intent().putExtras(
            bundleOf(EXTRA_POKEMON to selectionViewModel.pokemonChecked.value,
                EXTRA_SCREEN to selectionViewModel.screen.value))
        setResult(RESULT_OK, result)
        super.onBackPressed()
    }




    private fun getIntentData() {
        if (intent == null || !intent.hasExtra(EXTRA_POKEMON)) {
            throw RuntimeException(
                "SelectionActivity needs to receive pokemon as extra")
        }

        selectionViewModel.changePokemon(intent.getParcelableExtra(EXTRA_POKEMON)!!)

        selectionViewModel.changeScreen(intent.getIntExtra(EXTRA_SCREEN,1))
    }

}