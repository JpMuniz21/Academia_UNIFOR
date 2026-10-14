package com.example.academia_unifor

import android.content.Intent

/** Reuses the registration form behavior with the reasons for choosing the gym. */
class ReasonActivity : CadastroActivity() {
    override val screenLayout = R.layout.activity_reason
    override val optionIds = listOf(R.id.option_price, R.id.option_recommendation,
        R.id.option_professional, R.id.option_service, R.id.option_mobility,
        R.id.option_student_employee, R.id.option_other)
    override val otherError = R.string.describe_reason

    override fun onAnswerConfirmed() {
        startActivity(Intent(this, FrequencyActivity::class.java))
    }
}
