package com.mte.infrastructurebase.forms.fields_views.attachments.image_from_gallary

import android.app.Activity
import android.content.Intent
import com.mte.infrastructurebase.attachments.OnAttachmentSelectedListener
import com.mte.infrastructurebase.forms.fields_views.attachments.BaseAttachment


class ImageAttachment(activity: Activity?= null,
                      isMulti : Boolean = false,
                      onAttachmentSelectedListener : OnAttachmentSelectedListener? = null
) : BaseAttachment(activity , isMulti , onAttachmentSelectedListener) {

    override var RESULT_CODE:Int =  234

    override fun createIntent(): Intent? {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"

        return intent

    }
}