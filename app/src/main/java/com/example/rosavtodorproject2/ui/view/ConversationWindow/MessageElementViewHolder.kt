package com.example.rosavtodorproject2.ui.view.ConversationWindow

import androidx.recyclerview.widget.RecyclerView
import com.example.rosavtodorproject2.databinding.MessageElementBinding
import com.example.rosavtodorproject2.ui.model.MessageElementModel
import java.util.Date

class MessageElementViewHolder(private val itemMessageBinding: MessageElementBinding):
    RecyclerView.ViewHolder(itemMessageBinding.root) {

    fun onBind(message: MessageElementModel){
        itemMessageBinding.messageCollocutorName.text = message.userSenderName
        itemMessageBinding.messageText.text = message.text
        itemMessageBinding.messageSendDate.text = message.sendDate.myToString()
    }
    fun Date.myToString() : String = "${this.date}.${this.month + 1}.${this.year}"

}