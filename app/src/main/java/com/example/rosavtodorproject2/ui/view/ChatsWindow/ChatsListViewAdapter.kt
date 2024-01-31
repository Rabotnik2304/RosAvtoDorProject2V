package com.example.rosavtodorproject2.ui.view.ChatsWindow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ListAdapter
import com.example.rosavtodorproject2.R
import com.example.rosavtodorproject2.ui.model.ChatElementModel

class ChatsListViewAdapter(
    chatsDiffCalculator: ChatsDiffCalculator
) : ListAdapter<ChatElementModel, ChatElementViewHolder>(chatsDiffCalculator) {
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

            val action = ChatsFragmentDirections.actionChatsFragmentToConversationFragment(
                collocutorId = currentList[position].id,
                collocutorName = currentList[position].userName,
                collocutorPictureResourceId = currentList[position].userPictureResourcesId,
            )

            Navigation.findNavController(holder.itemView).navigate(action)
        }
        holder.onBind(currentList[position])
    }

}