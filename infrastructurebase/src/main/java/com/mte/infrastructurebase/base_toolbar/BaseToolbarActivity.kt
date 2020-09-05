package com.mte.infrastructurebase.base_toolbar

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.mte.infrastructurebase.R
import com.mte.infrastructurebase.base.base_activity.BaseActivity
import com.mte.infrastructurebase.databinding.BaseActivityToolbarBinding


abstract class BaseToolbarActivity<T : ViewDataBinding> : BaseActivity<T>() {

    override fun setActivityContentView(activity: AppCompatActivity) {
       val basToolbarBinding = DataBindingUtil.setContentView<BaseActivityToolbarBinding>(this , R.layout.base_activity_toolbar)
        basToolbarBinding.toolbarContainer.addView(createToolbarView())
        basToolbarBinding.toolbarContent.addView(createContent())
        basToolbarBinding?.executePendingBindings()
    }

    open fun createContent(): View? {
        binding = inflateLayout(layoutRes)?.let { DataBindingUtil.bind(it) }
        return  binding?.root
    }

    abstract fun createToolbarView(): View?


}