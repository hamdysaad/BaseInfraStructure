package com.mte.infrastructurebase

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.mte.infrastructurebase.utils.LocaleHelper
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import timber.log.Timber


abstract class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            modules(getModules())
            androidContext(getAndroidContext())
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        appInstance = this
    }

    abstract fun getAndroidContext(): Context

    private fun getModules(): List<Module> {

        return ArrayList<Module>().also {
            it.addAll(getModulesList())
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"))
    }


    abstract fun getModulesList(): List<Module>

    companion object{
        lateinit var appInstance: App

    }


}