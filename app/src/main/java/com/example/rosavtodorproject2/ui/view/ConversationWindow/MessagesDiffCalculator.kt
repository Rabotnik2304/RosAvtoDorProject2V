package com.example.rosavtodorproject2.ui.view.ConversationWindow

import androidx.recyclerview.widget.DiffUtil
import com.example.rosavtodorproject2.ui.model.MessageElementModel

class MessagesDiffCalculator : DiffUtil.ItemCallback<MessageElementModel>() {
    override fun areItemsTheSame(oldItem: MessageElementModel, newItem: MessageElementModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MessageElementModel, newItem: MessageElementModel): Boolean {
        return oldItem==newItem
    }
}