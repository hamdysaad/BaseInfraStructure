package com.mte.infrastructurebase.base.base_activity


interface IDialogAlert {

    fun showInfoMsg(msg : String , title : String? = null ,   positiveBtn: String? = null)

    fun showWarningMsg(
        msg : String ,
        title : String? = null ,
        positiveBtn : String?,
        negativeBtn : String ? = null,
        positiveBtnHandler : (() -> Unit) ? = null,
        negativeBtnHandler : (() -> Unit) ? = null)

    fun showErrorMsg(
        msg : String ,
        title : String? = null ,
        positiveBtn : String?,
        negativeBtn : String? = null,
        positiveBtnHandler : (() -> Unit) ? = null,
        negativeBtnHandler : (() -> Unit) ? = null
    )

    fun showSuccessMsg(msg : String? , title : String? = null, positiveBtnHandler: (() -> Unit)? , positiveBtn: String?)

    fun showConfirmationMsg( msg : String ,
                             title : String? = null ,
                             positiveBtn : String?,
                             negativeBtn : String?,
                             positiveBtnHandler : (() -> Unit) ? = null,
                             negativeBtnHandler : (() -> Unit) ? = null)

}