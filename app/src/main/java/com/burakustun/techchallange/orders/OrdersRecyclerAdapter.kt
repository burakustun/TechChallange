package com.burakustun.techchallange.orders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.burakustun.data.domain.OrderDomain
import com.burakustun.data.domain.ProductState
import com.burakustun.techchallange.R


/**
 * Created by burakustun on 2019-09-19
 */
class OrdersRecyclerAdapter(
    private val context: Context,
    private val orderList: List<OrderDomain>
) : RecyclerView.Adapter<OrdersRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_order, parent, false))
    }

    override fun getItemCount(): Int = orderList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(orderList, position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDate = itemView.findViewById<TextView>(R.id.tvDate)
        private val tvMonth = itemView.findViewById<TextView>(R.id.tvMonth)
        private val tvMarketName = itemView.findViewById<TextView>(R.id.tvMarketName)
        private val tvOrderName = itemView.findViewById<TextView>(R.id.tvOrderName)
        private val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        private val statusBox = itemView.findViewById<View>(R.id.statusBox)
        private val tvOrderStatus = itemView.findViewById<TextView>(R.id.tvOrderStatus)
        private val clDetailSection = itemView.findViewById<ConstraintLayout>(R.id.clDetailSection)
        private val tvOrderDetail = itemView.findViewById<TextView>(R.id.tvOrderDetail)
        private val tvPrice2 = itemView.findViewById<TextView>(R.id.tvPrice2)

        fun bind(orderList: List<OrderDomain>, position: Int) {

            val currentItem = orderList[position]

            setInformations(currentItem)
            initListeners(currentItem)
        }

        private fun initListeners(currentItem: OrderDomain) {
            itemView.setOnClickListener {

                //animate view related to flag state
                if (currentItem.isDetailSectionShowing) {
                    clDetailSection.animate().alpha(0f)
                        .setInterpolator(AccelerateDecelerateInterpolator()).duration = 1000
                } else {
                    clDetailSection.animate().alpha(1f)
                        .setInterpolator(AccelerateDecelerateInterpolator()).duration = 1000
                }

                //mutate the flag
                currentItem.isDetailSectionShowing = !currentItem.isDetailSectionShowing
            }
        }

        private fun setInformations(currentItem: OrderDomain) {

            //set texts
            tvDate.text = currentItem.date
            tvMonth.text = currentItem.month
            tvMarketName.text = currentItem.marketName
            tvOrderName.text = currentItem.orderName
            tvPrice.text = "${currentItem.productDetail.summaryPrice} TL"
            tvPrice2.text = "${currentItem.productPrice} TL"
            tvOrderStatus.text = currentItem.productState.code
            tvOrderDetail.text = currentItem.productDetail.orderDetail

            //decide which color to show and set
            var color = 0
            when (currentItem.productState) {
                ProductState.PREPARING -> color = ContextCompat.getColor(context, R.color.sun)
                ProductState.ON_THE_ROAD -> color = ContextCompat.getColor(context, R.color.apple)
                ProductState.WAIT_FOR_APPROVAL -> color =
                    ContextCompat.getColor(context, R.color.colorPrimary)
                else -> {
                }
            }
            statusBox.setBackgroundColor(color)
            tvOrderStatus.setTextColor(color)

            //set detail section alpha related to show flag
            if (currentItem.isDetailSectionShowing) {
                clDetailSection.alpha = 1f
            } else {
                clDetailSection.alpha = 0f
            }
        }
    }
}