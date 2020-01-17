package ru.vadimbliashuk.secondsaveyourpassword.extention

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import ru.vadimbliashuk.secondsaveyourpassword.R

fun Fragment.hideKeyboard() {
    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view!!.windowToken, 0)
}

fun Fragment.replaceFragment(fragment: Fragment) {
    val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
    transaction.replace(R.id.fragment_container, fragment)
    transaction.commit()
}

@Suppress("unused")
fun Any.toast(context: Context, message: String): Toast {
    return Toast.makeText(
        context, message, Toast.LENGTH_LONG
    ).apply { show() }
}