package com.example.rosavtodorproject2.ui.view.interactiveMapFragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
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
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.Runtime.getApplicationContext
import com.yandex.runtime.image.ImageProvider


class InteractiveMapFragment : Fragment() {
    private lateinit var binding: FragmentInteractiveMapBinding
    private val applicationComponent
        get() = App.getInstance().applicationComponent

    private lateinit var mapView: MapView

    private val viewModel: InteractiveMapFragmentViewModel by viewModels { applicationComponent.getInteractiveMapViewModelFactory() }

    private var isPointAdding = false
    private var currentIconNumber: Int = -1
    private val iconsResources= listOf( R.drawable.image_car_accident_24dp, R.drawable.image_car_accident_24dp, R.drawable.image_car_accident_24dp, R.drawable.image_car_accident_24dp)
    private var currentIconPlacemark: com.yandex.mapkit.map.PlacemarkMapObject? = null
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

        mapView.map.addInputListener(addingPointListener)


        viewModel.points.observe(viewLifecycleOwner) {
            addVerifiedPointsToInteractiveMap(it)
        }

        return binding.root
    }

    private val addingPointListener = object : InputListener {
        override fun onMapTap(map: Map, point: Point) {
            if (!isPointAdding) {
                return
            }

            val imageProvider =
                ImageProvider.fromResource(requireContext(), iconsResources[currentIconNumber])

            if (currentIconPlacemark == null) {
                currentIconPlacemark = mapView.map.mapObjects.addPlacemark()
                    .apply {
                        geometry = Point(point.latitude, point.longitude)
                        setIcon(imageProvider)
                    }
            } else {
                currentIconPlacemark?.geometry = Point(point.latitude, point.longitude)
            }

        }

        override fun onMapLongTap(p0: Map, p1: Point) {
            // Обработка долгого нажатия, если нужно
        }
    }
    private fun addVerifiedPointsToInteractiveMap(myPoints: List<MyPoint>) {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backToChatsPanelButton.setOnClickListener {
            findNavController().navigate(R.id.action_interactiveMapFragment_to_chatsFragment)
        }
        binding.addPointToMapFab.setOnClickListener {
            listenerForAddPointToMapFab(it)
        }
        binding.confirmAdditionPointToMapFab.setOnClickListener {
            listenerForConfirmAdditionPointFab(it)
        }
        binding.cancelAdditionPointToMapFab.setOnClickListener {
            listenerForCancelAdditionPointFab(it)
        }
    }

    private fun listenerForAddPointToMapFab(fabView: View) {
        val popupMenu = PopupMenu(requireContext(), fabView)
        popupMenu.inflate(R.menu.add_point_menu)

        popupMenu.setOnMenuItemClickListener {
            listenerForMenuItemClick(it)
        }

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

    private fun listenerForMenuItemClick(menuItem: MenuItem): Boolean {
        binding.addPointToMapFab.visibility = View.INVISIBLE
        binding.cancelAdditionPointToMapFab.visibility = View.VISIBLE
        binding.confirmAdditionPointToMapFab.visibility = View.VISIBLE

        currentIconNumber = menuItem.order
        isPointAdding = true
        return true
    }

    private fun listenerForCancelAdditionPointFab(fabView: View): Boolean {
        binding.addPointToMapFab.visibility = View.VISIBLE
        binding.cancelAdditionPointToMapFab.visibility = View.INVISIBLE
        binding.confirmAdditionPointToMapFab.visibility = View.INVISIBLE
        isPointAdding = false
        currentIconNumber = -1

        if (currentIconPlacemark!=null) {
            mapView.map.mapObjects.remove(currentIconPlacemark!!)
            currentIconPlacemark = null
        }
        return true
    }

    private fun listenerForConfirmAdditionPointFab(fabView: View): Boolean {
        binding.addPointToMapFab.visibility = View.VISIBLE
        binding.cancelAdditionPointToMapFab.visibility = View.INVISIBLE
        binding.confirmAdditionPointToMapFab.visibility = View.INVISIBLE
        isPointAdding = false
        currentIconNumber = -1

        currentIconPlacemark = null

        return true
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