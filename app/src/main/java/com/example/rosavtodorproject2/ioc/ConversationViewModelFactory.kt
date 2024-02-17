package com.example.rosavtodorproject2.ioc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rosavtodorproject2.data.repositories.MessagesRepository
import com.example.rosavtodorproject2.data.repositories.UserRepository
import com.example.rosavtodorproject2.domain.useCases.MessageWithUserSenderUseCase
import com.example.rosavtodorproject2.ui.viewModels.ConversationFragmentViewModel
import javax.inject.Inject

class ConversationViewModelFactory @Inject constructor(
    val messagesRepository: MessagesRepository,
    val userRepository: UserRepository,
    val messageWithUserSenderUseCase: MessageWithUserSenderUseCase,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ConversationFragmentViewModel(
            messagesRepository = messagesRepository,
            userRepository=userRepository,
            messageWithUserSenderUseCase = messageWithUserSenderUseCase
        ) as T
    }
}