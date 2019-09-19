package com.burakustun.techchallange.orders

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.burakustun.core.extensions.navigate
import com.burakustun.core.extensions.showToast
import com.burakustun.core.utils.ClientPreferences
import com.burakustun.core.utils.LottieProgress
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

    lateinit var adapter : OrdersRecyclerAdapter

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
        rvOrders.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
    }

    private fun initListeners() {
        btnLogout.setOnClickListener {
            showLogoutDialog()
        }

        btnOrders.setOnClickListener {
            viewModel.getOrders()
        }
    }

    private fun showLogoutDialog() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setTitle(getString(R.string.dialog_logout_title))
        alertDialog.setMessage(getString(R.string.dialog_logout_message))
        alertDialog.setCancelable(false)
        alertDialog.setPositiveButton(getString(R.string.dialog_logout_positive_button_title)) { _, _ ->
            clientPreferences.setRememberMe(false)
            navigate(this@OrdersActivity, LoginActivity::class.java)
            finish()
        }
        alertDialog.setNegativeButton(R.string.dialog_logout_negative_button_title) { dialogInterface, _ ->
            dialogInterface.dismiss()
        }
        alertDialog.show()
    }

    private fun initObservers() {
        viewModel.ordersLiveData.observe(this, Observer { response ->
            response.onSuccess {
                hideProgress()
                adapter.notifyDataSetChanged()
            }.onLoading {
                showProgress()
            }
        })

        viewModel.errorLiveData.observe(this, Observer {
            hideProgress()
            showToast(it.error)
        })

    }
}