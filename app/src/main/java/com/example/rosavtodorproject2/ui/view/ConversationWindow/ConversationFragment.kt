package com.example.rosavtodorproject2.ui.view.ConversationWindow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rosavtodorproject2.R
import com.example.rosavtodorproject2.databinding.FragmentConversationBinding
import com.example.rosavtodorproject2.ui.view.MAIN

class ConversationFragment : Fragment() {

    lateinit var binding: FragmentConversationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConversationBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backToChatsPanelButton.setOnClickListener{
            MAIN.navController.navigate(R.id.action_conversationFragment_to_chatsFragment)
        }
    }
}