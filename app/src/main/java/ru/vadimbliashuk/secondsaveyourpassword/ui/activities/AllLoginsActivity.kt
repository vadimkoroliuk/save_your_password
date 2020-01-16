package ru.vadimbliashuk.secondsaveyourpassword.ui.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import ru.vadimbliashuk.secondsaveyourpassword.R
import ru.vadimbliashuk.secondsaveyourpassword.ui.fragment.list_of_items.ListOfItemsFragment


class AllLoginsActivity : AppCompatActivity() {

    private var onBackPressedListener: OnBackPressedListener? = null

    interface OnBackPressedListener {
        fun doBack()
    }

    fun setOnBackPressedListener(onBackPressedListener: OnBackPressedListener?) {
        this.onBackPressedListener = onBackPressedListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_logins)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
        replaceFragment(ListOfItemsFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onDestroy() {
        onBackPressedListener = null
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (onBackPressedListener != null) {
            onBackPressedListener!!.doBack()
        } else {
            super.onBackPressed()
        }
    }

}

