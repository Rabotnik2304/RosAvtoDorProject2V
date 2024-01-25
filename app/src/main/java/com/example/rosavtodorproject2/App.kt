package com.example.rosavtodorproject2

import android.app.Application
import android.content.Context
import ioc.ApplicationComponent
import ioc.DaggerApplicationComponent


class App: Application() {
    val applicationComponent: ApplicationComponent by lazy { DaggerApplicationComponent.builder().build()}
    companion object{
        /**
         * Shortcut to get [App] instance from any context, e.g. from Activity.
         */
        fun get(context:Context):App = context.applicationContext as App

    }
}