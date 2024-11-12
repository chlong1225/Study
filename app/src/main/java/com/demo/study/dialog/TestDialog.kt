package com.demo.study.dialog

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.chl.common.utils.ScreenUtil
import com.demo.study.R
import com.demo.study.databinding.DialogTestBinding
import com.demo.study.list.RvListAdapter

/**
 * create on 2024/11/11
 * @author chenglong
 * description :
 */
class TestDialog : DialogFragment() {

    companion object{

        const val TEST_DIALOG_TAG = "test"
    }

    private lateinit var binding: DialogTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DialogTestBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setCanceledOnTouchOutside(true)
        val adapter = RvListAdapter()
        if (binding.rvList.layoutManager == null) {
            binding.rvList.layoutManager = LinearLayoutManager(activity)
        }
        binding.rvList.adapter = adapter
        val beans = mutableListOf<String>()
        val count = 3
        for (i in 1 ..  count) {
            beans.add("第${i}条数据")
        }
        val params = binding.rvList.layoutParams
        if (count > 8) {
            params.height = ScreenUtil.dp2px(400f)
        } else {
            params.height = LayoutParams.WRAP_CONTENT
        }
        binding.rvList.layoutParams = params
        adapter.setDates(beans)
        binding.tvCompleted.setOnClickListener {
            val fragment = childFragmentManager.findFragmentByTag(TEST_DIALOG_TAG)
            if (fragment != null) {
                childFragmentManager.beginTransaction().remove(fragment).commit()
            }
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        val params = dialog?.window?.attributes
        params?.let {
            it.width = WindowManager.LayoutParams.MATCH_PARENT
            it.height = WindowManager.LayoutParams.WRAP_CONTENT
            it.gravity = Gravity.BOTTOM
            dialog?.window?.attributes = it
        }
    }
}