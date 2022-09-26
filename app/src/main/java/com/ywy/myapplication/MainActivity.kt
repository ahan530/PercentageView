package com.ywy.myapplication

import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.ywy.percentageview.PercentageView
import com.ywy.percentageview.PercentageViewBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * 0:纯净模式
         */
        //1:有进度值，加个属性动画，从0开始不好看
        val mPercentageView0 = findViewById<PercentageView>(R.id.mPercentageView0)
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
        val mPercentageView01 = findViewById<PercentageView>(R.id.mPercentageView01)
        mPercentageView01.setType(PercentageView.PVType.PURE)
        mPercentageView01.setData(36f, 100f)

        //3：极限值  左边
        val mPercentageView02 = findViewById<PercentageView>(R.id.mPercentageView02)
        mPercentageView02.setType(PercentageView.PVType.PURE)
        mPercentageView02.setData(3f, 100f)

        //4：极限值 右边
        val mPercentageView03 = findViewById<PercentageView>(R.id.mPercentageView03)
        mPercentageView03.setType(PercentageView.PVType.PURE)
        mPercentageView03.setData(98.5f, 100f)

        /**
         * 普通模式
         */
        //1：直角带任意角度和任何宽度分割线
        val mPercentageView1 = findViewById<PercentageView>(R.id.mPercentageView1)
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
        val mPercentageView11 = findViewById<PercentageView>(R.id.mPercentageView11)
        mPercentageView11.setData(3f, 100f)
        //2:圆角无切割线
        val mPercentageView2 = findViewById<PercentageView>(R.id.mPercentageView2)
        mPercentageView2.setData(66.44f, 100f)
        //2.1:圆角无切线极限值
        val mPercentageView21 = findViewById<PercentageView>(R.id.mPercentageView21)
        mPercentageView21.setData(77.48f, 100f)


        //3：圆角带切割线无文字
        val mPercentageView3 = findViewById<PercentageView>(R.id.mPercentageView3)
        mPercentageView3.setData(55.5f, 100f)
        //3.1:左极值
        val mPercentageView31 = findViewById<PercentageView>(R.id.mPercentageView31)
        mPercentageView31.setData(0f, 100f)
        //3.2:右极值
        val mPercentageView32 = findViewById<PercentageView>(R.id.mPercentageView32)
        mPercentageView32.setData(100f, 100f)


        //4：圆角切割线有文字
