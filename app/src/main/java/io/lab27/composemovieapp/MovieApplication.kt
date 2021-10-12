package io.lab27.composemovieapp

import android.app.Application
import io.lab27.composemovieapp.di.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(applicationContext)
            modules(applicationModule)
        }
    }
}