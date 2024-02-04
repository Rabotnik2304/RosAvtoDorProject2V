package com.example.rosavtodorproject2.ui.view.ChatsWindow

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.rosavtodorproject2.App
import com.example.rosavtodorproject2.R
import com.example.rosavtodorproject2.databinding.FragmentChatsBinding
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

        setUpToolBar()

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
    fun setUpToolBar(){

        val navController = NavHostFragment.findNavController(this)

        val sideBar = binding.navView
        sideBar.setupWithNavController(navController)

        val profileMenuHeader = sideBar.getHeaderView(0)
        val userNameTextView = profileMenuHeader.findViewById<TextView>(R.id.current_user_name)

        viewModel.currentUser.observe(viewLifecycleOwner) {
            userNameTextView.text = it.name
        }

        val userNameProfilePicture = profileMenuHeader.findViewById<ImageView>(R.id.current_user_image)

        viewModel.currentUser.observe(viewLifecycleOwner) {
            userNameProfilePicture.setBackgroundResource(it.userPictureResourcesId)
        }

        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout = binding.drawerLayout)
        val toolbar = binding.profileAndSearchPanel

        toolbar.setupWithNavController(navController,appBarConfiguration)

        toolbar.children.forEach{
            if(it is ImageButton){
                it.scaleX=1.5f
                it.scaleY=1.5f
            }
        }
    }
}
