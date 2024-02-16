package com.example.rosavtodorproject2.data.dataSource

import com.example.rosavtodorproject2.data.models.MyPoint

class MapDataSourceHardCode {

    private val points: MutableList<MyPoint> = mutableListOf(
        MyPoint(
            0,
            54.88324475618048,
            57.26202530166927,
            "Азс 1.",
        ),
        MyPoint(
            0,
            55.52648707893606,
            65.22737461759895,
            "Азс 2",
        ),
        MyPoint(
            1,
            64.24725076450086,
            56.10278315937718,
            "Азс 3",
        ),
    )

    fun loadPoints() = points
}