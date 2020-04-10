package com.mte.infrastructurebase.forms.fields_views.attachments.models

import android.net.Uri
import java.io.File

data class AttachmentModel (
    val filePath : String?,
    val fileUri : Uri?,
    val file : File? = null
    )