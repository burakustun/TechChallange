package com.burakustun.core.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieDrawable
import com.airbnb.lottie.LottieProperty
import com.burakustun.core.R
import kotlinx.android.synthetic.main.dialog_progress.*

/**
 * Created by burakustun on 2019-09-19
 */
class LottieProgress(context : Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_progress)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(false)

        lottie.setAnimation(R.raw.loading)
        lottie.repeatMode = LottieDrawable.RESTART
        lottie.repeatCount = LottieDrawable.INFINITE
        lottie.playAnimation()
    }
}