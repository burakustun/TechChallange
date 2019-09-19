package com.burakustun.techchallange

import android.app.Activity
import android.view.Window
import android.view.WindowManager

/**
 * Created by burakustun on 2019-09-19
 */

//sets theme for No action bar and requests full screen activity
fun Activity.noBarStyle() {
    setTheme(R.style.AppTheme_NoBar)
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
}