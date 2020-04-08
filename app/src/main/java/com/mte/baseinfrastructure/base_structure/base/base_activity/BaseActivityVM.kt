package com.mte.baseinfrastructure.base_structure.base.base_activity

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.mte.baseinfrastructure.base_structure.base.BaseViewModel

abstract class BaseActivityVM<VM : BaseViewModel , T : ViewDataBinding> : BaseActivity<T>() {



    protected abstract val viewmodel : VM?


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewmodel?.activity = this
        viewmodel?.context = this
    }

}
