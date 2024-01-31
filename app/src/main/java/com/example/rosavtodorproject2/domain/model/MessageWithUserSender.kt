package com.example.rosavtodorproject2.domain.model

import com.example.rosavtodorproject2.data.models.Message
import com.example.rosavtodorproject2.data.models.User

data class MessageWithUserSender(
    val message: Message,
    val user: User,
)