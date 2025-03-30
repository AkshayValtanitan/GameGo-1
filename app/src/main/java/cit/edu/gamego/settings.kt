package cit.edu.gamego

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class settings : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        if (ThemePreferences.isDarkMode(this)) {
            setTheme(R.style.Theme_GameGo)
        } else {
            setTheme(R.style.Theme_Dark)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val appearanceButton = findViewById<LinearLayout>(R.id.appearance)
        appearanceButton.setOnClickListener {
            val isDarkMode = !ThemePreferences.isDarkMode(this) // toggle theme
            ThemePreferences.saveTheme(this, isDarkMode)

            recreate()
        }

        val back = findViewById<ImageView>(R.id.settings_back_Id);

        back.setOnClickListener{
            startActivity(
                Intent(this,activity_landing::class.java)
            )
        }


    }
}