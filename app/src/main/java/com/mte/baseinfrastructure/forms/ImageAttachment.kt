package com.mte.baseinfrastructure.forms

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.widget.ImageView
import androidx.databinding.InverseBindingListener
import com.mte.infrastructurebase.forms.FormField
import com.mte.infrastructurebase.forms.fields_views.attachments.OnAttachmentSelectedListener
import com.mte.infrastructurebase.forms.fields_views.attachments.image_from_gallary.ImageFromGallary
import com.mte.infrastructurebase.forms.fields_views.attachments.models.AttachmentModel
import com.mte.infrastructurebase.forms.interfaces.IFieldView

class ImageAttachment(context: Context , attributeSet: AttributeSet? = null) : ImageView(context , attributeSet) , IFieldView<AttachmentModel?> {


    private var imageFromGallary: ImageFromGallary? = null
    private var selectedImageAttach: AttachmentModel? = null

    val formField = FormField(this)

    var activity : Activity? = null
        set(value) {
            field = value
            imageFromGallary?.activity = field
        }


    init {

        imageFromGallary = ImageFromGallary(
            activity,
            false,
            object : OnAttachmentSelectedListener{
                override fun onAttachmentsSelected(attachments: List<AttachmentModel?>) {
                    selectedImageAttach = attachments[0]
                    setValue(selectedImageAttach)
                    formField.attrChangeListener?.onChange()
                }

            }
        )

        imageFromGallary?.RESULT_CODE = 321

        setOnClickListener {
            imageFromGallary?.openSAF()
        }
    }


    override fun isValid(): Boolean {
        return formField.isValid()
    }

    override fun getValidationMessage(): String? {
       return formField.getValidationMessage()
    }

    override fun setValue(value: AttachmentModel?) {
       setImageURI(value?.fileUri)
    }

    override fun getValue(): AttachmentModel? {
        return selectedImageAttach
    }

    override fun setAttrChange(attrChange: InverseBindingListener) {
        formField.setAttrChange(attrChange)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        imageFromGallary?.onActivityResult(requestCode , resultCode , data)
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        imageFromGallary?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


}