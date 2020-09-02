package com.ywy.percentageview.paint;

import android.graphics.Paint

/**
 * Time:2020/9/2
 * Author:ahan
 * Description:
 */
class SolidPaint : BasePaint{

    private var color:Int?=null

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
        paint?.reset()
        paint?.let {
            color?.let {it2->
                it.color = it2
            }
        }
    }
}
