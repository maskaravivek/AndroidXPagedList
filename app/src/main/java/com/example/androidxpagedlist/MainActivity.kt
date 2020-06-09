package com.example.androidxpagedlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.androidxpagedlist.db.AppDatabase
import com.example.androidxpagedlist.db.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    var appDatabase: AppDatabase? = null
    private var contributionBoundaryCallback: ContributionBoundaryCallback? = null
    var userList: LiveData<PagedList<User>>? = null

    var adapter: UserListAdapter = UserListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
        initRecyclerView()
    }

    fun setup() {
        appDatabase = initDb()
        contributionBoundaryCallback = ContributionBoundaryCallback(
            appDatabase!!.userDao()
        )

        val pagedListConfig = PagedList.Config.Builder()
            .setPrefetchDistance(50)
            .setPageSize(10).build()

        userList = LivePagedListBuilder(
            appDatabase!!.userDao().fetchUsers(),
            pagedListConfig
        ).setBoundaryCallback(contributionBoundaryCallback).build()
    }

    fun initDb(): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "user-db"
        ).build()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        userRecyclerView.setLayoutManager(layoutManager)
        userList!!.observe(this, Observer { pagedList ->
            adapter.submitList(pagedList)
        })
        userRecyclerView.setAdapter(adapter)
    }
}