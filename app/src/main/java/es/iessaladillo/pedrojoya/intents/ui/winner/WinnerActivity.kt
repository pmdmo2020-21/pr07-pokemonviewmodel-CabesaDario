package es.iessaladillo.pedrojoya.intents.ui.winner

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.databinding.BattleActivityBinding
import es.iessaladillo.pedrojoya.intents.databinding.SelectionActivityBinding
import es.iessaladillo.pedrojoya.intents.databinding.WinnerActivityBinding
import es.iessaladillo.pedrojoya.intents.ui.selection.SelectionActivity
import es.iessaladillo.pedrojoya.intents.ui.selection.SelectionViewModel

class WinnerActivity : AppCompatActivity() {
    private val binding: WinnerActivityBinding by lazy {
        WinnerActivityBinding.inflate(layoutInflater)
    }

    private val winnerViewModel: WinnerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeWinner()
        getIntentData()
    }
    private fun observeWinner() {
        winnerViewModel.winner.observe(this) { showPokemon(it) }
    }
    private fun showPokemon(poke: Pokemon) {
        binding.imageGanador.setImageDrawable(getDrawable(poke.idIcon))
        binding.winnerText.text = getText(poke.idName)
    }
    companion object {

        const val EXTRA_POKEMON = "EXTRA_POKEMON"


        fun newIntent(context: Context, poke: Pokemon): Intent =
            Intent(context, WinnerActivity::class.java)
                .putExtras(
                    bundleOf(
                    EXTRA_POKEMON to poke)
                )

    }
    private fun getIntentData() {
        if (intent == null || !intent.hasExtra(EXTRA_POKEMON)) {
            throw RuntimeException(
                "FighrActivity needs to receive pokemon id as extra")
        }
        winnerViewModel.changePokemon(intent.getParcelableExtra(EXTRA_POKEMON)!!)

    }


}