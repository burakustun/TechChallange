package com.burakustun.core.extensions

import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.core.content.ContextCompat

/**
 * Created by burakustun on 2019-09-18
 */


fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, duration).show()

fun Context.showDialog(title: String, message: String) =
    AlertDialog.Builder(this).apply {
        setTitle(title)
        setMessage(message)
        show()
    }

fun Context.showDialogNonCancelable(title: String, message: String) =
    AlertDialog.Builder(this).apply {
        setTitle(title)
        setMessage(message)
        setCancelable(false)
        setPositiveButton("TAMAM") { dialog, _ -> dialog?.dismiss() }
        show()
    }

fun Context.drawable(resID: Int): Drawable? {
    return ContextCompat.getDrawable(this,resID)
}

fun Context.color(resID: Int): Int {
    return ContextCompat.getColor(this,resID)
}

fun Context.isNetworkAvailable(): Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = cm.activeNetworkInfo

    if (netInfo != null && netInfo.isConnected) {
        return true
    }
    return false
}