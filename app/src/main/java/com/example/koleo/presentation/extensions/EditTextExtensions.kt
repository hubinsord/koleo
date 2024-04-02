package com.example.koleo.presentation.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int,
        ) { /* nop */ }

        override fun onTextChanged(
            s: CharSequence?,
            start: Int,
            before: Int,
            count: Int,
        ) { /* nop */ }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}