package com.example.rosavtodorproject2.domain.useCases

import androidx.lifecycle.LiveData
import com.example.rosavtodorproject2.data.models.Message
import com.example.rosavtodorproject2.data.repositories.MessagesRepository
import java.util.Date
import javax.inject.Inject

class MessagesUseCase @Inject constructor(
    private val messageRepository: MessagesRepository,
) {
    val messages: LiveData<List<Message>> = messageRepository.messages

    fun addMessage(userSenderId: Int, userRecieverId: Int, text: String, sendDate: Date) {
        messageRepository.addMessage(
            userSenderId = userSenderId,
            userRecieverId = userRecieverId,
            text = text,
            sendDate = sendDate,
        )
    }
}