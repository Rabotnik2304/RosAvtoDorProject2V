package com.example.RosAvtoDorApp.ui.view

import android.app.Activity
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.RosAvtoDorApp.ui.stateHolder.ChatsFragmentViewModel
import com.example.rosavtodorproject2.R

class ChatsViewController(
    private val activity: Activity,
    rootView: View,
    private val adapter: ChatsListViewAdapter,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: ChatsFragmentViewModel,
) {
    private val chatsRecyclerView:RecyclerView = rootView.findViewById(R.id.chats)

    fun setUpViews(){
        setUpChatsList()
    }

    fun setUpChatsList(){
        val layoutManager = LinearLayoutManager(activity.baseContext,LinearLayoutManager.VERTICAL,false)
        chatsRecyclerView.adapter=adapter
        chatsRecyclerView.layoutManager = layoutManager

        viewModel.chats.observe(lifecycleOwner){newUsersOrMessages->
            adapter.submitList(newUsersOrMessages)
        }

        chatsRecyclerView.addItemDecoration(DividerItemDecoration(activity.baseContext,layoutManager.orientation))
        chatsRecyclerView.addItemDecoration(ChatElementOffsetItemDecoration(0,0))
    }
}