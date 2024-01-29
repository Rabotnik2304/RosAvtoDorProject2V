package com.example.rosavtodorproject2.ui.stateHolder

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.rosavtodorproject2.data.repositories.MessagesRepository
import com.example.rosavtodorproject2.domain.model.MessageWithUserSender
import com.example.rosavtodorproject2.domain.model.UserWithLastMessage
import com.example.rosavtodorproject2.domain.useCases.MessageWithUserSenderUseCase
import com.example.rosavtodorproject2.ui.model.ChatElementModel
import com.example.rosavtodorproject2.ui.model.MessageElementModel
import java.util.Date
import javax.inject.Inject

class ConversationFragmentViewModel @Inject constructor(
    val messagesRepository: MessagesRepository,
    val messageWithUserSenderUseCase: MessageWithUserSenderUseCase,
): ViewModel() {
    val messages: LiveData<List<MessageElementModel>> = messageWithUserSenderUseCase.messageWithUserSender.map {
        it.map { messageWithUserSender -> messageWithUserSender.transformToItemModel()}
    }

    init {
        updateUsersAndLastMessages()
    }

    fun updateUsersAndLastMessages(){
        messageWithUserSenderUseCase.updateUsersAndMessages()
    }

    fun sendMessage(message:MessageElementModel){
        messagesRepository.addMessage(
            id=message.id,
            userSenderId = message.userSenderId,
            userRecieverId = message.userRecieverId,
            text = message.text,
            sendDate = message.sendDate
        )
    }
    private fun MessageWithUserSender.transformToItemModel() = MessageElementModel(
        id = message.id,
        userSenderId =message.userSenderId,
        userSenderName =user.name,
        userRecieverId =message.userRecieverId,
        text = message.text,
        sendDate = message.sendDate,
    )
}