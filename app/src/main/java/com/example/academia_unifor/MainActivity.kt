package com.example.academia_unifor

import android.content.Intent

class MainActivity : CadastroActivity() {
    override val screenLayout = R.layout.activity_main
    override val optionIds = listOf(R.id.option_instagram, R.id.option_facebook,
        R.id.option_youtube, R.id.option_referral, R.id.option_google, R.id.option_other)

    override fun onAnswerConfirmed() {
        startActivity(Intent(this, ReasonActivity::class.java))
    }
}
