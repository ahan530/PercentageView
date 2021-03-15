package com.ywy.percentageview.paints.factory

import android.graphics.Paint
import com.ywy.percentageview.paints.paint.BasePaint

/**
 *Time:2020/9/2
 *Author:ahan
 *Description: 画笔工厂类
 */
object PaintFactory : IPaintFactory {
    fun createPaint(basePaint: BasePaint): Paint? {
        return basePaint.paint
    }
}