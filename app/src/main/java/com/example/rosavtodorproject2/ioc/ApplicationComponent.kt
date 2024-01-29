package com.example.rosavtodorproject2.ioc

import com.example.rosavtodorproject2.data.dataSource.DataSourceHardCode
import com.example.rosavtodorproject2.data.repositories.MessagesRepository
import com.example.rosavtodorproject2.domain.useCases.MessageWithUserSenderUseCase
import com.example.rosavtodorproject2.domain.useCases.UserWithLastMessageUseCase
import dagger.Provides
import javax.inject.Scope

@Scope
annotation class AppComponentScope
@dagger.Component(modules = [DataModule ::class,ChatsViewModelModule::class])
@AppComponentScope
interface ApplicationComponent {
    fun getUserWithLastMessageUseCase() : UserWithLastMessageUseCase
    fun getMessageWithUserSenderUseCase(): MessageWithUserSenderUseCase
    fun getChatsViewModelFactory(): ChatsViewModelFactory
    fun getConversationViewModelFactory(): ConversationViewModelFactory
}
@dagger.Module
object DataModule{
    @Provides
    @AppComponentScope
    fun getDataSource() = DataSourceHardCode()
}

@dagger.Module
object ChatsViewModelModule{
    @Provides
    @AppComponentScope
    fun getChatsViewModelFactory(userWithLastMessageUseCase: UserWithLastMessageUseCase): ChatsViewModelFactory{
        return ChatsViewModelFactory(userWithLastMessageUseCase)
    }
}
@dagger.Module
object ConversationViewModelModule{
    @Provides
    @AppComponentScope
    fun getConversationViewModelFactory(messagesRepository:MessagesRepository,messageWithUserSenderUseCase: MessageWithUserSenderUseCase): ConversationViewModelFactory{
        return ConversationViewModelFactory(messagesRepository,messageWithUserSenderUseCase)
    }
}
