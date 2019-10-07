package com.example.chattychat.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chattychat.R
import com.example.chattychat.models.Message

class MessageAdapter(val context: Context, val messages: ArrayList<Message>) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindModel(messages[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.message, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return messages.count()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val msg: TextView = itemView.findViewById(R.id.tv_msg_body)
        val userName: TextView = itemView.findViewById(R.id.tv_user_name)
        //        val userAvatar: ImageView = itemView.findViewById(R.id.iv_user_avatar)
        val msgDate: TextView = itemView.findViewById(R.id.tv_msg_date)

        fun bindModel(message: Message) {
            msg.text = message.messageBody
            userName.text = message.userName
            msgDate.text = message.timeStamp
        }
    }

}