package com.mte.infrastructurebase.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.mte.infrastructurebase.base.base_activity.OnRetryClick
import com.mte.infrastructurebase.data.source.remote.Resource
import com.mte.infrastructurebase.data.source.remote.Status
import com.mte.infrastructurebase.databinding.DefaultEmptyDataLayoutBinding
import com.mte.infrastructurebase.databinding.DefaultErrorLayoutBinding
import com.mte.infrastructurebase.R

abstract class BaseRVAdapter<T , BINDING : ViewDataBinding>(val context: Context?, var resource: Resource<ArrayList<T>? , *>? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    open val emptyDataText: String? = "No Data Found"
    open val retryAgainText: String? = "Retry"
    open val errorMessage : String? = "an error has occurred"

    @get:LayoutRes
    open val loadingLayout : Int = com.mte.infrastructurebase.R.layout.layout_loading_dialog_default

    @get:LayoutRes
    open val emptylayout : Int = R.layout.default_empty_data_layout

    @get:LayoutRes
    open val errorLayout : Int = R.layout.default_error_layout

    @get:LayoutRes
    abstract val  itemLayoutRes : Int



    open fun createDataViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return DataItemVH(LayoutInflater.from(parent.context).inflate(itemLayoutRes, parent, false))
    }

    protected abstract fun bindDataViewHolder(binding : BINDING? , item : T? , position: Int)

    protected open fun bindEmptyViewHolder(
        holder: BaseRVAdapter<* , *>.EmptyItem,
        resource: Resource<ArrayList<T>? , *>?){

        holder.bind(resource)
    }

    protected open fun bindErrorViewHolder(
        holder:  BaseRVAdapter<* , *>.ErrorItem,
        resource: Resource<ArrayList<T>?, *>?){

        holder.bind(resource)
    }


    protected abstract fun onRetry()

    open fun createLoadingViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return LoadingItem(LayoutInflater.from(parent.context).inflate(loadingLayout, parent, false))
    }

    open fun createErrorViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ErrorItem(LayoutInflater.from(parent.context).inflate(errorLayout, parent, false))
    }

    open fun createEmptyViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return EmptyItem(LayoutInflater.from(parent.context).inflate(emptylayout, parent, false))
    }


    fun submitData(data: Resource<List<*>?, *>?) {
        data?.let {
            resource = it as Resource<ArrayList<T>? , *>?
            notifyDataSetChanged()
            if (data.status == Status.ERROR) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_DATA -> createDataViewHolder(parent)
            TYPE_LOADING -> createLoadingViewHolder(parent)
            TYPE_ERROR -> createErrorViewHolder(parent)
            TYPE_EMPTY -> createEmptyViewHolder(parent)
            else -> error("Unknown viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder){
            is BaseRVAdapter<* , *>.ErrorItem -> { bindErrorViewHolder(holder  , resource)}
            is BaseRVAdapter<* , *>.LoadingItem -> {}
            is BaseRVAdapter<* , *>.EmptyItem -> { bindEmptyViewHolder(holder , resource)}
            else ->bindDataViewHolder(DataBindingUtil.bind<BINDING>(holder .itemView), getItem(position), position)
        }
    }

    open fun getItem(position: Int): T? {
        return resource?.data?.get(position)
    }


    override fun getItemCount(): Int {
        if (getItems().isNullOrEmpty()) {
            return when (resource?.status ?:  Status.EMPTY  ) {
                Status.LOADING,
                Status.ERROR,
                Status.SUCCESS,
                Status.EMPTY -> 1
            }
        }

        return getItems()?.size ?: 0
    }

    open fun getItems(): List<T?>? {
        return resource?.data
    }

    override fun getItemViewType(position: Int): Int {
        if (getItems().isNullOrEmpty()) {
            return when (resource?.status?:  Status.EMPTY ) {
                Status.LOADING -> TYPE_LOADING
                Status.ERROR -> TYPE_ERROR
                Status.SUCCESS -> TYPE_EMPTY
                Status.EMPTY -> TYPE_EMPTY
            }
        }

        return TYPE_DATA
    }

    companion object {
        const val TYPE_DATA = 0
        const val TYPE_ERROR = 1
        const val TYPE_LOADING = 2
        const val TYPE_EMPTY = 3
    }

    inner class LoadingItem(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class ErrorItem(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(resource: Resource<* , *>?) {
            val binding = DataBindingUtil.bind<DefaultErrorLayoutBinding>(itemView)
            binding?.text = resource?.message?: errorMessage
            binding?.tryAgainText = retryAgainText
            binding?.iTryClick = object : OnRetryClick {
                override fun onRetry() {
                    this@BaseRVAdapter.onRetry()
                }
            }
        }
    }



    inner class EmptyItem(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(resource: Resource<* , *>?) {
            val binding = DataBindingUtil.bind<DefaultEmptyDataLayoutBinding>(itemView)
            binding?.text = emptyDataText
        }
    }

    inner class DataItemVH(itemView: View) : RecyclerView.ViewHolder(itemView)
}