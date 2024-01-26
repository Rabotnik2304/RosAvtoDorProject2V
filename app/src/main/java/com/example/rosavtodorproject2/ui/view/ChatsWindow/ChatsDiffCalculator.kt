package com.example.rosavtodorproject2.ui.view.ChatsWindow

import androidx.recyclerview.widget.DiffUtil
import com.example.rosavtodorproject2.ui.model.ChatElementModel

class ChatsDiffCalculator : DiffUtil.ItemCallback<ChatElementModel>() {
    override fun areItemsTheSame(oldItem: ChatElementModel, newItem: ChatElementModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ChatElementModel, newItem: ChatElementModel): Boolean {
        return oldItem==newItem
    }

}