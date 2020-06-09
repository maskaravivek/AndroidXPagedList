package com.example.androidxpagedlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.androidxpagedlist.db.User

class UserListAdapter internal constructor() :
    PagedListAdapter<User, UserViewHolder>(DIFF_CALLBACK) {
    /**
     * Initializes the view holder with contribution data
     */
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * Creates the new View Holder which will be used to display items(contributions) using the
     * onBindViewHolder(viewHolder,position)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserViewHolder(inflater, parent)
    }

    companion object {
        /**
         * Uses DiffUtil to calculate the changes in the list
         * It has methods that check ID and the content of the items to determine if its a new item
         */
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<User> =
            object : DiffUtil.ItemCallback<User>() {
                override fun areItemsTheSame(
                    oldContribution: User,
                    newContribution: User
                ): Boolean {
                    return oldContribution == newContribution
                }

                override fun areContentsTheSame(
                    oldContribution: User,
                    newContribution: User
                ): Boolean {
                    return oldContribution == newContribution
                }
            }
    }
}