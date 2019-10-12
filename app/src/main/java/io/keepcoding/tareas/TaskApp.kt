package io.keepcoding.tareas

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import io.keepcoding.tareas.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TaskApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        startKoin {
            androidLogger()
            androidContext(this@TaskApp)
            modules(appModule)
        }
    }

}