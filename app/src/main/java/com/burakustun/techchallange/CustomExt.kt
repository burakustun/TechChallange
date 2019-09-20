package com.burakustun.techchallange

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView

/**
 * Created by burakustun on 2019-09-19
 */

//sets theme for No action bar and requests full screen activity
fun Activity.noBarStyle() {
    setTheme(R.style.AppTheme_NoBar)
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    this.window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN
    )
}

fun TextView.setText(string: String){
    Log.i("denme","deneme")
}