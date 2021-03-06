package com.mte.infrastructurebase.forms.fields

import android.text.InputType
import com.mte.infrastructurebase.forms.interfaces.IRule

class DefaultEmailField(
    title : String,
    tag: String? = null,
    rules: List<IRule<String>>? = ArrayList(),
    model: ((String?) -> Unit)? = null,
    hint: String? = null,
    lines: Int = 1,
    inputType: Int = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
) : DefaultTextField(title , tag , rules , model , hint , lines , inputType)