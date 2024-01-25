package com.example.rosavtodorproject2.data.models

import java.util.Date

data class Message(
    val id: Int,
    val userId: Int,
    val text: String,
    val date: Date,
    )