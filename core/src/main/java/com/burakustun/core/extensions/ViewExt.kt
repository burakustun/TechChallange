package com.burakustun.core.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * Created by burakustun on 2019-09-18
 */

fun View.showSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisibile() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}