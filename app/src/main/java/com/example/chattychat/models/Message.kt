package com.example.chattychat.models

class Message(
        val userName: String,
        val messageBody: String,
        val userId: String,
        val userAvatar: String,
        val text: String,
        val timeStamp: String) {
    val _id: String? = null
}