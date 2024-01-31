package com.example.rosavtodorproject2.ui.model

import java.util.Date

data class MessageElementModel(
    val id: Int,
    val userSenderId: Int,
    val userSenderName: String,
    val userRecieverId: Int,
    val text: String,
    val sendDate: Date,
)