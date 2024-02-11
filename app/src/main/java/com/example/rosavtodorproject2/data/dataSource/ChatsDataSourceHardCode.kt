package com.example.rosavtodorproject2.data.dataSource

import com.example.rosavtodorproject2.data.models.Message
import com.example.rosavtodorproject2.R
import com.example.rosavtodorproject2.data.models.User
import java.util.Date

class ChatsDataSourceHardCode {
    companion object {
        val currentUser = User(
            -1,
            "Пользователь",
            R.drawable.empty_person_avatar
        )
    }

    private val userContacts: List<User> = listOf(
        User(
            0,
            "Оператор",
            R.drawable.empty_person_avatar
        )
    )
    private val messages: MutableList<Message> = mutableListOf(
        Message(
            0,
            -1,
            0,
            "Не придумал шутку.",
            Date(124, 0, 27)
        ),
        Message(
            1,
            0,
            -1,
            "Здравствуйте, меня зовут Оператор",
            Date(124, 0, 22)
        ),
        Message(
            2,
            0,
            -1,
            "Меня зовут [ДАННЫЕ УДАЛЕНЫ]",
            Date(122, 0, 31)
        ),
    )

    fun loadUserContacts() = userContacts
    fun loadMessages() = messages
}