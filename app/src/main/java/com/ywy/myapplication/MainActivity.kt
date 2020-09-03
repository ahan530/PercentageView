package com.ywy.myapplication

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ywy.percentageview.PercentageView
import com.ywy.percentageview.PercentageViewBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * 0:纯净模式
         */
        //1:有进度值，加个属性动画，从0开始不好看
        mPercentageView0.setType(PercentageView.Type.PURE)
        val ofFloat0 = ValueAnimator.ofFloat(10f, 75f)
        ofFloat0.duration = 3000
        ofFloat0.repeatCount = 0
        ofFloat0.addUpdateListener {
            val animatedValue = it.animatedValue as Float
            mPercentageView0.setData(animatedValue,100f)
            mPercentageView0.invaLidate()
        }
        ofFloat0.start()
        //2：无进度值
        mPercentageView01.setType(PercentageView.Type.PURE)
        mPercentageView01.setData(36f,100f)

        //3：极限值
        mPercentageView02.setType(PercentageView.Type.PURE)
        mPercentageView02.setData(3f,100f)

        /**
         * 普通模式
         */
        //1：直角带任意角度和任何宽度分割线
        mPercentageView1.mRadius = 0f
        val ofFloat = ValueAnimator.ofFloat(0f, 55f)
        ofFloat.duration = 2000
        ofFloat.repeatCount = 0
        ofFloat.repeatMode = ValueAnimator.REVERSE
        ofFloat.addUpdateListener {
            val animatedValue = it.animatedValue as Float
            mPercentageView1.setData(animatedValue,100f)
            mPercentageView1.invaLidate()
//            mPercentageView4.setData(animatedValue,100f)
//            mPercentageView4.invaLidate()
//
//            mPercentageView7.setData(animatedValue,100f)
//            mPercentageView7.invaLidate()
        }
        ofFloat.start()

        //1.1：极限值
        mPercentageView11.setData(3f,100f)



        //2:圆角无切割线
        mPercentageView2.setData(43.3f,100f)
        //2.1:圆角无切线极限值
        mPercentageView21.setData(3f,100f)


        //3：圆角带切割线无文字
        mPercentageView3.setData(55.5f,100f)
        //3.1:左极值
        mPercentageView31.setData(0f,100f)
        //3.2:右极值
        mPercentageView32.setData(100f,100f)


        //4：圆角切割线有文字
        mPercentageView4.setData(45.6f,100f)
        //4.1:左极值
        mPercentageView41.setData(0f,100f)
        //4.2：左极值（是否有极值-true）HaveLimiValue = true 则百分比进度值显示区域不够 自动隐藏
        mPercentageView42.setData(0f,100f)


        //5:直角带切割线有文字，有中间文字
        mPercentageView5.setData(67.5f,100f)
        //5.1:少
        mPercentageView51.setData(27f,100f)
        //5.2：平
        mPercentageView52.setData(50f,100f)
        //5.3：左极限
        mPercentageView53.setData(0f,100f)
        //5.4 右极限
        mPercentageView54.setData(100f,100f)

        //6
        mPercentageView6.setData(50f,100f)

        //7
        mPercentageView7.setData(50.2f,100f)
        mPercentageView71.setData(40.3f,100f)
        mPercentageView72.setData(36.2f,100f)
        mPercentageView73.setData(76f,100f)

        //8:有分割线无极限值限制
        mPercentageView8.setData(51f,100f)
        mPercentageView81.setData(6f,100f)

        //9:使用代码添加
        val build = PercentageViewBuilder
            .setMinHeight(60f)
            .setMinWidth(129f)
            .setLeftValue(45.6f)
            .build(this)

        emptyView.addView(build)

    }
}