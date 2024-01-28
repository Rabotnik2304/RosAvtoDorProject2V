package com.example.rosavtodorproject2.ui.view.ConversationWindow

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rosavtodorproject2.App
import com.example.rosavtodorproject2.R
import com.example.rosavtodorproject2.databinding.FragmentConversationBinding
import com.example.rosavtodorproject2.ui.stateHolder.ChatsFragmentViewModel
import com.example.rosavtodorproject2.ui.view.ChatsWindow.ChatsListViewAdapter
import com.example.rosavtodorproject2.ui.view.ChatsWindow.ChatsViewController

class ConversationFragment : Fragment() {
    var collocutorId = 0
    lateinit var binding: FragmentConversationBinding

    private val applicationComponent
        get() = App.getInstance().applicationComponent


    private lateinit var adapter: ChatsListViewAdapter
    private var chatsViewController: ChatsViewController? = null

    private lateinit var viewModel: ChatsFragmentViewModel

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
        val args : ConversationFragmentArgs by navArgs()

        collocutorId = args.collocutorId

        binding.collocutorName.text = args.collocutorName

        //Я знаю, что некрасиво, но если не так, то при плохом интернете прога тупо упадёт, так что терпим.
        binding.collocutorPicture.setImageBitmap(
            AppCompatResources.getDrawable(requireContext(),args.collocutorPictureResourceId)?.
            toBitmap(requireContext().toPx(70).toInt(), requireContext().toPx(70).toInt())
                ?:
            AppCompatResources.getDrawable(requireContext(),R.drawable.empty_person_avatar)?.
            toBitmap(requireContext().toPx(70).toInt(), requireContext().toPx(70).toInt()))

        binding.backToChatsPanelButton.setOnClickListener{
            findNavController().navigate(R.id.action_conversationFragment_to_chatsFragment)
        }
    }
    fun Context.toPx(dp: Int): Float = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        resources.displayMetrics
    )
}