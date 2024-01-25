package com.example.rosavtodorproject2

import android.app.Application
import com.example.rosavtodorproject2.ioc.ApplicationComponent
import com.example.rosavtodorproject2.ioc.DaggerApplicationComponent


class App: Application() {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder().build()}

    override fun onCreate() {
        super.onCreate()
        sInstance = this
    }
    companion object{
        private var sInstance: App? = null
        fun getInstance(): App {
            return sInstance!!
        }
    }
}