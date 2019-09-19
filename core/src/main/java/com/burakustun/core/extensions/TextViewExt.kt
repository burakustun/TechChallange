package com.burakustun.core.extensions

import android.graphics.Paint
import android.widget.TextView

/**
 * Created by burakustun on 2019-09-18
 */

fun TextView.strikeThrough() = this.setPaintFlags(this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG)

fun TextView.underline() = this.setPaintFlags(paintFlags or Paint.UNDERLINE_TEXT_FLAG)

fun TextView.endDrawable(draw: Int) = this.setCompoundDrawablesWithIntrinsicBounds(0, 0, draw, 0)

fun TextView.clearDrawable() = this.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
