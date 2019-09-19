package com.burakustun.core.extensions


import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Created by burakustun on 2019-09-18
 */

fun Activity.navigate(from : Context, to : Class<*>) {
    startActivity(Intent(from,to))
}

fun Activity.navigateForResult(intent: Intent, requestCode: Int) {
    startActivityForResult(intent, requestCode)
}

fun Activity.replaceFragment(fragmentManager: FragmentManager, id: Int, fragment: Fragment) {
    fragmentManager.beginTransaction().replace(id, fragment, fragment.javaClass.simpleName).commit()
}

fun Activity.addFragment(fragmentManager: FragmentManager, id: Int, fragment: Fragment) {
    fragmentManager.beginTransaction().addToBackStack(fragment.javaClass.simpleName)
        .add(id, fragment).commit()
}
