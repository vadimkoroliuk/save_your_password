package ru.vadimbliashuk.secondsaveyourpassword.ui.fragment.add_new_login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.add_new_login_fragment.*
import ru.vadimbliashuk.secondsaveyourpassword.R
import ru.vadimbliashuk.secondsaveyourpassword.data.UserViewModel
import ru.vadimbliashuk.secondsaveyourpassword.extention.hideKeyboard
import ru.vadimbliashuk.secondsaveyourpassword.extention.replaceFragment
import ru.vadimbliashuk.secondsaveyourpassword.ui.activities.AllLoginsActivity
import ru.vadimbliashuk.secondsaveyourpassword.ui.fragment.list_of_items.ListOfItemsFragment


class AddNewLoginFragment : Fragment(), AllLoginsActivity.OnBackPressedListener {


    companion object {
        fun newInstance() = AddNewLoginFragment()
    }

    private lateinit var viewModel: AddNewLoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_new_login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar_new_login.title = "Add new login"
        toolbar_new_login.setNavigationIcon(R.drawable.ic_back)
        toolbar_new_login.setNavigationOnClickListener {
            replaceFragment(ListOfItemsFragment())
        }

        (activity as AllLoginsActivity?)!!.setOnBackPressedListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddNewLoginViewModel::class.java)

        btn_save.setOnClickListener {

            hideKeyboard()

            when {
                tiet_website.text.toString().isEmpty() -> tiet_website.error =
                    "Please insert Website"
                tiet_login.text.toString().isEmpty() -> tiet_login.error =
                    "Please insert Login"
                tiet_password.text.toString().isEmpty() -> tiet_password.error =
                    "Please insert Password"
                else -> {
                    Snackbar.make(it, "Record successfully added ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()

                    viewModel.insert(
                        tiet_website.text.toString(),
                        tiet_login.text.toString(),
                        tiet_password.text.toString()
                    )
                    replaceFragment(ListOfItemsFragment())
                }
            }
        }
    }

    override fun doBack() {
        replaceFragment(ListOfItemsFragment())
    }
}
