package com.demo.study.list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.study.R
import java.util.Collections

/**
 * create on 2024/7/22
 * @author chenglong
 * description :
 */
class RvListAdapter : RecyclerView.Adapter<RvListAdapter.RvListViewHolder>(),ItemTouchMoveListener{

    private val dates = mutableListOf<String>()

    inner class RvListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvDelete: TextView = itemView.findViewById(R.id.tv_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvListViewHolder {
        return RvListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_list, parent, false))
    }

    override fun getItemCount(): Int {
        return dates.size
    }

    override fun onBindViewHolder(holder: RvListViewHolder, position: Int) {
        holder.tvName.text = dates[position]
    }

    fun setDates(beans: List<String>) {
        dates.clear()
        dates.addAll(beans)
        notifyDataSetChanged()
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(dates, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemRemove(position: Int): Boolean {
        dates.removeAt(position)
        notifyItemRemoved(position)
        return true
    }

    override fun onItemTranslate(dx: Float, viewHolder: RecyclerView.ViewHolder) {
        if (viewHolder is RvListViewHolder) {
            viewHolder.tvDelete.visibility = View.VISIBLE
            if (Math.abs(dx)<viewHolder.tvDelete.width){
                viewHolder.itemView.scrollTo(-dx.toInt(), 0)
            }else{
                if (dx < 0) {
                    viewHolder.itemView.scrollTo(viewHolder.tvDelete.width, 0)
                } else {
                    viewHolder.itemView.scrollTo(-viewHolder.tvDelete.width, 0)
                }
            }
        }
    }

    override fun onItemReset(viewHolder: RecyclerView.ViewHolder) {
        viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT)
        viewHolder.itemView.scrollTo(0, 0)
        if (viewHolder is RvListViewHolder) {
            viewHolder.tvDelete.visibility = View.INVISIBLE
        }
    }
}