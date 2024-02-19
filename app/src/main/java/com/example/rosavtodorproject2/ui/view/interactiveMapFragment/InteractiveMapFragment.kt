package com.example.rosavtodorproject2.ui.view.interactiveMapFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rosavtodorproject2.App
import com.example.rosavtodorproject2.R
import com.example.rosavtodorproject2.data.models.MyPoint
import com.example.rosavtodorproject2.databinding.FragmentInteractiveMapBinding
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.Runtime.getApplicationContext
import com.yandex.runtime.image.ImageProvider
import java.lang.Exception


class InteractiveMapFragment : Fragment() {
    private lateinit var binding: FragmentInteractiveMapBinding
    private val applicationComponent
        get() = App.getInstance().applicationComponent

    private lateinit var mapView: MapView

    private val viewModel: InteractiveMapFragmentViewModel by viewModels { applicationComponent.getInteractiveMapViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInteractiveMapBinding.inflate(layoutInflater, container, false)

        MapKitFactory.initialize(getApplicationContext())
        mapView = binding.mapview

        mapView.map.move(
            CameraPosition(
                Point(55.154, 61.4291),
                /* zoom = */ 6f,
                /* azimuth = */ 0f,
                /* tilt = */ 0f
            )
        )

        viewModel.points.observe(viewLifecycleOwner) {
            addPointsToInteractiveMap(it)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backToChatsPanelButton.setOnClickListener {
            findNavController().navigate(R.id.action_interactiveMapFragment_to_chatsFragment)
        }
        binding.addPointToMapFab.setOnClickListener {
            val popupMenu = PopupMenu(requireContext(), it)
            popupMenu.inflate(R.menu.add_point_menu)
            try {
                val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
                fieldMPopup.isAccessible = true
                val mPopup = fieldMPopup.get(popupMenu)
                mPopup.javaClass
                    .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(mPopup, true)
            } catch (e: Exception) {
                Log.e("Main", "Error showing menu icons", e)
            } finally {
                popupMenu.show()
            }
        }
    }

    fun addPointsToInteractiveMap(myPoints: List<MyPoint>) {
        val imageProvider =
            ImageProvider.fromResource(requireContext(), R.drawable.petrol_station_icon)
        myPoints.forEach {
            mapView.map.mapObjects.addPlacemark()
                .apply {
                    geometry = Point(it.latitude, it.longitude)
                    setIcon(imageProvider)
                }
        }
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
}