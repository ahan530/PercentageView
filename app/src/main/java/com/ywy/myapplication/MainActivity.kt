package com.ywy.myapplication

import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
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
        mPercentageView0.setType(PercentageView.PVType.PURE)
        mPercentageView0.setOnClickListener {
            startActivity(Intent(this,CardActivity::class.java))
        }

        val ofFloat0 = ValueAnimator.ofFloat(10f, 75f)
        ofFloat0.duration = 3000
        ofFloat0.repeatCount = 0
        ofFloat0.addUpdateListener {
            val animatedValue = it.animatedValue as Float
            mPercentageView0.setData(animatedValue, 100f)
            mPercentageView0.invaLidate()
        }
        ofFloat0.start()
        //2：无进度值
        mPercentageView01.setType(PercentageView.PVType.PURE)
        mPercentageView01.setData(36f, 100f)

        //3：极限值  左边
        mPercentageView02.setType(PercentageView.PVType.PURE)
        mPercentageView02.setData(3f, 100f)

        //4：极限值 右边
        mPercentageView03.setType(PercentageView.PVType.PURE)
        mPercentageView03.setData(98.5f, 100f)

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
            mPercentageView1.setData(animatedValue, 100f)
            mPercentageView1.invaLidate()
//            mPercentageView4.setData(animatedValue,100f)
//            mPercentageView4.invaLidate()
//
//            mPercentageView7.setData(animatedValue,100f)
//            mPercentageView7.invaLidate()
        }
        ofFloat.start()

        //1.1：极限值
        mPercentageView11.setData(3f, 100f)
        //2:圆角无切割线
        mPercentageView2.setData(66.44f, 100f)
        //2.1:圆角无切线极限值
        mPercentageView21.setData(77.48f, 100f)


        //3：圆角带切割线无文字
        mPercentageView3.setData(55.5f, 100f)
        //3.1:左极值
        mPercentageView31.setData(0f, 100f)
        //3.2:右极值
        mPercentageView32.setData(100f, 100f)


        //4：圆角切割线有文字
//        mPercentageView4.setData(45.6f,100f)

        val ofFloat01 = ValueAnimator.ofFloat(0f, 45.6f)
        ofFloat01.duration = 3000
        ofFloat01.repeatCount = 0
        ofFloat01.addUpdateListener {
            val animatedValue = it.animatedValue as Float
            mPercentageView4.setData(animatedValue, 100f)
            mPercentageView4.invaLidate()
        }
        ofFloat01.start()

        //4.1:左极值
        mPercentageView41.setData(0f, 100f)
        //4.2：左极值（是否有极值-true）HaveLimitValue = true 则百分比进度值显示区域不够 自动隐藏
        mPercentageView42.setData(0f, 100f)


        //5:直角带切割线有文字，有中间文字
        mPercentageView5.setData(67.5f, 100f)
        //5.1:少
        mPercentageView51.setData(27f, 100f)
        //5.2：平
        mPercentageView52.setData(50f, 100f)
        //5.3：左极限
        mPercentageView53.setData(0f, 100f)
        //5.4 右极限
        mPercentageView54.setData(100f, 100f)

        //5.5：左极限 mtilt <0
        mPercentageView55.setData(0f, 100f)
        //5.6 右极限 mtilt <0
        mPercentageView56.setData(100f, 100f)


        //6:圆角不同色切割线多个文字
        mPercentageView6.setData(50f, 100f)
        mPercentageView61.setData(0f, 100f)
        mPercentageView62.setData(100f, 100f)

        //7:无极限值情况下的极限值显示
        mPercentageView70.setData(50.2f, 100f)
        mPercentageView700.setData(0f, 100f)
        mPercentageView7000.setData(100f, 100f)
        mPercentageView70000.setData(8f, 100f)

        mPercentageView71.setData(66.44f, 100f)
        mPercentageView711.setData(0f, 100f)
        mPercentageView7111.setData(100f, 100f)
        mPercentageView71111.setData(0f, 100f)
        mPercentageView711111.setData(100f, 100f)

        mPercentageView72.setData(36.2f, 100f)
        mPercentageView722.setData(0f, 100f)
        mPercentageView7222.setData(100f, 100f)

        mPercentageView73.setData(76f, 100f)
        mPercentageView733.setData(0f, 100f)
        mPercentageView7333.setData(100f, 100f)

        //8:有分割线无极限值限制
        mPercentageView8.setData(51f, 100f)
        mPercentageView80.setData(0f, 100f)
        mPercentageView800.setData(100f, 100f)

        mPercentageView81.setData(6f, 100f)
        mPercentageView811.setData(98f, 100f)
        mPercentageView8111.setData(66f, 100f)
        mPercentageView81111.setData(0f, 100f)
        mPercentageView811111.setData(100f, 100f)

        //9:使用代码添加,父布局约束宽高
        val build = PercentageViewBuilder.with(this)
            .setMinHeight(60f)
            .setMinWidth(129f)
            .setLeftValue(45.6f)
            .build()
        emptyView.addView(build)

        //10.无限制带分割线有进度值有前单位
        mPercentageView10.setData(55f, 100f)

        mPercentageView108.setData(55f, 100f)

        val dealFloatPercentageNumber = 100f
        mPercentageViewx.setData(dealFloatPercentageNumber, 100f)
        if (dealFloatPercentageNumber in 50.0..100.0) {
            mPercentageViewx.mCircularBgColor = ContextCompat.getColor(this, R.color.color_f75251)
        } else {
            mPercentageViewx.mCircularBgColor = ContextCompat.getColor(this, R.color.color_02b36c)
        }

        //11:三种色彩的进度条
        mPV11.setData(1f,1f,100f)
        mPV11.setType(PercentageView.PVType.THIRD)

        mSdkPercentageView.setType(PercentageView.PVType.NO_CENTER)
        mSdkPercentageView.setData(100f,100f)


        mSdkPercentageView2.setData(44f,100f)
    }

    fun onClick(view: android.view.View) {
        startActivity(Intent(this@MainActivity,ListActivity::class.java))
    }
}