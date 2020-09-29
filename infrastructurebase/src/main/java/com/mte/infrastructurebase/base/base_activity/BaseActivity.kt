package com.mte.infrastructurebase.base.base_activity

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mte.infrastructurebase.base.BaseDialog
import com.mte.infrastructurebase.defaults.*
import com.mte.infrastructurebase.utils.KeyboardUtils
import com.mte.infrastructurebase.utils.LocaleHelper

abstract class BaseActivity<T : ViewDataBinding?> : AppCompatActivity() {


    protected var dialogLoading: IDialogLoading? = null
    protected var dialogAlert: IDialogAlert?      = null
    protected var wrapLoading: IWrapLoading?      = null
    protected var wrapError: IWrapError?          = null
    protected var wrapEmptyData: IWrapEmptyData?  = null


    var binding: T? = null

    @get:LayoutRes
    protected abstract val layoutRes: Int

    @get:StringRes
    protected open val okString: Int? = null
    protected open val yesString: Int? = null
    protected open val noString:  Int? = null

    protected abstract fun initUI(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        LocaleHelper.onAttach(this)

        super.onCreate(savedInstanceState)

        init()

        setActivityContentView(this)

        initUI(savedInstanceState)
    }

    open fun setActivityContentView(activity  : AppCompatActivity) {
        binding = DataBindingUtil.setContentView<T>(this , layoutRes)
        binding?.executePendingBindings()
    }

    protected fun inflateLayout(layout : Int): View? {
        return layoutInflater.inflate(
            layout,
            null,
            false)
    }

    open fun init() {
        dialogLoading   = DefaultDialogLoading(this)
        dialogAlert     = DefaultDialogAlert(this , okString?.let { getString(it) } ?: "ok",yesString?.let { getString(it) } ?: "Yes", noString?.let { getString(it) } ?: "No")
        wrapLoading     = DefaultWrapLoading(this)
        wrapError       = DefaultWrapError(this)
        wrapEmptyData   = DefaultWrapEmptyData(this)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        LocaleHelper.onAttach(this)
        super.onConfigurationChanged(newConfig)
    }



    /**
     * Show dialog loading
     */
    open fun showDialogLoading() {
        runOnUiThread {
            dialogLoading?.showLoading()
        }
    }


    /**
     * Hide dialog loading
     */
    open fun hideDialogLoading() {
        runOnUiThread {
            dialogLoading?.hideLoading()
        }
    }


    fun hideKeyboard() {
        KeyboardUtils.hideKeyboard(this)
    }

    fun hideKeyboardOutSide(view: View) {
        KeyboardUtils.hideKeyBoardWhenClickOutSide(view, this)
    }

    fun hideKeyboardOutSideText(view: View) {
        KeyboardUtils.hideKeyBoardWhenClickOutSideText(view, this)
    }


    fun <VH : RecyclerView.ViewHolder> setUpRcv(
        rcv: RecyclerView,
        adapter: RecyclerView.Adapter<VH>
    ) {
        rcv.setHasFixedSize(true)
        rcv.layoutManager = LinearLayoutManager(this)
        rcv.adapter = adapter
    }

    fun <VH : RecyclerView.ViewHolder> setUpRcv(
        rcv: RecyclerView, adapter:
        RecyclerView.Adapter<VH>,
        isHasFixedSize: Boolean,
        isNestedScrollingEnabled: Boolean
    ) {
        rcv.setHasFixedSize(isHasFixedSize)
        rcv.layoutManager = LinearLayoutManager(this)
        rcv.adapter = adapter
        rcv.isNestedScrollingEnabled = isNestedScrollingEnabled
    }

    fun <VH : RecyclerView.ViewHolder> setUpRcv(
        rcv: RecyclerView,
        adapter: RecyclerView.Adapter<VH>,
        isNestedScrollingEnabled: Boolean
    ) {
        rcv.setHasFixedSize(true)
        rcv.layoutManager = LinearLayoutManager(this)
        rcv.adapter = adapter
        rcv.isNestedScrollingEnabled = isNestedScrollingEnabled
    }

    open fun clearAllBackStack() {
        val fm = supportFragmentManager
        for (i in 0 until fm.backStackEntryCount) {
            fm.popBackStack()
        }
    }


    fun addFragment(fragment: Fragment, id: Int, addToBackStack: Boolean) {
        addFragment(supportFragmentManager , fragment , id , addToBackStack)
    }


    //Add Fragment by fragmentManager
    fun addFragment(fragmentManager : FragmentManager,
                    fragment : Fragment,
                    id: Int,
                    addToBackStack: Boolean) {

        if(supportFragmentManager.findFragmentByTag(fragment.javaClass.simpleName) != null) return

        val transaction = fragmentManager.beginTransaction()

        transaction.add(id, fragment, fragment.javaClass.simpleName)

        if (addToBackStack)
            transaction.addToBackStack(fragment.javaClass.simpleName)

        transaction.commitNow()

    }

    fun replaceFragment(fragment: Fragment, id: Int, addToBackStack: Boolean) {
        replaceFragment(supportFragmentManager , fragment , id , addToBackStack)
    }

    fun replaceFragment( fragmentManager : FragmentManager ,
                         fragment: Fragment,
                         id: Int,
                         addToBackStack: Boolean) {

        if(supportFragmentManager.findFragmentByTag(fragment.javaClass.simpleName) != null) return


        val transaction = fragmentManager.beginTransaction()


        if (addToBackStack)
            transaction.addToBackStack(fragment.javaClass.canonicalName)

        transaction.replace(id, fragment, fragment.javaClass.canonicalName)
        transaction.commitNow()
    }

    /**
     * show message dialog
     */
    fun showInfoMsgDialog(msg : String? , title : String? = null) {
        if(msg == null) return
        runOnUiThread { dialogAlert?.showInfoMsg(msg , title) }
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
        runOnUiThread { dialogAlert?.showSuccessMsg(msg , title , positiveBtnHandler , positiveBtn) }
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
            runOnUiThread { dialogAlert?.showErrorMsg(msg , title , positiveBtn , negativeBtn , positiveBtnHandler , negativeBtnHandler)
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
        runOnUiThread { dialogAlert?.showWarningMsg(msg , title , positiveBtn , negativeBtn , positiveBtnHandler , negativeBtnHandler) }
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
        runOnUiThread {
            dialogAlert?.showConfirmationMsg(msg , title , positiveBtn , negativeBtn , positiveBtnHandler , negativeBtnHandler)
        }
    }


    fun wrapingLoading(root: ViewGroup?) {
        runOnUiThread {
            wrapLoading?.addLoadingView(root)
        }

    }

    fun wrapinError(
        msg: String? = null,
        root: ViewGroup?,
        onRetryClick: (()->Unit)?  = null
    ) {

        runOnUiThread {
            wrapError?.addErrorView(root , msg  , object : OnRetryClick {
                override fun onRetry() {
                    onRetryClick?.invoke()
                }

            })
        }

    }

    fun wrapinEmptyData(
        msg: String? = null,
        root: ViewGroup?,
        onRetryClick: (()->Unit)?  = null
    ) {

        runOnUiThread {

            if(msg == null)
                wrapEmptyData?.addEmptyView(root)
            else wrapEmptyData?.addEmptyView(root , msg)
        }
    }


    fun openDialogFragment(dialogListFragment: BaseDialog<*>) {
        val ft = supportFragmentManager.beginTransaction()
        dialogListFragment.show(ft, "dialog")
    }

    fun showToast(msg : String? , duration : Int = Toast.LENGTH_SHORT){
        msg ?: return
        Toast.makeText(this , msg , duration).show()
    }


}