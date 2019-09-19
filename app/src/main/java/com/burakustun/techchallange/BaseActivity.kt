package com.burakustun.techchallange

import androidx.appcompat.app.AppCompatActivity
import com.burakustun.core.utils.LottieProgress

/**
 * Created by burakustun on 2019-09-19
 */
abstract class BaseActivity : AppCompatActivity() {

    protected var progressBar: LottieProgress? = null

    fun showProgress(){
        if (progressBar == null){
            progressBar = LottieProgress(this)
        }
        progressBar?.show()
    }

    fun hideProgress(){
        progressBar?.hide()
    }

    override fun onDestroy() {
        progressBar?.dismiss()
        super.onDestroy()
    }

}