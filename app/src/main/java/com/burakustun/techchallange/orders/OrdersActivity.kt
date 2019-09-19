package com.burakustun.techchallange.orders

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.burakustun.core.extensions.showToast
import com.burakustun.data.domain.ProductState
import com.burakustun.techchallange.R
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by burakustun on 2019-09-19
 */
class OrdersActivity : AppCompatActivity() {

    private val viewModel: OrdersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        initObservers()
        initListeners()
        viewModel.getOrders()
    }

    private fun initListeners() {
        
    }

    private fun initObservers() {
        viewModel.ordersLiveData.observe(this, Observer { response ->
            response.onSuccess {
                showToast("data")
                //TODO HIDE PROGRESS && SET RECYCLER ADAPTER
            }.onLoading {
                //TODO SHOW PROGRESS
            }
        })

        viewModel.errorLiveData.observe(this, Observer {
            //TODO HIDE PROGRESS AND SHOW ERROR
        })

    }
}