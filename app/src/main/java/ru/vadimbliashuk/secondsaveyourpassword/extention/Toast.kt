package ru.vadimbliashuk.secondsaveyourpassword.extention

import android.content.Context
import android.widget.Toast

fun Toast.createToast(context: Context, message: String) {
    Toast.makeText(
        context, message, Toast.LENGTH_LONG
    ).show()
}