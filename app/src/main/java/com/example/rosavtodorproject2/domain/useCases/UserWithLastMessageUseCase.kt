package com.example.rosavtodorproject2.domain.useCases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.rosavtodorproject2.data.models.Message
import com.example.rosavtodorproject2.data.repositories.MessagesRepository
import com.example.rosavtodorproject2.data.repositories.UserRepository
import com.example.rosavtodorproject2.domain.model.UserWithLastMessage
import javax.inject.Inject

class UserWithLastMessageUseCase @Inject constructor(
    private val messageRepository: MessagesRepository,
    private val userRepository: UserRepository,
) {

    private val _userWithLastMessage = MediatorLiveData<List<UserWithLastMessage>>()

    val userWithLastMessage: LiveData<List<UserWithLastMessage>> = _userWithLastMessage

    init {
        _userWithLastMessage.addSource(userRepository.currentUser) {
            updateUserWithLastMessage()
        }
        _userWithLastMessage.addSource(userRepository.userContacts) {
            updateUserWithLastMessage()
        }
        _userWithLastMessage.addSource(messageRepository.messages) {
            updateUserWithLastMessage()
        }

    }

    fun updateUsersAndMessages() {
        userRepository.updateCurrentUser()
        userRepository.updateUsers()
        messageRepository.updateMessages()
    }

    private fun updateUserWithLastMessage() {
        val conversationIdAndMessages: Map<Int, List<Message>> =
            messageRepository.messages.value.orEmpty()
                .groupBy { if (it.userSenderId == userRepository.currentUser.value?.id) it.userRecieverId else it.userSenderId }

        val conversationIdAndLastMessage: Map<Int, Message> =
            conversationIdAndMessages.mapValues { it.value.maxBy { message -> message.sendDate } }

        val result: List<UserWithLastMessage> = conversationIdAndLastMessage.map {
            UserWithLastMessage(
                userRepository.userContacts.value.orEmpty()[it.key],
                it.value,
                if (it.value.userSenderId == userRepository.currentUser.value?.id) "Вы:" else userRepository.userContacts.value.orEmpty()[it.value.userSenderId].name + ":"
            )
        }

        _userWithLastMessage.value = result
    }
}