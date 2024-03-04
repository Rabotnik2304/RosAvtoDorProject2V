package com.example.rosavtodorproject2.ui.view.interactiveMapFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListPopupWindow
import android.widget.PopupMenu
import android.widget.Spinner
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
    private val iconsResources = listOf(
        R.drawable.image_car_accident_24dp,
        R.drawable.image_car_accident_24dp,
        R.drawable.image_car_accident_24dp,
        R.drawable.image_car_accident_24dp,
        R.drawable.petrol_station_icon,
        R.drawable.petrol_station_icon,
        R.drawable.petrol_station_icon,
        R.drawable.petrol_station_icon,
    )
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

        /*
           Всё что находится ниже, до конца метода, это просто эксперимент по получению местоположения
           После получения разрешения, он не сразу, а только после повторного захода во фрагмент
           начинает получать текущее местоположение
           Причём получает он его прямо таки постоянно, без остановок.
           (Возможно стоит делать запросы в Бек, только если расстояние от прошлого отличается на 2 км.)
           Мне кажется стоит ПОКА оставить это дело, до обновления бека, т.к. пока, без знаний того,
           как будет организован бек, я не могу сказать, как мне надо здесь всё поменять, чтобы
           работало нормально + выглядело не так топорно что-ли.



        val locationManager:LocationManager? = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager?

        val locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                // Местоположение пользователя изменилось
                val latitude: Double = location.getLatitude()
                val longitude: Double = location.getLongitude()
                // Делаем что-то с полученными координатами
                Toast.makeText(
                    requireContext(),
                    "Широта: $latitude, Долгота: $longitude",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Проверяем разрешение на использование местоположения
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Если разрешение не предоставлено, запрашиваем его
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )

            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                locationManager?.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0,
                    0f,
                    locationListener
                )
            }
        } else {
            // Если разрешение предоставлено, запрашиваем обновления местоположения
            locationManager?.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0,
                0f,
                locationListener
            )
        }
        */
        setUpRoadsSpinnerList()

        return binding.root
    }

    private val addingPointListener = object : InputListener {
        override fun onMapTap(map: Map, point: Point) {

            if (!isPointAdding) {
                return
            }

            binding.confirmAdditionPointToMapFab.isEnabled = true

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
            // Пока не нужно, но может потом что-нибудь покумекаем
        }
    }

    private fun addVerifiedPointsToInteractiveMap(myPoints: List<MyPoint>) {

        mapView.map.mapObjects.clear()
        myPoints.forEach {
            val imageProvider =
                ImageProvider.fromResource(requireContext(), iconsResources[it.type])
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

    val roads: ArrayList<String> =
        arrayListOf<String>("Р-152", "Р-153", "Р-1545", "Р-145", "Р-155", "Р-545")

    private fun setUpRoadsSpinnerList() {
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        val adapter =
            ArrayAdapter(requireContext(), R.layout.spinner_list_element, R.id.road_name, roads)

        binding.choseCurrentRoadSpinner.adapter = adapter
        val itemSelectedListener: AdapterView.OnItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {

                    // Получаем выбранный объект
                    val item = parent.getItemAtPosition(position) as String
                    Toast.makeText(requireContext(), item, Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        binding.choseCurrentRoadSpinner.onItemSelectedListener = itemSelectedListener
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

        binding.choseCurrentRoadSpinner.isEnabled = false
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

        if (currentIconPlacemark != null) {
            mapView.map.mapObjects.remove(currentIconPlacemark!!)
            currentIconPlacemark = null
        }
        binding.confirmAdditionPointToMapFab.isEnabled = false
        binding.choseCurrentRoadSpinner.isEnabled = true

        return true
    }

    private fun listenerForConfirmAdditionPointFab(fabView: View): Boolean {
        binding.addPointToMapFab.visibility = View.VISIBLE
        binding.cancelAdditionPointToMapFab.visibility = View.INVISIBLE
        binding.confirmAdditionPointToMapFab.visibility = View.INVISIBLE
        isPointAdding = false

        viewModel.addPoint(
            type = currentIconNumber,
            latitude = currentIconPlacemark?.geometry!!.latitude,
            longitude = currentIconPlacemark?.geometry!!.longitude,
            text = "Зачем я существую?"
        )

        currentIconNumber = -1
        currentIconPlacemark = null
        binding.confirmAdditionPointToMapFab.isEnabled = false
        binding.choseCurrentRoadSpinner.isEnabled = true

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