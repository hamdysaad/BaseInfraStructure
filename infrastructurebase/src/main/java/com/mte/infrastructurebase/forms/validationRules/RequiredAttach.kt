package com.mte.infrastructurebase.forms.validationRules

import com.mte.infrastructurebase.forms.fields_views.attachments.models.AttachmentModel
import com.mte.infrastructurebase.forms.interfaces.IRule

class RequiredAttach(val message: String?) : IRule<AttachmentModel> {

    override fun validate(value: AttachmentModel?): String? {

        return if(value?.fileUri == null)
            message
        else
            null


    }

}