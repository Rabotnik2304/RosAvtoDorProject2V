package com.example.RosAvtoDorApp.ui.view

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import com.example.RosAvtoDorApp.ui.model.ChatElementModel
import com.example.rosavtodorproject2.R
import java.util.Date

class ChatElementViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val userName: TextView = itemView.findViewById(R.id.user_name)
    private val userPicture:ImageView = itemView.findViewById(R.id.user_picture)
    private var userLastMessage:TextView = itemView.findViewById(R.id.user_last_message)
    private var userLastMessageDate:TextView = itemView.findViewById(R.id.user_last_message_date)


    fun onBind(chatElementModel: ChatElementModel){
        userName.text=chatElementModel.userName

        userPicture.setImageBitmap(AppCompatResources.getDrawable(itemView.context,chatElementModel.userPictureResourcesId)!!
            .toBitmap(itemView.context.toPx(70).toInt(), itemView.context.toPx(70).toInt()))
        userLastMessage.text = chatElementModel.userLastMessage
        userLastMessageDate.text = chatElementModel.userLastMessageDate.myToString()
    }
    fun Date.myToString() : String = "${this.day}.${this.month}.${this.year}"
    fun Context.toPx(dp: Int): Float = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        resources.displayMetrics
    )
}