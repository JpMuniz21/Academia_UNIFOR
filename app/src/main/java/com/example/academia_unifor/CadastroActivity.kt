package com.example.academia_unifor

import android.os.Bundle

import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

abstract class CadastroActivity : AppCompatActivity() {
    protected abstract val screenLayout: Int
    protected open val optionIds = emptyList<Int>()
    protected open val answerInputId: Int? = null
    protected open val answerError = R.string.inactivity_error
    protected open val singleSelection = false
    protected open val selectionError = R.string.select_source
    protected open val otherError = R.string.describe_source

    protected abstract fun onAnswerConfirmed()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(screenLayout)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = false
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val keyboard = insets.getInsets(WindowInsetsCompat.Type.ime())
            view.setPadding(bars.left, bars.top, bars.right, maxOf(bars.bottom, keyboard.bottom))
            insets
        }
        findViewById<android.view.View>(R.id.back_button).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        val other = findViewById<CheckBox?>(R.id.option_other)
        val input = findViewById<EditText?>(R.id.other_input)
        other?.setOnCheckedChangeListener { _, checked ->
            input?.isEnabled = checked
            if (checked) input?.requestFocus() else input?.clearFocus()
        }
        if (singleSelection) {
            optionIds.forEach { id ->
                findViewById<CheckBox>(id).setOnCheckedChangeListener { _, checked ->
                    if (checked) {
                        optionIds.filter { it != id }.forEach { otherId ->
                            findViewById<CheckBox>(otherId).isChecked = false
                        }
                    }
                }
            }
        }
        findViewById<android.view.View>(R.id.next_button).setOnClickListener {
            val answerInput = answerInputId?.let { findViewById<EditText>(it) }
            if (answerInput != null && answerInput.text.isNullOrBlank()) {
                answerInput.error = getString(answerError)
                answerInput.requestFocus()
            } else if (optionIds.isNotEmpty() && optionIds.none { findViewById<CheckBox>(it).isChecked }) {
                Toast.makeText(this, selectionError, Toast.LENGTH_SHORT).show()
            } else if (other?.isChecked == true && input?.text.isNullOrBlank()) {
                input?.error = getString(otherError)
                input?.requestFocus()
            } else {
                onAnswerConfirmed()
            }
        }
    }
}
