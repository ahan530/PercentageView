package com.ywy.percentageview.paints

import android.graphics.Paint

/**
 *Time:2020/9/2
 *Author:ahan
 *Description: 工厂类
 */
class PaintFactory :IPaintFactory{
    companion object{
        fun createPaint(basePaint: BasePaint):Paint?{
            return basePaint.paint
        }
    }
}