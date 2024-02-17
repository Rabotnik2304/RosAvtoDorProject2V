package com.example.rosavtodorproject2.ioc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rosavtodorproject2.data.repositories.UserRepository
import com.example.rosavtodorproject2.domain.useCases.UserWithLastMessageUseCase
import com.example.rosavtodorproject2.domain.useCases.UsersUseCase
import com.example.rosavtodorproject2.ui.view.chatsFragment.ChatsFragmentViewModel
import javax.inject.Inject

class ChatsViewModelFactory @Inject constructor(
    val usersUseCase: UsersUseCase,
    val userWithLastMessageUseCase: UserWithLastMessageUseCase,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChatsFragmentViewModel(
            usersUseCase = usersUseCase,
            userWithLastMessageUseCase = userWithLastMessageUseCase,
        ) as T
    }
}