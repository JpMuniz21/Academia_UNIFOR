package com.example.academia_unifor

import android.content.Intent

class PainActivity : PerguntaComDetalhesActivity() {
    override val screenLayout = R.layout.activity_pain
    override val noButtonId = R.id.pain_no
    override val yesButtonId = R.id.pain_yes
    override val detailInputId = R.id.pain_input
    override val detailError = R.string.pain_error

    override fun abrirProximaTela() {
        startActivity(Intent(this, HabitsActivity::class.java))
    }
}
