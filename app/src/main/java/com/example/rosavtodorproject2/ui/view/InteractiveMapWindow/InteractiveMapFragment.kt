package com.example.rosavtodorproject2.ui.view.InteractiveMapWindow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.rosavtodorproject2.R
import com.example.rosavtodorproject2.databinding.FragmentChatsBinding
import com.example.rosavtodorproject2.databinding.FragmentInteractiveMapBinding

class InteractiveMapFragment : Fragment() {
    lateinit var binding: FragmentInteractiveMapBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInteractiveMapBinding.inflate(layoutInflater, container,false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backToChatsPanelButton.setOnClickListener{
            findNavController().navigate(R.id.action_interactiveMapFragment_to_chatsFragment)
        }
    }

}