package com.example.rosavtodorproject2.ui.view.interactiveMapFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rosavtodorproject2.data.models.MyPoint
import com.example.rosavtodorproject2.domain.useCases.MapPointsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class InteractiveMapFragmentViewModel @Inject constructor(
    val mapPointsUseCase: MapPointsUseCase,
) : ViewModel() {
    val points: LiveData<List<MyPoint>> = mapPointsUseCase.points

    init {
        updatePoints()
    }

    //Я оставляю здесь отдельный метод, чтобы в будущем добавить SwipeToRefresh, к точкам на карте
    fun updatePoints() {
        viewModelScope.launch {
            mapPointsUseCase.updatePoints()
        }
    }

    fun addPoint(type: Int, latitude: Double, longitude: Double, text: String) {
        mapPointsUseCase.addPoint(
            MyPoint(
                type = type,
                latitude = latitude,
                longitude = longitude,
                name = text,
            )
        )
    }
}