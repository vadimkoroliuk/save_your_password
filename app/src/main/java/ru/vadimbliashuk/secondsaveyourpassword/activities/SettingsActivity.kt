package ru.vadimbliashuk.secondsaveyourpassword.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_setting.*
import ru.vadimbliashuk.secondsaveyourpassword.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        tv_settings_update_pin.setOnClickListener {
            val intent = Intent(this, ChangePinCode::class.java)
            startActivity(intent)
        }
    }
}
