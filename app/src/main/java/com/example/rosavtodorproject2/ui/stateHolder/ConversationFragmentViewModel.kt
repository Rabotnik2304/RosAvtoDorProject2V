package com.example.rosavtodorproject2.ui.stateHolder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.rosavtodorproject2.data.repositories.MessagesRepository
import com.example.rosavtodorproject2.domain.model.UserWithLastMessage
import com.example.rosavtodorproject2.domain.useCases.UserWithLastMessageUseCase
import com.example.rosavtodorproject2.ui.model.ChatElementModel
import javax.inject.Inject

class ConversationFragmentViewModel @Inject constructor(
    val messagesRepository: MessagesRepository,
): ViewModel() {
    val messages = messagesRepository.messages

    init {
        updateUsersAndLastMessages()
    }

    fun updateUsersAndLastMessages(){
        messagesRepository.updateMessages()
    }
}