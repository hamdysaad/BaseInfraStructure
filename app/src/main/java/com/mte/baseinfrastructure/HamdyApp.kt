package com.mte.baseinfrastructure

import android.content.Context
import com.mte.infrastructurebase.App
import org.koin.core.module.Module

class HamdyApp : App() {


    override fun getAndroidContext(): Context {
        return this
    }

    override fun getModulesList(): List<Module> {
        return emptyList()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


    companion object {

        private var instance : HamdyApp? = null

    }
}