package com.example.rosavtodorproject2.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rosavtodorproject2.App
import com.example.rosavtodorproject2.R
import com.example.rosavtodorproject2.ui.stateHolder.ChatsFragmentViewModel

class ChatsFragment: Fragment(){

    private val applicationComponent
        get() = App.getInstance().applicationComponent


    private lateinit var adapter:ChatsListViewAdapter
    private var chatsViewController:ChatsViewController? = null

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
