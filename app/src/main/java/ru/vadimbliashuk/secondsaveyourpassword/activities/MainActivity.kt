package ru.vadimbliashuk.secondsaveyourpassword.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_pin_code.*
import kotlinx.android.synthetic.main.activity_main.*
import ru.vadimbliashuk.secondsaveyourpassword.R

class MainActivity : AppCompatActivity() {

    private var EMPTY = ""
    private var ERROR_INPUT_EMPTY = "Please fill all fields"
    private var SAVED = "Saved!"
    private var myPreferences = "myPrefs"
    private var PHONE_NUMBER = "phoneNumber"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences(myPreferences, Context.MODE_PRIVATE)

        if (sharedPreferences.getString(PHONE_NUMBER, EMPTY) != EMPTY) {
            setContentView(R.layout.activity_main)

            val phoneNumber = sharedPreferences.getString(PHONE_NUMBER, EMPTY)
            var password = 0

            tv_clear.setOnClickListener {
                iv_1.visibility = View.INVISIBLE
                iv_2.visibility = View.INVISIBLE
                iv_3.visibility = View.INVISIBLE
                iv_4.visibility = View.INVISIBLE
                password = 0
            }

            btn_click.setOnClickListener {
                val intent = Intent(this, AllLoginsActivity::class.java)
                startActivity(intent)
                finish()
            }

            val listener = View.OnClickListener { v ->
                val b = v as Button
                password = password * 10 + b.text.toString().toInt()

                when (password.toString().length) {
                    1 -> iv_1.visibility = View.VISIBLE
                    2 -> iv_2.visibility = View.VISIBLE
                    3 -> iv_3.visibility = View.VISIBLE
                    4 -> {
                        iv_4.visibility = View.VISIBLE
                        if (password.toString() == phoneNumber) {
                            val intent = Intent(this, AllLoginsActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Password wrong", Toast.LENGTH_LONG).show()
                            iv_1.visibility = View.INVISIBLE
                            iv_2.visibility = View.INVISIBLE
                            iv_3.visibility = View.INVISIBLE
                            iv_4.visibility = View.INVISIBLE
                            password = 0
                        }
                    }
                }
            }

            button0.setOnClickListener(listener)
            button1.setOnClickListener(listener)
            button2.setOnClickListener(listener)
            button3.setOnClickListener(listener)
            button4.setOnClickListener(listener)
            button5.setOnClickListener(listener)
            button6.setOnClickListener(listener)
            button7.setOnClickListener(listener)
            button8.setOnClickListener(listener)
            button9.setOnClickListener(listener)

        } else {
            //If the user details are not saved previously then display the form
            setContentView(R.layout.activity_add_pin_code)

            button_add_pw.setOnClickListener {
                //If the user has left any fields empty, show Toast message
                if (et_create_password.text.toString() == EMPTY
                    || et_create_password_2.text.toString() == EMPTY
                ) {
                    Toast.makeText(applicationContext, ERROR_INPUT_EMPTY, Toast.LENGTH_LONG).show()

                } else if (et_create_password.text.toString() != et_create_password_2.text.toString()) {
                    Toast.makeText(
                        applicationContext,
                        "Passwords do not match!!! ",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    //If all fields are filled then fetch the data and
                    // save the data in Shared Preferences
                    val editor = sharedPreferences.edit()

                    editor.putString(PHONE_NUMBER, et_create_password.text.toString())
                    editor.apply()

                    Toast.makeText(applicationContext, SAVED, Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}