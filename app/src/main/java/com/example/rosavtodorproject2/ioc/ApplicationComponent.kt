package com.example.rosavtodorproject2.ioc

import com.example.rosavtodorproject2.data.dataSource.ChatsDataSourceHardCode
import com.example.rosavtodorproject2.data.dataSource.MapDataSourceHardCode
import com.example.rosavtodorproject2.data.repositories.MapPointsRepository
import com.example.rosavtodorproject2.data.repositories.MessagesRepository
import com.example.rosavtodorproject2.data.repositories.UserRepository
import com.example.rosavtodorproject2.domain.useCases.MessageWithUserSenderUseCase
import com.example.rosavtodorproject2.domain.useCases.UserWithLastMessageUseCase
import dagger.Provides
import javax.inject.Scope

@Scope
annotation class AppComponentScope

@dagger.Component(modules = [DataModule::class, ChatsViewModelModule::class, ConversationViewModelModule::class, InteractiveMapViewModelModule::class])
@AppComponentScope
interface ApplicationComponent {
    fun getUserWithLastMessageUseCase(): UserWithLastMessageUseCase
    fun getMessageWithUserSenderUseCase(): MessageWithUserSenderUseCase

    fun getChatsViewModelFactory(): ChatsViewModelFactory
    fun getConversationViewModelFactory(): ConversationViewModelFactory
    fun getInteractiveMapViewModelFactory(): InteractiveMapViewModelFactory
}

@dagger.Module
object DataModule {
    @Provides
    @AppComponentScope
    fun getChatsDataSource() = ChatsDataSourceHardCode()
    @Provides
    @AppComponentScope
    fun getMapDataSource() = MapDataSourceHardCode()
}

@dagger.Module
object ChatsViewModelModule {
    @Provides
    @AppComponentScope
    fun getChatsViewModelFactory(
        userRepository: UserRepository,
        userWithLastMessageUseCase: UserWithLastMessageUseCase
    ): ChatsViewModelFactory {
        return ChatsViewModelFactory(userRepository,userWithLastMessageUseCase)
    }
}

@dagger.Module
object ConversationViewModelModule {
    @Provides
    @AppComponentScope
    fun getConversationViewModelFactory(
        messagesRepository: MessagesRepository,
        userRepository: UserRepository,
        messageWithUserSenderUseCase: MessageWithUserSenderUseCase
    ): ConversationViewModelFactory {
        return ConversationViewModelFactory(messagesRepository, userRepository,messageWithUserSenderUseCase)
    }
}
@dagger.Module
object InteractiveMapViewModelModule {
    @Provides
    @AppComponentScope
    fun getInteractiveMapViewModelFactory(
        pointsRepository: MapPointsRepository,
    ): InteractiveMapViewModelFactory {
        return InteractiveMapViewModelFactory(pointsRepository)
    }
}
