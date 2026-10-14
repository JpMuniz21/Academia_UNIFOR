package com.example.academia_unifor

import android.content.Intent

class InjuryActivity : PerguntaComDetalhesActivity() {
    override val screenLayout = R.layout.activity_injury
    override val noButtonId = R.id.injury_no
    override val yesButtonId = R.id.injury_yes
    override val detailInputId = R.id.injury_input
    override val detailError = R.string.injury_error

    override fun abrirProximaTela() {
        startActivity(Intent(this, PainActivity::class.java))
    }
}
