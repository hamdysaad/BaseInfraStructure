package com.mte.infrastructurebase.forms.fields_views.attachments.image_from_camera

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import com.mte.infrastructurebase.forms.fields_views.attachments.BaseAttachment


class ImageBitmapFromCamera(activity: Activity,
                            isMulti : Boolean,
                            var onTakePhotoListener : OnTakeBitmapPhotoListener? = null
) : BaseAttachment(activity , isMulti) {


    override var RESULT_CODE: Int = 1246

    override fun dispatchIntent() {
        dispatchPictureIntent()
    }

    private fun dispatchPictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(activity?.packageManager!!)?.also {
                activity?.startActivityForResult(takePictureIntent, RESULT_CODE)
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RESULT_CODE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            onTakePhotoListener?.OnTakePhoto(imageBitmap)
        }

    }

    override fun createIntent(): Intent? = null
}