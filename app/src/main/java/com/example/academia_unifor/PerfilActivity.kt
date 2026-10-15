package com.example.academia_unifor

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class PerfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_perfil)

        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = false
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_scroll)) { view, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(bars.left, 0, bars.right, bars.bottom)
            insets
        }

        findViewById<ImageButton>(R.id.back_button).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        findViewById<ImageButton>(R.id.button_settings).setOnClickListener {
            Toast.makeText(this, "Configurações", Toast.LENGTH_SHORT).show()
        }

        findViewById<ImageView>(R.id.button_add_avatar).setOnClickListener {
            Toast.makeText(this, "Alterar foto de perfil", Toast.LENGTH_SHORT).show()
        }

        findViewById<ImageView>(R.id.button_add_objetivo).setOnClickListener {
            Toast.makeText(this, "Adicionar objetivo", Toast.LENGTH_SHORT).show()
        }

        findViewById<ImageView>(R.id.button_add_resultado).setOnClickListener {
            Toast.makeText(this, "Adicionar resultado", Toast.LENGTH_SHORT).show()
        }

        findViewById<AppCompatButton>(R.id.button_salvar).setOnClickListener {
            Toast.makeText(this, "Perfil salvo com sucesso!", Toast.LENGTH_SHORT).show()
        }
    }
}
