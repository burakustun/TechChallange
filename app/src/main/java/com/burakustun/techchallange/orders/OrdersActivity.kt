package com.burakustun.techchallange.orders

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.burakustun.core.extensions.navigate
import com.burakustun.core.extensions.showToast
import com.burakustun.core.utils.ClientPreferences
import com.burakustun.data.domain.OrderDomain
import com.burakustun.techchallange.BaseActivity
import com.burakustun.techchallange.R
import com.burakustun.techchallange.login.LoginActivity
import kotlinx.android.synthetic.main.activity_orders.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by burakustun on 2019-09-19
 */
class OrdersActivity : BaseActivity() {

    lateinit var adapter: OrdersRecyclerAdapter

    private val viewModel: OrdersViewModel by viewModel()

    private val clientPreferences: ClientPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        initOrdersRecycler()
        initObservers()
        initListeners()
        viewModel.getOrders()
    }

    private fun initOrdersRecycler() {
        adapter = OrdersRecyclerAdapter(this, viewModel.orders)
        rvOrders.adapter = adapter
        rvOrders.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun initListeners() {
        btnLogout.setOnClickListener {
            //ask confirmation for logout
            showLogoutDialog()
        }

        btnOrders.setOnClickListener {
            //refresh orders
            viewModel.getOrders()
        }
    }

    private fun showLogoutDialog() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setTitle(getString(R.string.dialog_logout_title))
        alertDialog.setMessage(getString(R.string.dialog_logout_message))
        alertDialog.setCancelable(false)
        alertDialog.setPositiveButton(getString(R.string.dialog_logout_positive_button_title)) { _, _ ->
            //set remember me false
            clientPreferences.setRememberMe(false)
            //redirect to login activity
            navigate(this@OrdersActivity, LoginActivity::class.java)
            //close this activity
            finish()
        }
        alertDialog.setNegativeButton(R.string.dialog_logout_negative_button_title) { dialogInterface, _ ->
            //dismiss dialog
            dialogInterface.dismiss()
        }
        alertDialog.show()
    }

    private fun initObservers() {
        viewModel.ordersLiveData.observe(this, Observer { response ->
            response.onSuccess {
                //hide loading animation
                hideProgress()
                //items added to order list refresh adapter
                adapter.notifyDataSetChanged()
            }.onLoading {
                //show loading animation
                showProgress()
            }
        })

        //observe for errors
        viewModel.errorLiveData.observe(this, Observer {
            //hide progress
            hideProgress()
            //show the error message
            showToast(it.error)
        })
    }
}