package ru.vadimbliashuk.secondsaveyourpassword.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_add_new_login.*
import ru.vadimbliashuk.secondsaveyourpassword.R
import ru.vadimbliashuk.secondsaveyourpassword.data.UserViewModel
import ru.vadimbliashuk.secondsaveyourpassword.models.UserEntity

class AddNewLogin : AppCompatActivity() {

    private lateinit var vm: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_login)


        vm = ViewModelProviders.of(this).get(UserViewModel::class.java)

        btn_save.setOnClickListener {
            when {
                tiet_website.text.toString().isEmpty() -> tiet_website.error =
                    "Please insert Website"
                tiet_login.text.toString().isEmpty() -> tiet_login.error =
                    "Please insert Login"
                tiet_password.text.toString().isEmpty() -> tiet_password.error =
                    "Please insert Password"
                else -> {

                    Toast.makeText(
                        this@AddNewLogin,
                        "Record succeed!",
                        Toast.LENGTH_LONG
                    ).show()


                    val user = UserEntity(
                        tiet_website.text.toString(),
                        tiet_login.text.toString(),
                        tiet_password.text.toString()
                    )
                    vm.insert(user)
                    finish()
                }
            }
        }
    }
}
