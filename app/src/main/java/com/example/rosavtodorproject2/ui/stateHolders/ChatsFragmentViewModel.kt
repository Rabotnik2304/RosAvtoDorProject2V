package com.example.rosavtodorproject2.ui.stateHolders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

import com.example.rosavtodorproject2.domain.model.UserWithLastMessage
import com.example.rosavtodorproject2.domain.useCases.UserWithLastMessageUseCase
import com.example.rosavtodorproject2.ui.model.ChatElementModel
import javax.inject.Inject

class ChatsFragmentViewModel @Inject constructor(
    val userWithLastMessageUseCase: UserWithLastMessageUseCase,
) : ViewModel() {
    val chats = userWithLastMessageUseCase.userWithLastMessage.map {
        it.map { userWithLastMessage -> userWithLastMessage.transformToItemModel() }
    }

    init {
        updateUsersAndLastMessages()
    }

    fun updateUsersAndLastMessages() {
        userWithLastMessageUseCase.updateUsersAndMessages()
    }

    private fun UserWithLastMessage.transformToItemModel() = ChatElementModel(
        id = user.id,
        userSenderName = senderName,
        userPictureResourcesId = user.userPictureResourcesId,
        userName = user.name,
        userLastMessage = lastMessage.text,
        userLastMessageDate = lastMessage.sendDate
    )
}