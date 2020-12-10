package com.ywy.percentageview.utils

import java.math.BigDecimal
import kotlin.math.pow

/**
 * CreateTime:2020/12/10
 * Author:admin
 * Description:
 **/
object NumberUtils {

    /**
     * 四舍五入
     */
    fun setFloatTo45(number: Float, mInt: Int): Float {
        if (mInt < 0) return number
        val pow = 10.0.pow(mInt).toInt()
        val b = BigDecimal("$number")
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).toFloat()
    }

}