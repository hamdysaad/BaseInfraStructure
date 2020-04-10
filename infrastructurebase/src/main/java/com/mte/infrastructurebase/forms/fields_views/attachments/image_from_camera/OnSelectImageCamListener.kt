package com.mte.infrastructurebase.forms.fields_views.attachments.image_from_camera

import android.graphics.Bitmap
import java.io.File

/**
 * Created by Your name on 2/5/2020.
 */
interface OnSelectImageCamListener {
    fun OnTakePhoto(file1: Bitmap?, file: File?)
}