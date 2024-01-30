package com.example.rosavtodorproject2.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rosavtodorproject2.data.dataSource.DataSourceHardCode
import com.example.rosavtodorproject2.data.models.User
import com.example.rosavtodorproject2.ioc.AppComponentScope
import javax.inject.Inject
@AppComponentScope
class UserRepository @Inject constructor(
    val dataSource: DataSourceHardCode
        ){
    private val _userContacts = MutableLiveData<List<User>>(emptyList())
    val userContacts: LiveData<List<User>> = _userContacts
    fun updateUsers(){
        val loadedList = dataSource.loadUserContacts()
        _userContacts.value = loadedList
    }
}