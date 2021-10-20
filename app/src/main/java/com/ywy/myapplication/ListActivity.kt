package com.ywy.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ywy.percentageview.PercentageView
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.activity_main.*

/**
 * CreateTime:2021-10-20
 * @Author:admin
 * Description: 列表模式
 **/
class ListActivity : AppCompatActivity() {
    private val arrayListOf = arrayListOf(81f, 22f, 3f, 49f, 51f, 16f, 75f, 8f, 79f, 100f)
    private  val  mAdapter by lazy {
        MyAdapter(arrayListOf)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        initView()
    }

    private fun initView() {
        mRv.apply {
            adapter =  mAdapter
            layoutManager = LinearLayoutManager(this@ListActivity)
        }
    }
}


class MyAdapter(arrayListOf:ArrayList<Float>):BaseQuickAdapter<Float, BaseViewHolder>(R.layout.item_pv,arrayListOf){
    override fun convert(holder: BaseViewHolder, item: Float) {

        val view = holder.getView<PercentageView>(R.id.mSdkPercentageView2)
        view.setType(PercentageView.PVType.NO_CENTER)
        view.setData(item,100f)
    }
}