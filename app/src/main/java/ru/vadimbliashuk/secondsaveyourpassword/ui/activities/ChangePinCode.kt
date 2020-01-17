package ru.vadimbliashuk.secondsaveyourpassword.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_change_pin.*
import ru.vadimbliashuk.secondsaveyourpassword.R
import ru.vadimbliashuk.secondsaveyourpassword.extention.toast

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
                toast(applicationContext, ERROR_INPUT_EMPTY)
            } else if (et_change_password.text.toString() != et_change_password_2.text.toString()) {
                toast(applicationContext, "Passwords do not match!!!")
            } else if (et_change_password.text.toString().length < 4) {
                toast(applicationContext, "Password is too short ")
            } else {
                val editor = sharedPreferences.edit()
                editor.putString(PHONE_NUMBER, et_change_password.text.toString())
                editor.apply()

                toast(applicationContext, SAVED)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}