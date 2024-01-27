package com.example.rosavtodorproject2.data.models

import java.util.Date

data class Message(
    val id: Int,
    val userSenderId: Int,
    val userRecieverId: Int,
    val text: String,
    val sendDate: Date,
    )