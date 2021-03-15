package com.ywy.percentageview.paints.paint;

import android.graphics.Paint

/**
 * Time:2020/9/2
 * Author:ahan
 * Description: 填充画笔-可用于画图形
 */
class SolidPaint : BasePaint {

    //复用画笔+颜色
    constructor(paint: Paint?, color:Int){
        this.paint = paint
        this.color = color
        initPaint()
    }

    //颜色
    constructor(color:Int){
        this.paint = Paint()
        this.color = color
        paint?.let {
            it.style = Paint.Style.FILL //画笔样式
            it.isAntiAlias = true //抗锯齿
        }
        initPaint()
    }

    private fun initPaint() {
        paint?.let {
            color.let { it2->
                it.color = it2
            }
        }
    }
}
