package com.ywy.percentageview.paints.paint

import android.graphics.Paint

/**
 *Time:2020/9/2
 *Author:ahan
 *Description:画笔基本颜色类
 */
open class BasePaint {
    var paint: Paint? = null   //画笔-此处进行赋值复用
    var color: Int = 0          //画笔颜色
    var paintSize: Int = 0    //画笔粗细
}