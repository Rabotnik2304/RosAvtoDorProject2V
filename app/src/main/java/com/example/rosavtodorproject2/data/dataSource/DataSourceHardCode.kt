package com.example.rosavtodorproject2.data.dataSource

import com.example.rosavtodorproject2.data.models.Message
import com.example.rosavtodorproject2.R
import com.example.rosavtodorproject2.data.models.User
import java.util.Date

class DataSourceHardCode {
    private val users:List<User> = listOf(
        User(
            0,
            "Рос автодор Бот",
            R.drawable.ros_avto_bot_avatar
        ),
        User(
            1,
            "Оператор",
            R.drawable.empty_person_avatar
        ),
    )
    private val messages:List<Message> = listOf(
        Message(
            0,
            0,
            "Здравствуйте, меня зовут, а кто я?",
            Date(2024,1,24)
        ),
        Message(
            1,
            0,
            "До Свидания, меня не зовут, я сам ухожу",
            Date(2024,1,22)
        ),
        Message(
            2,
            0,
            "Я мухожук.",
            Date(2023,12,31)
        ),
        Message(
            3,
            1,
            "Не придумал мем.",
            Date(2024,1,24)
        ),
        Message(
            4,
            1,
            "Здравствуйте, меня зовут Зубенко Михаил Петрович",
            Date(2024,1,22)
        ),
        Message(
            5,
            1,
            "Меня зовут [ДАННЫЕ УДАЛЕНЫ ПО ПРИЧИНЕ МЕМЕТИЧЕСКОЙ УГРОЗЫ КЛАССА 3]",
            Date(2025,1,31)
        ),
    )

    fun loadUsers() = users
    fun loadMessages() = messages
}