package com.example.academia_unifor

import android.content.Intent

class InactivityActivity : CadastroActivity() {
    override val screenLayout = R.layout.activity_inactivity
    override val optionIds = emptyList<Int>()
    override val answerInputId = R.id.inactivity_input

    override fun onAnswerConfirmed() {
        startActivity(Intent(this, ExperienceActivity::class.java))
    }
}
