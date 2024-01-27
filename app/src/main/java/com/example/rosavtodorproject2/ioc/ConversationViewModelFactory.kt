package com.example.rosavtodorproject2.ioc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rosavtodorproject2.data.repositories.MessagesRepository
import com.example.rosavtodorproject2.ui.stateHolder.ConversationFragmentViewModel
import javax.inject.Inject

class ConversationViewModelFactory @Inject constructor(
    val messagesRepository: MessagesRepository
) : ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ConversationFragmentViewModel(
            messagesRepository=messagesRepository
        ) as T
    }
}