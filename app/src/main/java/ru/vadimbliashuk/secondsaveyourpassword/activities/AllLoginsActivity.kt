package ru.vadimbliashuk.secondsaveyourpassword.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_all_logins.*
import kotlinx.android.synthetic.main.activity_update.*
import kotlinx.android.synthetic.main.recycler_view_item.view.*
import ru.vadimbliashuk.secondsaveyourpassword.R
import ru.vadimbliashuk.secondsaveyourpassword.adapter.UserListAdapter
import ru.vadimbliashuk.secondsaveyourpassword.data.UserViewModel
import ru.vadimbliashuk.secondsaveyourpassword.models.UserEntity
import ru.vadimbliashuk.secondsaveyourpassword.ui.RecyclerItemClickListener
import ru.vadimbliashuk.secondsaveyourpassword.ui.SwipeToDeleteCallback

class AllLoginsActivity : AppCompatActivity(),
    RecyclerItemClickListener.OnRecyclerClickListener {

    private lateinit var vm: UserViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_logins)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            val intent = Intent(this, AddNewLogin::class.java)
            startActivity(intent)
        }

        vm = ViewModelProviders.of(this).get(UserViewModel::class.java)

        recyclerView = findViewById(R.id.recycler_view)
        adapter = UserListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(
                this,
                recyclerView,
                this
            )
        )

        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //   val adapter = recyclerView.adapter as UserListAdapter

                val user: UserEntity = adapter.getUserAtPosition(viewHolder.adapterPosition)
                vm.delete(user)

            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)


        vm.allUsers.observe(this, Observer { users ->
            users.let {
                adapter.setUsers(it)
            }
        })
    }

    override fun onItemClick(view: View, position: Int) {
        Log.d("inItemClick", "ShortClick")
    }

    override fun onItemLongClick(view: View, position: Int) {

        val mDialogView = LayoutInflater.from(this).inflate(R.layout.activity_update, null)
        val mBuilder = AlertDialog.Builder(this)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_setting -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

