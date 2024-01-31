package com.example.rosavtodorproject2.domain.model

import com.example.rosavtodorproject2.data.models.Message
import com.example.rosavtodorproject2.data.models.User

data class UserWithLastMessage(
    val user: User,
    val lastMessage: Message,
    val senderName: String,
)

