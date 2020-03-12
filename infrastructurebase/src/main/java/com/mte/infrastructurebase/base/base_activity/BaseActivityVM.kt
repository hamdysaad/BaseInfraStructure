package com.mte.infrastructurebase.base.base_activity

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.mte.infrastructurebase.base.BaseViewModel

abstract class BaseActivityVM<VM : BaseViewModel , T : ViewDataBinding> : BaseActivity<T>() {



    protected abstract val viewmodel : VM?


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewmodel?.activity = this
        viewmodel?.context = this
    }

}
