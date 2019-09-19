package com.burakustun.techchallange.login

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.burakustun.techchallange.R
import com.burakustun.techchallange.noBarStyle

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noBarStyle()
        setContentView(R.layout.activity_login)
    }
}
