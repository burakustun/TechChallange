package com.burakustun.core.utils

import android.content.Context

/**
 * Created by burakustun on 2019-09-18
 */

class ClientPreferences(context: Context) : PreferencesManager(context) {

    private val KEY_REMEMBER_ME = "REMEMBER_ME"

    fun isRememberMe() : Boolean {
        return getBooleanValue(KEY_REMEMBER_ME)
    }

    fun setRememberMe(rememberMe : Boolean) {
        putBoolean(KEY_REMEMBER_ME,rememberMe)
    }

}