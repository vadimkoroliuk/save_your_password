package ru.vadimbliashuk.secondsaveyourpassword.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_pin_code.*
import kotlinx.android.synthetic.main.activity_change_pin.*
import ru.vadimbliashuk.secondsaveyourpassword.R

class ChangePinCode : AppCompatActivity() {

    private var EMPTY = ""
    private var ERROR_INPUT_EMPTY = "Please fill all fields"
    private var SAVED = "Saved!"
    private var myPreferences = "myPrefs"
    private var PHONE_NUMBER = "phoneNumber"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pin)

        val sharedPreferences = getSharedPreferences(myPreferences, Context.MODE_PRIVATE)

        button_change_pw.setOnClickListener {

            if (et_change_password.text.toString() == EMPTY
                || et_change_password_2.text.toString() == EMPTY
            ) {
                Toast.makeText(applicationContext, ERROR_INPUT_EMPTY, Toast.LENGTH_LONG).show()

            } else if (et_change_password.text.toString() != et_change_password_2.text.toString()) {
                Toast.makeText(
                    applicationContext,
                    "Passwords do not match!!! ",
                    Toast.LENGTH_LONG
                ).show()
            } else if (et_create_password.text.toString().length < 4) {
                Toast.makeText(
                    applicationContext,
                    "Passwords is too short ",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val editor = sharedPreferences.edit()

                editor.putString(PHONE_NUMBER, et_change_password.text.toString())
                editor.apply()

                Toast.makeText(applicationContext, SAVED, Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}