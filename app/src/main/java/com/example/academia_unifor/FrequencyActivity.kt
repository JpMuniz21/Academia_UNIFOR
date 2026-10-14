package com.example.academia_unifor

import android.content.Intent

class FrequencyActivity : CadastroActivity() {
    override val screenLayout = R.layout.activity_frequency
    override val optionIds = listOf(R.id.option_frequency_1, R.id.option_frequency_2,
        R.id.option_frequency_3, R.id.option_frequency_4, R.id.option_frequency_5,
        R.id.option_frequency_6, R.id.option_frequency_7)
    override val singleSelection = true
    override val selectionError = R.string.select_frequency

    override fun onAnswerConfirmed() {
        startActivity(Intent(this, InactivityActivity::class.java))
    }
}
