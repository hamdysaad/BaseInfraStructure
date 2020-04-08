package com.mte.baseinfrastructure.base_structure.base.base_activity

import android.view.ViewGroup

interface IWrapEmptyData {

    fun addEmptyView(root: ViewGroup? , msge : String? = null)
    fun addEmptyView(root: ViewGroup? )
}