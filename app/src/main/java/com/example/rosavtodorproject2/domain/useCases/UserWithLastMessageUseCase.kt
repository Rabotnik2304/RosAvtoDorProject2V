package com.example.rosavtodorproject2.domain.useCases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.rosavtodorproject2.data.models.Message
import com.example.rosavtodorproject2.data.repositories.MessagesRepository
import com.example.rosavtodorproject2.data.repositories.UserRepository
import com.example.rosavtodorproject2.domain.model.UserWithLastMessage
import javax.inject.Inject

class  UserWithLastMessageUseCase @Inject constructor(
    private val messageRepository: MessagesRepository,
    private val userRepository: UserRepository,
) {

    private val _userWithLastMessage = MediatorLiveData<List<UserWithLastMessage>>()

    val userWithLastMessage:LiveData<List<UserWithLastMessage>> = _userWithLastMessage

    init {
        _userWithLastMessage.addSource(userRepository.users){
            updateUserWithLastMessage()
        }
        _userWithLastMessage.addSource(messageRepository.messages){
            updateUserWithLastMessage()
        }

    }
    fun updateUsersAndMessages(){
        messageRepository.updateMessages()
        userRepository.updateUsers()
    }
    private fun updateUserWithLastMessage(){
        val userIdAndMessage: Map<Int,List<Message>> = messageRepository.messages.value.orEmpty().groupBy { it.userId }
        val userIdAndLastMessage : Map<Int,Message> = userIdAndMessage.mapValues { it.value.maxBy { message -> message.date}}

        val result:List<UserWithLastMessage> = userIdAndLastMessage.map{
            UserWithLastMessage(
                userRepository.users.value.orEmpty()[it.key],
                it.value
            )
        }

        _userWithLastMessage.value = result
    }
}