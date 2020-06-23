package com.mte.infrastructurebase.forms.fields_views.attachments

import com.mte.infrastructurebase.forms.fields_views.attachments.models.AttachmentModel

interface OnAttachmentSelectedListener {

    fun onAttachmentsSelected(attachments: List<AttachmentModel?>)
}