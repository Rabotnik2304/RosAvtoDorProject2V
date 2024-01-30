package com.example.rosavtodorproject2.domain.useCases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.rosavtodorproject2.data.dataSource.DataSourceHardCode
import com.example.rosavtodorproject2.data.models.Message
import com.example.rosavtodorproject2.data.repositories.MessagesRepository
import com.example.rosavtodorproject2.data.repositories.UserRepository
import com.example.rosavtodorproject2.domain.model.MessageWithUserSender
import javax.inject.Inject

class  MessageWithUserSenderUseCase @Inject constructor(
    private val messageRepository: MessagesRepository,
    private val userRepository: UserRepository,
) {

    private val _messageWithUserSender = MediatorLiveData<List<MessageWithUserSender>>()

    val messageWithUserSender: LiveData<List<MessageWithUserSender>> = _messageWithUserSender

    init {
        _messageWithUserSender.addSource(userRepository.userContacts){
            updateMessageWithUserSender()
        }
        _messageWithUserSender.addSource(messageRepository.messages){
            updateMessageWithUserSender()
        }

    }
    fun updateUsersAndMessages(){
        messageRepository.updateMessages()
        userRepository.updateUsers()
    }

    private fun updateMessageWithUserSender(){
        val sortedByTimeMessages: List<Message> = messageRepository.messages.value.orEmpty().sortedBy { it.sendDate }

        val result:List<MessageWithUserSender> = sortedByTimeMessages.map{
            MessageWithUserSender(
                it,
                if (it.userSenderId!= DataSourceHardCode.currentUser.id) userRepository.userContacts.value.orEmpty()[it.userSenderId] else DataSourceHardCode.currentUser,
            )
        }

        _messageWithUserSender.value = result
    }
}