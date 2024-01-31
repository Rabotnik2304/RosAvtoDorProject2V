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
import com.example.rosavtodorproject2.ui.model.ChatElementModel
import java.util.Date

class ChatElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    //надо будет в будущем на viewBinding-и поменять
    private val userName: TextView = itemView.findViewById(R.id.user_name)
    private val userPicture: ImageView = itemView.findViewById(R.id.user_picture)
    private var userLastMessage: TextView = itemView.findViewById(R.id.user_last_message)
    private var userLastMessageDate: TextView = itemView.findViewById(R.id.user_last_message_date)


    fun onBind(chatElementModel: ChatElementModel) {
        userName.text = chatElementModel.userName

        userPicture.setImageBitmap(
            AppCompatResources.getDrawable(
                itemView.context,
                chatElementModel.userPictureResourcesId
            )!!
                .toBitmap(itemView.context.toPx(70).toInt(), itemView.context.toPx(70).toInt())
        )
        userLastMessage.text =
            smallPartOfMessage(chatElementModel.userSenderName, chatElementModel.userLastMessage)
        userLastMessageDate.text = chatElementModel.userLastMessageDate.myToString()
    }

    fun smallPartOfMessage(senderName: String, message: String): String {
        val fullMessageText = "$senderName $message"
        if (fullMessageText.length < 27) {
            return fullMessageText
        } else {
            return fullMessageText.substring(0, 26) + "..."
        }
    }

    fun Date.myToString(): String =
        "${this.date}.${this.month + 1}.${this.year}  ${this.hours}:${this.minutes}"

    fun Context.toPx(dp: Int): Float = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        resources.displayMetrics
    )
}