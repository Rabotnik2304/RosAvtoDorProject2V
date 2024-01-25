package com.example.RosAvtoDorApp.ui.view

import androidx.recyclerview.widget.DiffUtil
import com.example.RosAvtoDorApp.ui.model.ChatElementModel

class ChatsDiffCalculator : DiffUtil.ItemCallback<ChatElementModel>() {
    override fun areItemsTheSame(oldItem: ChatElementModel, newItem: ChatElementModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ChatElementModel, newItem: ChatElementModel): Boolean {
        return oldItem==newItem
    }

}