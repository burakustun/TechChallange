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

        val currentItem = orderList[position]

        holder.tvDate.text = currentItem.date
        holder.tvMonth.text = currentItem.month
        holder.tvMarketName.text = currentItem.marketName
        holder.tvOrderName.text = currentItem.orderName
        holder.tvPrice.text = "${currentItem.productDetail.summaryPrice} TL"
        holder.tvPrice2.text = "${currentItem.productPrice} TL"
        holder.tvOrderStatus.text = currentItem.productState.code
        holder.tvOrderDetail.text = currentItem.productDetail.orderDetail

        var color = 0

        when (currentItem.productState) {
            ProductState.PREPARING -> color = ContextCompat.getColor(context, R.color.sun)
            ProductState.ON_THE_ROAD -> color = ContextCompat.getColor(context, R.color.apple)
            ProductState.WAIT_FOR_APPROVAL -> color =
                ContextCompat.getColor(context, R.color.colorPrimary)
            else -> {
            }
        }

        holder.statusBox.setBackgroundColor(color)
        holder.tvOrderStatus.setTextColor(color)

        if (currentItem.isDetailSectionShowing) {
            holder.clDetailSection.alpha = 1f
        } else {
            holder.clDetailSection.alpha = 0f
        }

        holder.itemView.setOnClickListener {
            if (currentItem.isDetailSectionShowing) {
                holder.clDetailSection.animate().alpha(0f)
                    .setInterpolator(AccelerateDecelerateInterpolator()).duration = 1000
            } else {
                holder.clDetailSection.animate().alpha(1f)
                    .setInterpolator(AccelerateDecelerateInterpolator()).duration = 1000
            }

            currentItem.isDetailSectionShowing = !currentItem.isDetailSectionShowing
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDate = itemView.findViewById<TextView>(R.id.tvDate)
        val tvMonth = itemView.findViewById<TextView>(R.id.tvMonth)
        val tvMarketName = itemView.findViewById<TextView>(R.id.tvMarketName)
        val tvOrderName = itemView.findViewById<TextView>(R.id.tvOrderName)
        val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        val statusBox = itemView.findViewById<View>(R.id.statusBox)
        val tvOrderStatus = itemView.findViewById<TextView>(R.id.tvOrderStatus)
        val clDetailSection = itemView.findViewById<ConstraintLayout>(R.id.clDetailSection)
        val tvOrderDetail = itemView.findViewById<TextView>(R.id.tvOrderDetail)
        val tvPrice2 = itemView.findViewById<TextView>(R.id.tvPrice2)
    }
}