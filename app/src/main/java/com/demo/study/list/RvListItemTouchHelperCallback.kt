package com.demo.study.list

import android.graphics.Canvas
import android.graphics.Color
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.chl.common.utils.LogUtil

/**
 * create on 2024/7/22
 * @author chenglong
 * description :
 */
class RvListItemTouchHelperCallback(private var itemTouchMoveListener: ItemTouchMoveListener) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun isLongPressDragEnabled(): Boolean {
        return super.isLongPressDragEnabled()
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        itemTouchMoveListener.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        itemTouchMoveListener.onItemRemove(viewHolder.adapterPosition)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        LogUtil.e("AAAA", "onChildDraw : dx = $dX ;; dy = $dY ;; state = $actionState ;; ${ItemTouchHelper.ACTION_STATE_IDLE}")
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            itemTouchMoveListener.onItemTranslate(dX, viewHolder)
        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        LogUtil.e("AAAA", "onSelectedChanged : state = $actionState")
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder?.itemView?.setBackgroundColor(Color.parseColor("#33ff0000"))
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        itemTouchMoveListener.onItemReset(viewHolder)
        super.clearView(recyclerView, viewHolder)
        LogUtil.e("AAAA", "clearView : width = ${viewHolder.itemView.width}")
    }
}