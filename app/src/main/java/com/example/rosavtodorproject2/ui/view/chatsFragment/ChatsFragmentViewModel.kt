package com.example.rosavtodorproject2.ui.view.chatsFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.rosavtodorproject2.data.models.User
import com.example.rosavtodorproject2.data.repositories.UserRepository

import com.example.rosavtodorproject2.domain.model.UserWithLastMessage
import com.example.rosavtodorproject2.domain.useCases.UserWithLastMessageUseCase
import com.example.rosavtodorproject2.domain.useCases.UsersUseCase
import com.example.rosavtodorproject2.ui.model.ChatElementModel
import javax.inject.Inject

class ChatsFragmentViewModel @Inject constructor(
    val usersUseCase: UsersUseCase,
    val userWithLastMessageUseCase: UserWithLastMessageUseCase,
) : ViewModel() {
    val currentUser: LiveData<User> = usersUseCase.currentUser
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
        usersUseCase.setNewNameToCurrentUser(newCurrentUserName)
    }
    private fun UserWithLastMessage.transformToItemModel() = ChatElementModel(
        id = user.id,
        userPictureResourcesId = user.userPictureResourcesId,
        userName = user.name,
        userLastMessage = "$senderName ${lastMessage.text}",
        userLastMessageDate = lastMessage.sendDate
    )
}