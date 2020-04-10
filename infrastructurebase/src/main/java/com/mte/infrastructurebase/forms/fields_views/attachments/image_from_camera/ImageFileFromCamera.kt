package com.mte.infrastructurebase.forms.fields_views.attachments.image_from_camera

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import com.mte.infrastructurebase.forms.fields_views.attachments.BaseAttachment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Your name on 2/5/2020.
 */
open class ImageFileFromCamera(activity: Activity,
                               isMulti : Boolean,
                               private val onTakePhotoListener : OnSelectImageCamListener? = null,
                               resultCode : Int = 1246,
                                val externalDirPath : String = Environment.DIRECTORY_PICTURES) : BaseAttachment(activity , isMulti) {


    private var currentPhotoPath: String? = null

    override var RESULT_CODE: Int = resultCode

    override fun dispatchIntent() {
        dispatchTakePictureIntent()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RESULT_CODE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = currentPhotoPath?.let { ImageFileFromCamUtils.getThumbnailBitmap(it) }
            onTakePhotoListener?.OnTakePhoto(imageBitmap ,  File(currentPhotoPath))
        }

    }


    override fun createIntent(): Intent? = null


    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    open fun createImageFile(): File? {

        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

        val imageFileName = "JPEG_" + timeStamp + "_"

        val storageDir: File? = activity?.getExternalFilesDir(externalDirPath)
        val image: File = File.createTempFile(
            imageFileName,  /* prefix */
            ".jpg",  /* suffix */
            storageDir /* directory */
        )

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.absolutePath

        return image
    }

    private fun dispatchTakePictureIntent() {

        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Ensure that there's a camera activity to handle the intent
        if (activity?.packageManager?.let { takePictureIntent.resolveActivity(it) } != null) {
            // Create the File where the photo should go
            var photoFile : File? = null
            try {
                photoFile = createImageFile()
            } catch ( ex : IOException) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                val  photoURI = activity?.let {
                    FileProvider.getUriForFile(
                        it,
                        activity?.applicationContext?.packageName.toString() + ".provider",
                        photoFile)
                }

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                activity?.startActivityForResult(takePictureIntent, RESULT_CODE)
            }
        }
    }
}