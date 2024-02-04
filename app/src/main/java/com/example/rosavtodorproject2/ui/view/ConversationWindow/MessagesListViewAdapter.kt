package com.example.rosavtodorproject2.ui.view.ConversationWindow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.rosavtodorproject2.databinding.MessageElementBinding
import com.example.rosavtodorproject2.ui.model.MessageElementModel

class MessagesListViewAdapter(
    messagesDiffCalculator: MessagesDiffCalculator,
    private val currentUserId:Int,
) : ListAdapter<MessageElementModel, MessageElementViewHolder>(messagesDiffCalculator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageElementViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val messageElementViewBinding = MessageElementBinding.inflate(layoutInflater, parent, false)

        return MessageElementViewHolder(messageElementViewBinding,currentUserId)
    }

    override fun onBindViewHolder(holder: MessageElementViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}