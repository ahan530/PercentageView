package com.ywy.percentageview.paints;

import android.graphics.Paint

/**
 * Time:2020/9/2
 * Author:ahan
 * Description: 填充画笔-可用于画图形
 */
class SolidPaint : BasePaint{

    constructor(paint: Paint?, color:Int){
        this.paint = paint
        this.color = color
        initPaint()
    }

    constructor(color:Int){
        this.paint = Paint()
        this.color = color
        initPaint()
    }

    private fun initPaint() {
        paint?.let {
            color.let { it2->
                it.style = Paint.Style.FILL //画笔样式
                it.isAntiAlias = true //抗锯齿
                it.color = it2
            }
        }
    }
}
