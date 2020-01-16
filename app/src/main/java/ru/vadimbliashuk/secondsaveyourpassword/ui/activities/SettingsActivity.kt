package ru.vadimbliashuk.secondsaveyourpassword.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_setting.*
import ru.vadimbliashuk.secondsaveyourpassword.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        change_pin_layout.setOnClickListener {
            val intent = Intent(this, ChangePinCode::class.java)
            startActivity(intent)
        }
    }
}
