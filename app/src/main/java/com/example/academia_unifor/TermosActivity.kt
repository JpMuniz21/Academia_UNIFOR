package com.example.academia_unifor

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class TermosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_termos)

        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = false
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        val statementView = findViewById<TextView>(R.id.text_termos_statement)
        statementView.text = Html.fromHtml(getString(R.string.termos_checkbox_text), Html.FROM_HTML_MODE_LEGACY)

        val checkbox = findViewById<AppCompatCheckBox>(R.id.checkbox_termos)
        findViewById<LinearLayout>(R.id.terms_layout).setOnClickListener {
            checkbox.isChecked = !checkbox.isChecked
        }

        findViewById<ImageButton>(R.id.back_button).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Allow proceeding to height screen
        checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // User agreed, can navigate to next step
                val intent = Intent(this, AlturaActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
