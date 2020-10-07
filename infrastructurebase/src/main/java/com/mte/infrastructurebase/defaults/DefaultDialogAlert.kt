package com.mte.infrastructurebase.defaults

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.mte.infrastructurebase.base.base_activity.IDialogAlert

class DefaultDialogAlert(
    val context: Context,
    val okBtnText: String,
    val yesBtnText: String,
    val noBtnText: String
) : IDialogAlert {


    private var builder: AlertDialog.Builder? = null
    private var messageDialog: AlertDialog? = null
    var themeId: Int = -1

    var isCancelable: Boolean = false


    init {
        init()
    }


    private fun init() {

        builder = if (themeId != -1)
            AlertDialog.Builder(context, themeId) else AlertDialog.Builder(context)

        builder?.setCancelable(isCancelable)

    }

    private fun showDialog(
        msg: String?,
        title: String?,
        positiveBtn : String? = null,
        negativeBtn : String? = null,
        positiveBtnHandler : (() -> Unit) ? = null,
        negativeBtnHandler : (() -> Unit) ? = null
    ) {


        msg ?: return

        if (messageDialog?.isShowing == true) return

        builder?.setTitle(title)
        builder?.setMessage(msg)

        //Yes Action
        builder?.setPositiveButton(positiveBtn) { dialog, which ->
            dialog.dismiss()
            positiveBtnHandler?.invoke()
        }


        //negative btn Action
        negativeBtn.let { it ->
            builder?.setNegativeButton(it) { dialog, which ->
                dialog.dismiss()
                negativeBtnHandler?.invoke()
            }
        }


        messageDialog = builder?.create()
        messageDialog?.show()
    }

    override fun showInfoMsg(msg: String, title: String? ,   positiveBtn: String?) {
       showDialog(msg , title , okBtnText)
    }

    override fun showWarningMsg(
        msg: String,
        title: String?,
        positiveBtn: String?,
        negativeBtn: String?,
        positiveBtnHandler: (() -> Unit)?,
        negativeBtnHandler: (() -> Unit)?
    ) {
        showDialog(msg , title , positiveBtn ?: okBtnText  , negativeBtn , positiveBtnHandler  , negativeBtnHandler )
    }

    override fun showErrorMsg(
        msg: String,
        title: String?,
        positiveBtn: String?,
        negativeBtn: String?,
        positiveBtnHandler: (() -> Unit)?,
        negativeBtnHandler: (() -> Unit)?
    ) {
        showDialog(msg , title , positiveBtn ?: okBtnText, negativeBtn , positiveBtnHandler  , negativeBtnHandler )
    }

    override fun showSuccessMsg(msg: String?, title: String?, positiveBtnHandler: (() -> Unit)? , positiveBtn: String?) {
        showDialog(msg , title , positiveBtn ?: okBtnText , null , positiveBtnHandler )
    }

    override fun showConfirmationMsg(
        msg: String,
        title: String?,
        positiveBtn: String?,
        negativeBtn: String?,
        positiveBtnHandler: (() -> Unit)?,
        negativeBtnHandler: (() -> Unit)? ) {

        showDialog(msg , title , positiveBtn ?: yesBtnText , negativeBtn ?: noBtnText , positiveBtnHandler  , negativeBtnHandler )
    }


}