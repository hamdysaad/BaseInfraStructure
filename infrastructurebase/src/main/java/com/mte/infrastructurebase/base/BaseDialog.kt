package com.mte.infrastructurebase.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.mte.infrastructurebase.base.base_activity.BaseActivity


abstract class BaseDialog<T : ViewDataBinding> : DialogFragment() {

    protected  var alertDialog: AlertDialog?= null
    lateinit var binding: T

    @get:StringRes
    protected open val titleRes: Int = -1

    @get:LayoutRes
    abstract val  layoutId: Int

    open fun isFullWidth(): Boolean = false

    abstract fun initUI(savedInstanceState: Bundle?)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        alertDialog =  createDialog()

        return alertDialog ?: super.onCreateDialog(savedInstanceState)
    }

    open fun createDialog(): AlertDialog? {

        val alertBuilder =  AlertDialog.Builder(activity!!)

        if(titleRes != -1) alertBuilder.setTitle(titleRes)

        alertDialog = alertBuilder.create()

        return alertDialog
    }

    open fun setTitle(title : String){
        alertDialog?.setTitle(title)
    }

    open fun setTitle(title : Int){
        alertDialog?.setTitle(title)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        alertDialog?.window?.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT))
        alertDialog?.setView(binding.root)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI(savedInstanceState)
    }

    override fun show(fragManager: FragmentManager, tag: String?) {
        val transaction = fragManager.beginTransaction()
        val prev: Fragment? = fragManager.findFragmentByTag(tag)
        prev?.let { transaction.remove(it) }
        transaction.addToBackStack(null)
        super.show(transaction, tag)
    }

    @Throws()
    open fun dismissDialog(fragManager: FragmentManager, tag: String) {
        val frag: Fragment? = fragManager.findFragmentByTag(tag)
        frag?.let {
            fragManager.beginTransaction()
                    .disallowAddToBackStack()
                    .remove(it)
                    .commitAllowingStateLoss()
        }
    }

    @Throws()
    open fun dismissDialog(fragManager: FragmentManager, tag: String, aniIn: Int, aniOut: Int) {
        val frag: Fragment? = fragManager.findFragmentByTag(tag)
        frag?.let {
            fragManager.beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(aniIn, aniOut)
                    .remove(it)
                    .commitAllowingStateLoss()
        }
    }


    fun showDialogLoading() {
        if (activity is BaseActivity<*>) {
            (activity as BaseActivity<*>).showDialogLoading()
        }
    }

    fun hideDialogLoading() {
        if (activity is BaseActivity<*>) {
            (activity as BaseActivity<*>).hideDialogLoading()
        }
    }



    fun hideKeyboard() {
        if (dialog!!.currentFocus != null) {
            val inputManager: InputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                dialog!!.currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }}


    /**
     * show message dialog
     */
    fun showInfoMsgDialog(msg : String? , title : String? = null) {
        if(msg == null) return

        activity?.let {
            if (it is BaseActivity<*>) {
                it.showInfoMsgDialog(msg , title)
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
        positiveBtn : String? ) {

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
        positiveBtn : String,
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
}