package com.example.rosavtodorproject2.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rosavtodorproject2.data.dataSource.ChatsDataSourceHardCode
import com.example.rosavtodorproject2.data.models.User
import com.example.rosavtodorproject2.ioc.AppComponentScope
import javax.inject.Inject

@AppComponentScope
class UserRepository @Inject constructor(
    val dataSource: ChatsDataSourceHardCode
) {
    private val _currentUser = MutableLiveData<User>()
    val currentUser: LiveData<User>  = _currentUser

    private val _userContacts = MutableLiveData<List<User>>(emptyList())
    val userContacts: LiveData<List<User>> = _userContacts
    fun updateUsers() {
        _userContacts.value = dataSource.loadUserContacts()
    }
    fun updateCurrentUser(){
        _currentUser.value = ChatsDataSourceHardCode.currentUser
    }
}