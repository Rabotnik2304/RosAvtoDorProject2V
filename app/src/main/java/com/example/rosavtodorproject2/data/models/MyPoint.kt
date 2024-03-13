package com.example.rosavtodorproject2.data.models

import com.google.gson.annotations.SerializedName

data class MyPoint(
    //Возможно нужно все поля сделать nullable, но тут не уверен.
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: Int,
)