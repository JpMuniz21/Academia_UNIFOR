package com.example.academia_unifor

import android.widget.Toast

class CompletionActivity : CadastroActivity() {
    override val screenLayout = R.layout.activity_completion
    override val optionIds = emptyList<Int>()

    override fun onAnswerConfirmed() {
        // A próxima etapa será integrada com a tela de objetivos.
        Toast.makeText(this, R.string.objectives_pending, Toast.LENGTH_SHORT).show()
    }
}
