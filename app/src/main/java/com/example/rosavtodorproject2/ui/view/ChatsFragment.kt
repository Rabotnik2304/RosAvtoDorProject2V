package com.example.RosAvtoDorApp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.RosAvtoDorApp.ui.stateHolder.ChatsFragmentViewModel
import com.example.rosavtodorproject2.App
import com.example.rosavtodorproject2.R

class ChatsFragment: Fragment(){

    private val applicationComponent
        get() = App.get(requireContext()).applicationComponent


    private lateinit var adapter:ChatsListViewAdapter
    private var chatsViewController:ChatsViewController? = null

    private lateinit var viewModel:ChatsFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ChatsListViewAdapter(ChatsDiffCalculator())

        val useCase = applicationComponent.getUserWithLastMessageUseCase()
        viewModel = ChatsFragmentViewModel(useCase)
        /*viewModel = ViewModelProvider(this, applicationComponent.getChatsViewModelFactory())
            .get(ChatsFragmentViewModel::class.java)*/
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chats, container, false)
        chatsViewController = ChatsViewController(
            activity = requireActivity(),
            rootView = view,
            adapter  = adapter,
            lifecycleOwner = viewLifecycleOwner,
            viewModel = viewModel,
        ).apply {
            setUpViews()
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        chatsViewController=null;
    }
}
