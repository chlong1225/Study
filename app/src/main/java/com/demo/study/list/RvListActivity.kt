package com.demo.study.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.study.BaseActivity
import com.demo.study.databinding.ActivityRvListBinding

/**
 * create on 2024/7/22
 * @author chenglong
 * description :
 */
class RvListActivity : BaseActivity<ActivityRvListBinding>() {

    companion object {

        fun openSelf(context: Context) {
            context.startActivity(Intent(context, RvListActivity::class.java))
        }
    }

    private val adapter = RvListAdapter()

    override fun buildViewBinding(): ActivityRvListBinding {
        return ActivityRvListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (getViewBinding().rvList.layoutManager == null) {
            getViewBinding().rvList.layoutManager = LinearLayoutManager(this)
            getViewBinding().rvList.adapter = adapter
        }

        val beans = mutableListOf<String>()
        for (i in 1 ..  100) {
            beans.add("第${i}条数据")
        }
        adapter.setDates(beans)

        val itemTouchHelper = ItemTouchHelper(RvListItemTouchHelperCallback(adapter))
        itemTouchHelper.attachToRecyclerView(getViewBinding().rvList)
        getViewBinding().rvList.addItemDecoration(RvItemDecoration(this))
    }

}