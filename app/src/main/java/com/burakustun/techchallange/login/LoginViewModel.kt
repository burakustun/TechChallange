package com.burakustun.techchallange.login

import com.burakustun.core.utils.ClientPreferences
import com.burakustun.core.viewmodel.BaseViewModel
import com.google.android.material.textfield.TextInputLayout

/**
 * Created by burakustun on 2019-09-19
 */

class LoginViewModel(private val clientPreferences: ClientPreferences) : BaseViewModel() {

    private val acceptedUserName = "kariyer"
    private val acceptedPassword = "2019ADev"

    fun validateInputs(vararg inputs: TextInputLayout): List<String> {

        val errors = mutableListOf<String>()

        inputs.forEach { input ->
            if (input.editText?.text.toString().isEmpty()) {
                errors.add(input.tag.toString())
            }
        }

        return errors.toList()
    }

    fun loginUser(userName: String, password: String): Boolean {
        return userName == acceptedUserName && password == acceptedPassword
    }

    fun setRememberUser(rememberMe: Boolean) {
        clientPreferences.setRememberMe(rememberMe)
    }

}