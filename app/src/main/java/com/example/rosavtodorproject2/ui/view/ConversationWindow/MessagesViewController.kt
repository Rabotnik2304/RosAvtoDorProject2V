package com.example.rosavtodorproject2.ui.view.ConversationWindow

import android.app.Activity
import android.content.Context
import android.util.TypedValue
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rosavtodorproject2.R
import com.example.rosavtodorproject2.ui.stateHolders.ConversationFragmentViewModel


class MessagesViewController(
    private val activity: Activity,
    rootView: View,
    private val adapter: MessagesListViewAdapter,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: ConversationFragmentViewModel,
    private val collocutorId: Int,
) {
    private val messagesRecyclerView: RecyclerView = rootView.findViewById(R.id.messages_list)

    fun setUpViews() {
        setUpMessagesList()
    }

    fun setUpMessagesList() {
        val layoutManager = LinearLayoutManager(
            activity.baseContext,
            LinearLayoutManager.VERTICAL,
            false
        )

        layoutManager.stackFromEnd = true
        messagesRecyclerView.adapter = adapter
        messagesRecyclerView.layoutManager = layoutManager


        viewModel.messages.observe(lifecycleOwner) { newMessages ->
            adapter.submitList(newMessages.filter { it.userRecieverId == collocutorId || it.userSenderId == collocutorId })
        }

        messagesRecyclerView.addItemDecoration(
            MessageElementOffsetItemDecoration(
                leftOffset = activity.baseContext.toPx(16).toInt(),
                bottomOffset = activity.baseContext.toPx(15).toInt(),
                rightOffset = activity.baseContext.toPx(16).toInt(),
            )
        )
    }

    fun Context.toPx(dp: Int): Float = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        resources.displayMetrics
    )
}