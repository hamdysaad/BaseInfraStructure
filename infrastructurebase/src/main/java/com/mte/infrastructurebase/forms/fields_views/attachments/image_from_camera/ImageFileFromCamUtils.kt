package com.mte.infrastructurebase.forms.fields_views.attachments.image_from_camera

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils

object ImageFileFromCamUtils {




    @JvmStatic
    fun getThumbnailBitmap(path : String): Bitmap? {


        val THUMBSIZE = 200

        return ThumbnailUtils.extractThumbnail(
            BitmapFactory.decodeFile(path),
            THUMBSIZE,
            THUMBSIZE
        )
    }
}