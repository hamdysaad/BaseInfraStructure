package com.mte.infrastructurebase.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mte.infrastructurebase.base.base_activity.BaseActivity

abstract class BaseFragment < D : ViewDataBinding>:Fragment() {

    var isShown: Boolean = false

    protected lateinit var binding: D


    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        initUI(savedInstanceState)
        binding.executePendingBindings()
        return binding.root
    }

    protected fun inflateLayout(layout : Int): View? {
        return layoutInflater.inflate(
            layout,
            null,
            false)
    }

    protected abstract fun initUI(savedInstanceState: Bundle?)

    fun <VH : RecyclerView.ViewHolder> setUpRcv(rcv: RecyclerView, adapter: RecyclerView.Adapter<VH>) {
        rcv.setHasFixedSize(true)
        rcv.layoutManager = LinearLayoutManager(context)
        rcv.adapter = adapter
    }

    fun <VH : RecyclerView.ViewHolder> setUpRcv(
        rcv: RecyclerView, adapter:
        RecyclerView.Adapter<VH>,
        isHasFixedSize: Boolean,
        isNestedScrollingEnabled: Boolean
    ) {
        rcv.setHasFixedSize(isHasFixedSize)
        rcv.layoutManager = LinearLayoutManager(context)
        rcv.adapter = adapter
        rcv.isNestedScrollingEnabled = isNestedScrollingEnabled
    }

    fun <VH : RecyclerView.ViewHolder> setUpRcv(
        rcv: RecyclerView, adapter:
        RecyclerView.Adapter<VH>,
        isNestedScrollingEnabled: Boolean
    ) {
        rcv.setHasFixedSize(true)
        rcv.layoutManager = LinearLayoutManager(context)
        rcv.adapter = adapter
        rcv.isNestedScrollingEnabled = isNestedScrollingEnabled
    }

    fun addFragment(
        fragment: Fragment,
        id: Int,
        addToBackStack: Boolean
    ) {

        val activity = activity
        if(activity is BaseActivity<*>)
            activity.addFragment(fragment , id , addToBackStack)
    }

    fun addFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        id: Int,
        addToBackStack: Boolean
    ) {

        val activity = activity
        if(activity is BaseActivity<*>)
            activity.addFragment(fragmentManager ,fragment , id , addToBackStack)
    }



    fun replaceFragment(
        fragment: Fragment,
        id: Int,
        addToBackStack: Boolean
    ) {

        val activity = activity
        if(activity is BaseActivity<*>)
            activity.replaceFragment(fragment , id , addToBackStack)
    }

    fun replaceFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        id: Int,
        addToBackStack: Boolean
    ) {

        val activity = activity
        if(activity is BaseActivity<*>)
            activity.replaceFragment(fragmentManager ,fragment , id , addToBackStack)
    }

    fun hideKeyboard() {
        activity?.let {
            if (it is BaseActivity<*>) {
                it.hideKeyboard()
            }
        }
    }

    fun showDialogLoading() {
        activity?.let {
            if (it is BaseActivity<*>) {
                it.showDialogLoading()
            }
        }
    }

    fun hideDialogLoading() {
        activity?.let {
            if (it is BaseActivity<*>) {
                it.hideDialogLoading()
            }
        }
    }



    /**
     * show message dialog
     */
    fun showInfoMsgDialog(msg : String? , title : String? = null ,  positiveBtn: String? = null) {
        if(msg == null) return

        activity?.let {
            if (it is BaseActivity<*>) {
                it.showInfoMsgDialog(msg , title , positiveBtn)
            }
        }
    }

    /**
     * show message dialog
     */
    fun showSuccessMsgDialog(
        msg : String? ,
        title : String? = null ,
        positiveBtnHandler : (() -> Unit) ? = null,
        positiveBtn : String? = null ) {

        if(msg == null) return

        activity?.let {
            if (it is BaseActivity<*>) {
                it.showSuccessMsgDialog(msg , title, positiveBtnHandler, positiveBtn)
            }
        }
    }

    /**
     * show Error message dialog
     */
    fun showErrorMsgDialog(
        msg : String? ,
        title : String? = null ,
        positiveBtn : String? = null,
        negativeBtn : String? = null,
        positiveBtnHandler : (() -> Unit) ? = null,
        negativeBtnHandler : (() -> Unit) ? = null

    ) {
        if(msg == null) return

        activity?.let {
            if (it is BaseActivity<*>) {
                it.showErrorMsgDialog(msg , title , positiveBtn , negativeBtn, positiveBtnHandler, negativeBtnHandler)
            }
        }
    }

    /**
     * show Warning message dialog
     */
    fun showWarningMsgDialog(
        msg : String? ,
        title : String? = null ,
        positiveBtn : String? = null,
        negativeBtn : String? = null,
        positiveBtnHandler : (() -> Unit) ? = null,
        negativeBtnHandler : (() -> Unit) ? = null
    ) {
        if(msg == null) return

        activity?.let {
            if (it is BaseActivity<*>) {
                it.showWarningMsgDialog(msg , title, positiveBtn, negativeBtn, positiveBtnHandler, negativeBtnHandler)
            }
        }
    }

    /**
     * Show confirm message dialog loading
     */
    fun showConfirmMessagDialog(
        msg : String? ,
        title : String? = null ,
        positiveBtnHandler : (() -> Unit) ? = null,
        negativeBtnHandler : (() -> Unit) ? = null,
        positiveBtn : String? = null,
        negativeBtn : String? = null
    ) {
        if(msg == null) return

        activity?.let {
            if (it is BaseActivity<*>) {
                it.showConfirmMessagDialog(msg , title, positiveBtnHandler, negativeBtnHandler, positiveBtn, negativeBtn )
            }
        }
    }


    fun openDialogFragment(dialogListFragment: BaseDialog<*>) {
        activity?.let {
            if (it is BaseActivity<*>) {
                it.openDialogFragment(dialogListFragment)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        isShown = false
    }


    override fun onResume() {
        super.onResume()

        isShown = true
    }


    fun wrapingLoading(root : ViewGroup?) {
        activity?.let {
            if (it is BaseActivity<*>) {
                it.wrapingLoading(root)
            }
        }
    }

    fun wrapingError(root : ViewGroup? ,
                     message : String?,
                     onRetryClick: (()->Unit)?  = null) {

        activity?.let {
            if (it is BaseActivity<*>) {
                it.wrapinError(message , root , onRetryClick )
            }
        }
    }

    fun wrapingEmtyData(
        root : ViewGroup? ,
        message : String?,
        onRetryClick: (()->Unit)?  = null) {
        activity?.let {
            if (it is BaseActivity<*>) {
                it.wrapinEmptyData(message , root , onRetryClick)
            }
        }
    }

    open fun onBackPressed(): Boolean {
        return false
    }


    fun showToast(msg : String? , duration : Int = Toast.LENGTH_SHORT){
        activity?.let {
            if (it is BaseActivity<*>) {
                it.showToast(msg , duration)
            }
        }
    }


}