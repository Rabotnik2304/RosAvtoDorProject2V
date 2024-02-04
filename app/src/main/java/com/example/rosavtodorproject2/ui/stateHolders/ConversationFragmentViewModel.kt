package com.example.rosavtodorproject2.ui.stateHolders

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.rosavtodorproject2.data.models.User
import com.example.rosavtodorproject2.data.repositories.MessagesRepository
import com.example.rosavtodorproject2.data.repositories.UserRepository
import com.example.rosavtodorproject2.domain.model.MessageWithUserSender
import com.example.rosavtodorproject2.domain.useCases.MessageWithUserSenderUseCase
import com.example.rosavtodorproject2.ui.model.MessageElementModel
import java.util.Calendar
import java.util.Date
import java.util.TimeZone
import javax.inject.Inject

class ConversationFragmentViewModel @Inject constructor(
    val messagesRepository: MessagesRepository,
    val userRepository:UserRepository,
    val messageWithUserSenderUseCase: MessageWithUserSenderUseCase,
) : ViewModel() {
    val messages: LiveData<List<MessageElementModel>> =
        messageWithUserSenderUseCase.messageWithUserSender.map {
            it.map { messageWithUserSender -> messageWithUserSender.transformToItemModel() }
        }

    init {
        updateUsersAndMessages()
    }

    fun updateUsersAndMessages() {
        messageWithUserSenderUseCase.updateUsersAndMessages()
    }

    fun sendMessage(userRecieverId:Int, text :String, sendDate: Date) {
        messagesRepository.addMessage(
            userSenderId = userRepository.currentUser.value!!.id,
            userRecieverId = userRecieverId,
            text = text,
            sendDate = sendDate
        )
    }

    private fun MessageWithUserSender.transformToItemModel() = MessageElementModel(
        id = message.id,
        userSenderId = message.userSenderId,
        userSenderName = user.name,
        userRecieverId = message.userRecieverId,
        text = message.text,
        sendDate = message.sendDate,
    )
}