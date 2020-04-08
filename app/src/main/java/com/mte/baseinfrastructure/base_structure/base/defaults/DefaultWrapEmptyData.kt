package com.mte.baseinfrastructure.base_structure.base.defaults

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.mte.baseinfrastructure.R
import com.mte.baseinfrastructure.base_structure.base.base_activity.IWrapEmptyData
import com.mte.baseinfrastructure.databinding.DefaultEmptyDataLayoutBinding


class DefaultWrapEmptyData(val context: Context) : IWrapEmptyData {

    private var databinding: DefaultEmptyDataLayoutBinding? = null

    var layoutId : Int = R.layout.default_empty_data_layout

    override fun addEmptyView(root: ViewGroup?, msge: String?) {
        databinding =  DataBindingUtil.bind(LayoutInflater.from(context).inflate(layoutId, null) )
        root?.removeAllViews()
        databinding?.text = msge
        root?.addView(databinding?.root)
    }

    override fun addEmptyView(root: ViewGroup?) {
        databinding =  DataBindingUtil.bind(LayoutInflater.from(context).inflate(layoutId, null) )
        root?.removeAllViews()
        databinding?.text = "No Data Found"
        root?.addView(databinding?.root)
    }
}