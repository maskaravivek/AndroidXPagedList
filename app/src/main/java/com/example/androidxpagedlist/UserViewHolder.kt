package com.example.androidxpagedlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidxpagedlist.db.User

class UserViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_user, parent, false)) {
    private var tvUser: TextView? = null


    init {
        tvUser = itemView.findViewById(R.id.name_tv)
    }

    fun bind(user: User?) {
        tvUser?.text = user?.firstName
    }
}