package com.example.rosavtodorproject2.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rosavtodorproject2.data.dataSource.DataSourceHardCode
import com.example.rosavtodorproject2.data.models.Message
import com.example.rosavtodorproject2.ioc.AppComponentScope
import java.util.Date
import javax.inject.Inject
@AppComponentScope
class MessagesRepository @Inject constructor(
    val dataSource: DataSourceHardCode
) {
    private val _messages = MutableLiveData<List<Message>>(emptyList())
    val messages: LiveData<List<Message>> = _messages

    fun updateMessages(){
        val loadedList = dataSource.loadMessages()
        _messages.value = loadedList
    }
    fun addMessage(id:Int,userSenderId:Int, userRecieverId: Int, text:String, sendDate:Date){
        dataSource.loadMessages().add(
            Message(
                id=id,
                userSenderId=userSenderId,
                userRecieverId=userRecieverId,
                text=text,
                sendDate=sendDate,
            ))
        updateMessages()
    }
}