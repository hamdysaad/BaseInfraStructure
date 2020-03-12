package com.mte.infrastructurebase.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.mte.infrastructurebase.base.BaseViewModel

abstract class BaseFragmentVM<VM : BaseViewModel , T : ViewDataBinding> : BaseFragment<T>() {



    protected abstract val viewmodel : VM?


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewmodel?.activity = activity
        viewmodel?.context = context
        viewmodel?.fragment = this

        return super.onCreateView(inflater, container, savedInstanceState)
    }

}
