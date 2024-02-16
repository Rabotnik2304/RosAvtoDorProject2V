package com.example.rosavtodorproject2.ioc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rosavtodorproject2.data.repositories.MapPointsRepository
import com.example.rosavtodorproject2.ui.stateHolders.InteractiveMapFragmentViewModel
import javax.inject.Inject

class InteractiveMapViewModelFactory @Inject constructor(
    val mapPointsRepository: MapPointsRepository,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InteractiveMapFragmentViewModel(
            mapPointsRepository = mapPointsRepository,
        ) as T
    }
}