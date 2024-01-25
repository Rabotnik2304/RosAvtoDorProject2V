package com.example.rosavtodorproject2.ui.model

import java.util.Date

data class ChatElementModel(
    val id: Int,
    val userPictureResourcesId: Int,
    val userName: String,
    val userLastMessage: String,
    val userLastMessageDate: Date,
)