package com.example.rosavtodorproject2.ui.stateHolders

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.rosavtodorproject2.data.models.User
import com.example.rosavtodorproject2.data.repositories.UserRepository

import com.example.rosavtodorproject2.domain.model.UserWithLastMessage
import com.example.rosavtodorproject2.domain.useCases.UserWithLastMessageUseCase
import com.example.rosavtodorproject2.ui.model.ChatElementModel
import com.example.rosavtodorproject2.ui.view.ChatsWindow.NewCurrentUserNameReciever
import javax.inject.Inject

class ChatsFragmentViewModel @Inject constructor(
    val userRepository: UserRepository,
    val userWithLastMessageUseCase: UserWithLastMessageUseCase,
) : ViewModel() {
    val currentUser: LiveData<User> = userRepository.currentUser
    val chats: LiveData<List<ChatElementModel>> = userWithLastMessageUseCase.userWithLastMessage.map {
        it.map { userWithLastMessage -> userWithLastMessage.transformToItemModel() }
    }

    init {
        updateUsersAndLastMessages()
    }
    //Я оставляю здесь отдельный метод, чтобы в будущем добавить SwipeToRefresh, к списку чатов
    fun updateUsersAndLastMessages() {
        userWithLastMessageUseCase.updateUsersAndMessages()
    }
    fun setNewCurrentUserName(newCurrentUserName:String){
        userRepository.setNewNameToCurrentUser(newCurrentUserName)
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