package com.example.koleo.presentation.extensions

import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

fun View.hideKeyboard() =
    (context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.hideSoftInputFromWindow(windowToken, 0)