package com.example.rosavtodorproject2.data.dataSource

import com.example.rosavtodorproject2.data.models.Message
import com.example.rosavtodorproject2.R
import com.example.rosavtodorproject2.data.models.User
import java.util.Date

class DataSourceHardCode {
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
            "Рос автодор Бот",
            R.drawable.ros_avto_bot_avatar
        ),
        User(
            1,
            "Оператор",
            R.drawable.empty_person_avatar
        )
    )
    private val messages: MutableList<Message> = mutableListOf(
        Message(
            0,
            0,
            -1,
            "Здравствуйте, меня зовут, а кто я?",
            Date(124, 0, 23)
        ),
        Message(
            1,
            0,
            -1,
            "До Свидания, меня не зовут, я сам ухожу",
            Date(124, 0, 22)
        ),
        Message(
            2,
            0,
            -1,
            "Я прибыл туда, куда прибыл.",
            Date(123, 11, 31)
        ),
        Message(
            3,
            -1,
            1,
            "Не придумал шутку.",
            Date(124, 0, 27)
        ),
        Message(
            4,
            1,
            -1,
            "Здравствуйте, меня зовут Оператор",
            Date(124, 0, 22)
        ),
        Message(
            5,
            1,
            -1,
            "Меня зовут [ДАННЫЕ УДАЛЕНЫ]",
            Date(122, 0, 31)
        ),
    )

    fun loadUserContacts() = userContacts
    fun loadMessages() = messages
}