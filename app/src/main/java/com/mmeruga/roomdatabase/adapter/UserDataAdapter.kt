package com.mmeruga.roomdatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mmeruga.roomdatabase.R
import com.mmeruga.roomdatabase.database.User
import kotlinx.android.synthetic.main.list_item.view.*

class UserDataAdapter: RecyclerView.Adapter<UserDataAdapter.ViewHolder>() {

    private var userList = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.textId.text = currentItem.id.toString()
        holder.itemView.textName.text = currentItem.name
        holder.itemView.textEmail.text = currentItem.email
        holder.itemView.rowLayout.setOnClickListener(View.OnClickListener {
            // click event
        })
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setUser(user: List<User>) {
        userList = user
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}