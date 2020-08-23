package com.ywy.myapplication

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ywy.percentageview.PercentageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //0
        mPercentageView0.setType(PercentageView.Type.PURE)
        mPercentageView0.setData(76f,100f)

        //1
        mPercentageView1.mRadius = 0f
        val ofFloat = ValueAnimator.ofFloat(0f, 55f)
        ofFloat.duration = 2000
        ofFloat.repeatCount = 0
        ofFloat.repeatMode = ValueAnimator.REVERSE
        ofFloat.addUpdateListener {
            val animatedValue = it.animatedValue as Float
            mPercentageView1.setData(animatedValue,100f)
            mPercentageView1.invaLidate()
            mPercentageView4.setData(animatedValue,100f)
            mPercentageView4.invaLidate()

            mPercentageView7.setData(animatedValue,100f)
            mPercentageView7.invaLidate()
        }
        ofFloat.start()


        //2
        mPercentageView2.setData(3f,100f)
        mPercentageView21.setData(45f,100f)



        //3
        mPercentageView3.setData(100f,100f)
        mPercentageView31.setData(0f,100f)
        mPercentageView32.setData(60f,100f)

        //4
        mPercentageView4.setData(45.6f,100f)

        //5
        mPercentageView5.setData(57f,100f)

        //6
        mPercentageView6.setData(50f,100f)

        //7
        mPercentageView7.setData(50f,100f)
        mPercentageView71.setData(40f,100f)
        mPercentageView72.setData(36f,100f)
        mPercentageView73.setData(76f,100f)



    }
}