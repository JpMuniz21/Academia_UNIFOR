package com.example.academia_unifor

import android.content.Intent

class SurgeryActivity : PerguntaComDetalhesActivity() {
    override val screenLayout = R.layout.activity_surgery
    override val noButtonId = R.id.surgery_no
    override val yesButtonId = R.id.surgery_yes
    override val detailInputId = R.id.surgery_input
    override val detailError = R.string.surgery_error

    override fun abrirProximaTela() {
        startActivity(Intent(this, CompletionActivity::class.java))
    }
}
