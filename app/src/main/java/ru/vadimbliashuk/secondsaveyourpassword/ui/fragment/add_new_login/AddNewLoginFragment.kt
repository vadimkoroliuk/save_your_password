package ru.vadimbliashuk.secondsaveyourpassword.ui.fragment.add_new_login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.add_new_login_fragment.*
import ru.vadimbliashuk.secondsaveyourpassword.R
import ru.vadimbliashuk.secondsaveyourpassword.data.UserViewModel
import ru.vadimbliashuk.secondsaveyourpassword.extention.hideKeyboard
import ru.vadimbliashuk.secondsaveyourpassword.models.UserEntity
import ru.vadimbliashuk.secondsaveyourpassword.ui.fragment.list_of_items.ListOfItemsFragment

class AddNewLoginFragment : Fragment() {

    companion object {
        fun newInstance() = AddNewLoginFragment()
    }

    private lateinit var viewModel: AddNewLoginViewModel

    private lateinit var vm: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_new_login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar_new_login.title = "Add new login"
        // need to set the icon here to have a navigation icon. You can simple create an vector image by "Vector Asset" and using here
        toolbar_new_login.setNavigationIcon(R.drawable.ic_back)
        // do something when click navigation
        toolbar_new_login.setNavigationOnClickListener {

            replaceFragment(ListOfItemsFragment())

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddNewLoginViewModel::class.java)

        vm = ViewModelProviders.of(this).get(UserViewModel::class.java)

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


                    val user = UserEntity(
                        tiet_website.text.toString(),
                        tiet_login.text.toString(),
                        tiet_password.text.toString()
                    )
                    vm.insert(user)

                    replaceFragment(ListOfItemsFragment())
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }




}
