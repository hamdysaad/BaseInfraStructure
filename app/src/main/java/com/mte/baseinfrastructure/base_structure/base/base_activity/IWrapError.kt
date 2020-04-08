package com.mte.baseinfrastructure.base_structure.base.base_activity
import android.view.ViewGroup
interface IWrapError {

    fun addErrorView(root: ViewGroup?, msge: String?, onRetryClick: OnRetryClick)
}