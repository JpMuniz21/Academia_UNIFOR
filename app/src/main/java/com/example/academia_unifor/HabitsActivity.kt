package com.example.academia_unifor

import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import com.google.android.material.button.MaterialButton

class HabitsActivity : CadastroActivity() {
    override val screenLayout = R.layout.activity_habits
    override val optionIds = emptyList<Int>()
    private var smokingChoice = -1
    private var alcoholChoice = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        smokingChoice = savedInstanceState?.getInt("smoking_choice", -1) ?: -1
        alcoholChoice = savedInstanceState?.getInt("alcohol_choice", -1) ?: -1
        bindChoices(listOf(R.id.smoking_no, R.id.smoking_yes), smokingChoice) { smokingChoice = it }
        bindChoices(listOf(R.id.alcohol_no, R.id.alcohol_yes), alcoholChoice) { alcoholChoice = it }
    }

    private fun bindChoices(ids: List<Int>, selected: Int, onSelected: (Int) -> Unit) {
        ids.forEach { id ->
            findViewById<MaterialButton>(id).isChecked = selected == id
            findViewById<MaterialButton>(id).setOnClickListener {
                onSelected(id)
                ids.forEach { choice ->
                    findViewById<MaterialButton>(choice).isChecked = choice == id
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("smoking_choice", smokingChoice)
        outState.putInt("alcohol_choice", alcoholChoice)
        super.onSaveInstanceState(outState)
    }

    override fun onAnswerConfirmed() {
        val message = when {
            smokingChoice == -1 -> R.string.select_smoking
            alcoholChoice == -1 -> R.string.select_alcohol
            else -> null
        }
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } else {
            startActivity(Intent(this, SurgeryActivity::class.java))
        }
    }
}
