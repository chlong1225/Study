package com.demo.study.list

import androidx.recyclerview.widget.RecyclerView

/**
 * create on 2024/7/22
 * @author chenglong
 * description :
 */
interface ItemTouchMoveListener {

    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean

    fun onItemRemove(position: Int): Boolean

    fun onItemTranslate(dx: Float, viewHolder: RecyclerView.ViewHolder)

    fun onItemReset(viewHolder:RecyclerView.ViewHolder)
}