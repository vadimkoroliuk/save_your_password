package ru.vadimbliashuk.secondsaveyourpassword.ui.fragment.list_of_items

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ru.vadimbliashuk.secondsaveyourpassword.R

class ListOfItemsFragment : Fragment() {

    companion object {
        fun newInstance() =
            ListOfItemsFragment()
    }

    private lateinit var viewModel: ListOfItemsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_of_items_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListOfItemsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
