package ru.vadimbliashuk.secondsaveyourpassword.ui.fragment.add_new_login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import ru.vadimbliashuk.secondsaveyourpassword.R

class AddNewLoginFragment : Fragment() {

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddNewLoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
