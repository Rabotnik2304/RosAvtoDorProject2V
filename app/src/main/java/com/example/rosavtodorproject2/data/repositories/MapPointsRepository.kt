package com.example.rosavtodorproject2.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rosavtodorproject2.data.dataSource.MapDataSourceHardCode
import com.example.rosavtodorproject2.data.models.MyPoint
import com.example.rosavtodorproject2.ioc.AppComponentScope
import javax.inject.Inject

@AppComponentScope
class MapPointsRepository @Inject constructor(
    val dataSource: MapDataSourceHardCode
) {
    private val _points = MutableLiveData<List<MyPoint>>(emptyList())
    val points: LiveData<List<MyPoint>> = _points

    fun updatePoints() {
        _points.value = dataSource.loadPoints()
    }
}