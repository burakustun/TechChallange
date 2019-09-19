package com.burakustun.techchallange.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.burakustun.core.extensions.navigate
import com.burakustun.core.extensions.showSnackbar
import com.burakustun.core.extensions.showToast
import com.burakustun.core.utils.ClientPreferences
import com.burakustun.techchallange.R
import com.burakustun.techchallange.noBarStyle
import com.burakustun.techchallange.orders.OrdersActivity
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel : LoginViewModel by viewModel()

    private val clientPreferences : ClientPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //call Kotlin extension for no status bar and toolbar visibility
        noBarStyle()
        setContentView(R.layout.activity_login)

        initListeners()

        if (clientPreferences.isRememberMe()){
            redirectToOrders()
        }
    }

    private fun initListeners() {
        btnLogin.setOnClickListener {

            resetErrors()
            //GET TAG OF EMPTY INPUTS
            val errors = viewModel.validateInputs(tilUserName,tilPassword)

            //IF NO ERROR
            if (errors.isNullOrEmpty()){
                if (viewModel.loginUser(etUserName.text.toString(),etPassword.text.toString())){
                    //save remember preference
                    viewModel.setRememberUser(swRemember.isChecked)
                    redirectToOrders()
                }
                else{
                    //SHOW WRONG INFO MESSAGE
                    btnLogin.showSnackbar(getString(R.string.error_login_fail))
                }
            }else{
                //SHOW ERRORS
                initializeErrors(errors)
            }
        }
    }

    private fun redirectToOrders() {
        navigate(this@LoginActivity,OrdersActivity::class.java)
        finish()
    }

    private fun resetErrors(){
        tilUserName.error = null
        tilPassword.error = null
    }

    private fun initializeErrors(errors: List<String>) {
        errors.forEach { tag ->
            //get view by tag
            val view : TextInputLayout = clRoot.findViewWithTag(tag)
            //set error text
            view.error = getString(R.string.error_required_field)
        }
    }
}
