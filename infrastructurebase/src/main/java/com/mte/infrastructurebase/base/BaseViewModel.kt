package com.mte.infrastructurebase.base

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent

open class BaseViewModel : ViewModel(){

    var activity: Activity? = null
    var context: Context? = null

    var fragment : BaseFragment<*>? = null


}
