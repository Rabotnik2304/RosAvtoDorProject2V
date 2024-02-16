package com.example.rosavtodorproject2.ui.view.ChatsWindow

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import com.example.rosavtodorproject2.R
import com.example.rosavtodorproject2.databinding.ChatElementBinding
import com.example.rosavtodorproject2.databinding.MessageElementBinding
import com.example.rosavtodorproject2.ui.model.ChatElementModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatElementViewHolder(private val itemChatBinding: ChatElementBinding) : RecyclerView.ViewHolder(itemChatBinding.root) {
    fun onBind(chatElementModel: ChatElementModel) {
        itemChatBinding.userName.text = chatElementModel.userName

        itemChatBinding.userPicture.setImageBitmap(
            AppCompatResources.getDrawable(
                itemView.context,
                chatElementModel.userPictureResourcesId
            )!!
                .toBitmap(itemView.context.toPx(70).toInt(), itemView.context.toPx(70).toInt())
        )
        itemChatBinding.userLastMessage.text =
            smallPartOfMessage(chatElementModel.userSenderName, chatElementModel.userLastMessage)

        val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMANY)
        itemChatBinding.userLastMessageDate.text = sdf.format(chatElementModel.userLastMessageDate)
    }

    fun smallPartOfMessage(senderName: String, message: String): String {
        val fullMessageText = "$senderName $message"
        if (fullMessageText.length < 27) {
            return fullMessageText
        } else {
            return fullMessageText.substring(0, 26) + "..."
        }
    }

    fun Context.toPx(dp: Int): Float = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        resources.displayMetrics
    )
}