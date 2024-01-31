package com.example.rosavtodorproject2.ui.view.ConversationWindow

import android.view.Gravity
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.rosavtodorproject2.data.dataSource.DataSourceHardCode.Companion.currentUser
import com.example.rosavtodorproject2.databinding.MessageElementBinding
import com.example.rosavtodorproject2.ui.model.MessageElementModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MessageElementViewHolder(private val itemMessageBinding: MessageElementBinding) :
    RecyclerView.ViewHolder(itemMessageBinding.root) {

    fun onBind(message: MessageElementModel) {

        itemMessageBinding.messageCollocutorName.text = message.userSenderName
        itemMessageBinding.messageText.text = message.text
        val sdf =SimpleDateFormat("dd.MM.yyyy hh:mm", Locale.GERMANY)
        itemMessageBinding.messageSendDate.text = sdf.format(message.sendDate)

        if (message.userSenderId == currentUser.id) {
            itemMessageBinding.messageLayout.layoutParams = LinearLayout.LayoutParams(
                itemMessageBinding.messageLayout.layoutParams
            ).apply {
                gravity = Gravity.END
            }
        }
    }
}