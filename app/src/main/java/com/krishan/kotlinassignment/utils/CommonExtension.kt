package com.krishan.kotlinassignment.utils

import android.content.Context
import android.widget.Toast

object CommonExtension {

    fun Context.myToast(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

}