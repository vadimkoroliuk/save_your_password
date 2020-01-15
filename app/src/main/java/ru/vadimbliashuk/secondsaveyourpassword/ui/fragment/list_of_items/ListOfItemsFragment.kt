package ru.vadimbliashuk.secondsaveyourpassword.ui.fragment.list_of_items

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_update.*
import kotlinx.android.synthetic.main.content_all_logins.*
import kotlinx.android.synthetic.main.list_of_items_fragment.*
import ru.vadimbliashuk.secondsaveyourpassword.R
import ru.vadimbliashuk.secondsaveyourpassword.adapter.UserListAdapter
import ru.vadimbliashuk.secondsaveyourpassword.adapter.rv_listener.RecyclerItemClickListener
import ru.vadimbliashuk.secondsaveyourpassword.adapter.rv_listener.SwipeToDeleteCallback
import ru.vadimbliashuk.secondsaveyourpassword.data.UserViewModel
import ru.vadimbliashuk.secondsaveyourpassword.models.UserEntity
import ru.vadimbliashuk.secondsaveyourpassword.ui.activities.AddNewLogin
import ru.vadimbliashuk.secondsaveyourpassword.ui.activities.SettingsActivity

class ListOfItemsFragment : Fragment(),
    RecyclerItemClickListener.OnRecyclerClickListener {

    private lateinit var vm: UserViewModel
    //    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserListAdapter

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // need to set the icon here to have a navigation icon. You can simple create an vector image by "Vector Asset" and using here
        // toolbar.setNavigationIcon(R.drawable.ic_back)
        // toolbar.setNavigationOnClickListener {
        // do something when click navigation
        // }
        toolbar.title = "Items"
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_setting -> {
                    // do something
                    val intent = Intent(activity, SettingsActivity::class.java)
                    startActivity(intent)

                    true
                }
//                R.id.action_update -> {
//                    // do something
//                    true
//                }
                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        viewModel = ViewModelProviders.of(this).get(ListOfItemsViewModel::class.java)


        fab.setOnClickListener {
            val intent = Intent(activity, AddNewLogin::class.java)
            startActivity(intent)
        }

        vm = ViewModelProviders.of(this).get(UserViewModel::class.java)

//        recyclerView = findViewById(R.id.recycler_view)
//        recyclerView = fragmentManager.findFragmentById(R.id.recycler_view)
        adapter = UserListAdapter(context!!)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(context!!)
        recycler_view.addOnItemTouchListener(
            RecyclerItemClickListener(
                context!!,
                recycler_view,
                this
            )
        )

        val swipeHandler = object : SwipeToDeleteCallback(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //   val adapter = recyclerView.adapter as UserListAdapter

                val user: UserEntity = adapter.getUserAtPosition(viewHolder.adapterPosition)
                vm.delete(user)

            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recycler_view)


        vm.allUsers.observe(this, Observer { users ->
            users.let {
                adapter.setUsers(it)
            }
        })

    }
//    override fun onItemClick(view: View, position: Int) {
//        Log.d("inItemClick", "ShortClick")
//    }

    @SuppressLint("InflateParams")
    override fun onItemLongClick(view: View, position: Int) {

        val mDialogView = LayoutInflater.from(context!!).inflate(R.layout.activity_update, null)
        val mBuilder = AlertDialog.Builder(context!!)
            .setView(mDialogView)
            .setTitle("Update Form")
        val mAlertDialog = mBuilder.show()

        val user = adapter.getUserAtPosition(position)
        mAlertDialog.tv_update_website.text = user.website
        mAlertDialog.et_update_login.setText(user.login)
        mAlertDialog.et_update_pw_act.setText(user.password)


        mAlertDialog.btn_update.setOnClickListener {
            mAlertDialog.dismiss()
            user.login = mAlertDialog.et_update_login.text.toString()
            user.password = mAlertDialog.et_update_pw_act.text.toString()
            vm.update(user)
        }
    }
}
