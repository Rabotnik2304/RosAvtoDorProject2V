package com.example.rosavtodorproject2.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rosavtodorproject2.data.models.MyPoint
import com.example.rosavtodorproject2.data.repositories.MapPointsRepository
import javax.inject.Inject

class InteractiveMapFragmentViewModel @Inject constructor(
    val mapPointsRepository: MapPointsRepository,
) : ViewModel() {
    val points: LiveData<List<MyPoint>> = mapPointsRepository.points
    init {
        updatePoints()
    }
    //Я оставляю здесь отдельный метод, чтобы в будущем добавить SwipeToRefresh, к точкам на карте
    fun updatePoints(){
        mapPointsRepository.updatePoints()
    }
}