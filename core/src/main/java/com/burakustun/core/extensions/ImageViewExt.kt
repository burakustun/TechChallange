package com.burakustun.core.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by burakustun on 2019-09-18
 */


fun ImageView.loadImage(url: String?) = Picasso.get().load(url).into(this)

fun ImageView.loadImageWithPlaceHolder(url: String?, placeHolder: Int) {
    Picasso.get()
        .load(url)
        .placeholder(placeHolder)
        .into(this)
}