//        mPercentageView4.setData(45.6f,100f)

        val ofFloat01 = ValueAnimator.ofFloat(0f, 45.6f)
        ofFloat01.duration = 3000
        ofFloat01.repeatCount = 0
        ofFloat01.addUpdateListener {
            val animatedValue = it.animatedValue as Float
            val mPercentageView4 = findViewById<PercentageView>(R.id.mPercentageView4)
            mPercentageView4.setData(animatedValue, 100f)
            mPercentageView4.invaLidate()
        }
        ofFloat01.start()

        //4.1:左极值
        val mPercentageView41 = findViewById<PercentageView>(R.id.mPercentageView41)
        mPercentageView41.setData(0f, 100f)
        //4.2：左极值（是否有极值-true）HaveLimitValue = true 则百分比进度值显示区域不够 自动隐藏
        val mPercentageView42 = findViewById<PercentageView>(R.id.mPercentageView42)
        mPercentageView42.setData(0f, 100f)


        //5:直角带切割线有文字，有中间文字
        val mPercentageView5 = findViewById<PercentageView>(R.id.mPercentageView5)
        mPercentageView5.setData(67.5f, 100f)
        //5.1:少
        val mPercentageView51 = findViewById<PercentageView>(R.id.mPercentageView51)
        mPercentageView51.setData(27f, 100f)
        //5.2：平
        val mPercentageView52 = findViewById<PercentageView>(R.id.mPercentageView52)
        mPercentageView52.setData(50f, 100f)
        //5.3：左极限
        val mPercentageView53 = findViewById<PercentageView>(R.id.mPercentageView53)
        mPercentageView53.setData(0f, 100f)
        //5.4 右极限
        val mPercentageView54 = findViewById<PercentageView>(R.id.mPercentageView54)
        mPercentageView54.setData(100f, 100f)

        //5.5：左极限 mtilt <0
        val mPercentageView55 = findViewById<PercentageView>(R.id.mPercentageView55)
        mPercentageView55.setData(0f, 100f)
        //5.6 右极限 mtilt <0
        val mPercentageView56 = findViewById<PercentageView>(R.id.mPercentageView56)
        mPercentageView56.setData(100f, 100f)


        //6:圆角不同色切割线多个文字
        val mPercentageView6 = findViewById<PercentageView>(R.id.mPercentageView6)
        mPercentageView6.setData(50f, 100f)
        val mPercentageView61 = findViewById<PercentageView>(R.id.mPercentageView61)
        mPercentageView61.setData(0f, 100f)
        val mPercentageView62 = findViewById<PercentageView>(R.id.mPercentageView62)
        mPercentageView62.setData(100f, 100f)

        //7:无极限值情况下的极限值显示
        val mPercentageView70 = findViewById<PercentageView>(R.id.mPercentageView70)
        mPercentageView70.setData(50.2f, 100f)
        val mPercentageView700 = findViewById<PercentageView>(R.id.mPercentageView700)
        mPercentageView700.setData(0f, 100f)
        val mPercentageView7000 = findViewById<PercentageView>(R.id.mPercentageView7000)
        mPercentageView7000.setData(100f, 100f)
        val mPercentageView70000 = findViewById<PercentageView>(R.id.mPercentageView70000)
        mPercentageView70000.setData(8f, 100f)

        val mPercentageView71 = findViewById<PercentageView>(R.id.mPercentageView71)
        val mPercentageView711 = findViewById<PercentageView>(R.id.mPercentageView711)
        val mPercentageView7111 = findViewById<PercentageView>(R.id.mPercentageView7111)
        val mPercentageView71111 = findViewById<PercentageView>(R.id.mPercentageView71111)
        val mPercentageView711111 = findViewById<PercentageView>(R.id.mPercentageView711111)
        mPercentageView71.setData(66.44f, 100f)
        mPercentageView711.setData(0f, 100f)
        mPercentageView7111.setData(100f, 100f)
        mPercentageView71111.setData(0f, 100f)
        mPercentageView711111.setData(100f, 100f)

        val mPercentageView72 = findViewById<PercentageView>(R.id.mPercentageView72)
        val mPercentageView722 = findViewById<PercentageView>(R.id.mPercentageView722)
        val mPercentageView7222 = findViewById<PercentageView>(R.id.mPercentageView7222)
        mPercentageView72.setData(36.2f, 100f)
        mPercentageView722.setData(0f, 100f)
        mPercentageView7222.setData(100f, 100f)

        val mPercentageView73 = findViewById<PercentageView>(R.id.mPercentageView73)
        val mPercentageView733 = findViewById<PercentageView>(R.id.mPercentageView733)
        val mPercentageView7333 = findViewById<PercentageView>(R.id.mPercentageView7333)
        mPercentageView73.setData(76f, 100f)
        mPercentageView733.setData(0f, 100f)
        mPercentageView7333.setData(100f, 100f)

        //8:有分割线无极限值限制
        val mPercentageView8 = findViewById<PercentageView>(R.id.mPercentageView8)
        val mPercentageView80 = findViewById<PercentageView>(R.id.mPercentageView80)
        val mPercentageView800 = findViewById<PercentageView>(R.id.mPercentageView800)
        mPercentageView8.setData(51f, 100f)
        mPercentageView80.setData(0f, 100f)
        mPercentageView800.setData(100f, 100f)

        val mPercentageView81 = findViewById<PercentageView>(R.id.mPercentageView81)
        val mPercentageView811 = findViewById<PercentageView>(R.id.mPercentageView811)
        val mPercentageView8111 = findViewById<PercentageView>(R.id.mPercentageView8111)
        val mPercentageView81111 = findViewById<PercentageView>(R.id.mPercentageView81111)
        val mPercentageView811111 = findViewById<PercentageView>(R.id.mPercentageView811111)
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
        val emptyView = findViewById<LinearLayout>(R.id.emptyView)
        emptyView.addView(build)

        //10.无限制带分割线有进度值有前单位
        val mPercentageView10 = findViewById<PercentageView>(R.id.mPercentageView10)
        val mPercentageView108 = findViewById<PercentageView>(R.id.mPercentageView108)
        mPercentageView10.setData(55f, 100f)

        mPercentageView108.setData(55f, 100f)

        val dealFloatPercentageNumber = 100f
        val mPercentageViewx = findViewById<PercentageView>(R.id.mPercentageViewx)
        mPercentageViewx.setData(dealFloatPercentageNumber, 100f)
        if (dealFloatPercentageNumber in 50.0..100.0) {
            mPercentageViewx.mCircularBgColor = ContextCompat.getColor(this, R.color.color_f75251)
        } else {
            mPercentageViewx.mCircularBgColor = ContextCompat.getColor(this, R.color.color_02b36c)
        }

        //11:三种色彩的进度条
        val mPV11 = findViewById<PercentageView>(R.id.mPV11)
        mPV11.setData(1f,1f,100f)
        mPV11.setType(PercentageView.PVType.THIRD)

        val mSdkPercentageView = findViewById<PercentageView>(R.id.mSdkPercentageView)
        val mSdkPercentageView2 = findViewById<PercentageView>(R.id.mSdkPercentageView2)
        mSdkPercentageView.setType(PercentageView.PVType.NO_CENTER)
        mSdkPercentageView.setData(40f,100f)
        mSdkPercentageView2.setData(44f,100f)
    }

    fun onClick(view: android.view.View) {
        startActivity(Intent(this@MainActivity,ListActivity::class.java))
    }
}