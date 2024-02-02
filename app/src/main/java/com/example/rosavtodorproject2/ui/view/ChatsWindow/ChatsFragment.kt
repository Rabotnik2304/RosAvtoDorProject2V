package com.example.rosavtodorproject2.ui.view.ChatsWindow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rosavtodorproject2.App
import com.example.rosavtodorproject2.R
import com.example.rosavtodorproject2.databinding.FragmentChatsBinding
import com.example.rosavtodorproject2.databinding.FragmentConversationBinding
import com.example.rosavtodorproject2.ui.stateHolders.ChatsFragmentViewModel

class ChatsFragment : Fragment() {

    lateinit var binding: FragmentChatsBinding
    private val applicationComponent
        get() = App.getInstance().applicationComponent


    private lateinit var adapter: ChatsListViewAdapter
    private var chatsViewController: ChatsViewController? = null

    private lateinit var viewModel: ChatsFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ChatsListViewAdapter(ChatsDiffCalculator())

        viewModel = ViewModelProvider(this, applicationComponent.getChatsViewModelFactory())
            .get(ChatsFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentChatsBinding.inflate(layoutInflater, container, false)

        chatsViewController = ChatsViewController(
            activity = requireActivity(),
            rootView = binding.root,
            adapter = adapter,
            lifecycleOwner = viewLifecycleOwner,
            viewModel = viewModel,
        ).apply {
            setUpViews()
        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goToInteractiveMapPanel.root.setOnClickListener{
            findNavController().navigate(R.id.action_chatsFragment_to_interactiveMapFragment)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        chatsViewController = null
    }
}
