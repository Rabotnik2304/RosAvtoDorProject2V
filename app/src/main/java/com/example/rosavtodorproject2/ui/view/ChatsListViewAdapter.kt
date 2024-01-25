package com.example.rosavtodorproject2.ui.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.rosavtodorproject2.R
import com.example.rosavtodorproject2.ui.model.ChatElementModel

class ChatsListViewAdapter(
    chatsDiffCalculator: ChatsDiffCalculator
): ListAdapter<ChatElementModel, ChatElementViewHolder>(chatsDiffCalculator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatElementViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.chat_element,
            parent,
            false
        )


        return ChatElementViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: ChatElementViewHolder, position: Int) {
        val chatElement = holder.itemView
        chatElement.setOnClickListener {
            val intentToOpenChatActivity = Intent(chatElement.context, ChatActivity::class.java)

            intentToOpenChatActivity.putExtra(ChatActivity.user_id_key, currentList[position].id)
            intentToOpenChatActivity.putExtra(ChatActivity.user_name_key, currentList[position].userName)
            intentToOpenChatActivity.putExtra(
                ChatActivity.user_picture_key,
                currentList[position].userPictureResourcesId
            )

            chatElement.context.startActivity(intentToOpenChatActivity)
        }
        holder.onBind(currentList[position])
    }

}