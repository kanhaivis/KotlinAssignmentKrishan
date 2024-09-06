package com.krishan.kotlinassignment.utils

import android.app.Activity
import android.app.AlertDialog
import com.krishan.kotlinassignment.callbackmethod.AlertCallBack

object AppAlertDialog {

    fun onShowAlert(activity: Activity, message: String, callBack: AlertCallBack) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(message)
        builder.setCancelable(false)
        builder.setPositiveButton("Cancel") { _, _ -> callBack.onCancel()}
        builder.setNeutralButton("Retry") { _, _ -> callBack.onRetry()}

        val alertDialog: AlertDialog = builder.create()

        alertDialog.show()
    }
}