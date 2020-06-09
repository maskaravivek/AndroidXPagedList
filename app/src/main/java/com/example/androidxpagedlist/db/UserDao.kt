package com.example.androidxpagedlist.db

import androidx.paging.DataSource
import androidx.room.*
import io.reactivex.Single

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun fetchUsers(): DataSource.Factory<Int, User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query(
        "SELECT * FROM user WHERE first_name LIKE :first AND " +
                "last_name LIKE :last LIMIT 1"
    )
    fun findByName(first: String, last: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(contribution: List<User>): Single<List<Long>>

    @Delete
    fun delete(user: User)
}