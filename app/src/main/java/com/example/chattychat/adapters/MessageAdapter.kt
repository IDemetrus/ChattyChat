package com.example.chattychat.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chattychat.R
import com.example.chattychat.models.Message

class MessageAdapter(context: Context, val messages: List<Message>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // TODO fix this method
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val msg: TextView = itemView.findViewById(R.id.tv_msg_body)
        val userName: TextView = itemView.findViewById(R.id.tv_user_name)
//        val userAvatar: ImageView = itemView.findViewById(R.id.iv_user_avatar)
        val msgDate: TextView = itemView.findViewById(R.id.tv_msg_date)

        private fun bindModel(message: Message) {
            msg.text = message.text
            userName.text = message.userId
            msgDate.text = message.date
        }
    }

}