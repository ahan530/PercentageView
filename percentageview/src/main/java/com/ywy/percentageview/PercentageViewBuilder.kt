package com.ywy.percentageview

import android.content.Context
import android.graphics.Color

/**
 * Time:2020/9/3
 * Author:ahan
 * Description: 静态对象构建器
 */
object PercentageViewBuilder {

    //最小高度 默認40f
    var mMinHeight = 40f

    //最小寬度 默認180f
    var mMinWidth = 180f

    //百分比左邊文字顏色
    var mTextLeftColor = Color.parseColor("#ffffff")

    //左边坐标进度 布局设置0~100
    var mLeftValue = 0.00f

    //总值
    var mTotailValue = 100.0f

    //左边进度条颜色 默认蓝色
    var mLeftColor = Color.parseColor("#5498f0")

    //左边进度值
    var mRightValue = 100.0f

    //进度条弧度
    var mProgressRadius = 0f

    //百分比右邊字體顏色
    var mTextRightColor = Color.parseColor("#ffffff")

    //右边进度颜色 默认红色
    var mRightColor = Color.parseColor("#e14c5b")

    //百分比字體大小
    var mValueTextSize = 22f

    //中間字體大小
    var mCenterTextSize = 20f

    //进度文字左右padding
    var mPadding = 8f

    //是否显示两边进度值 默认显示
    var mShowProgressText = true

    //进度单位
    var mProgressUnit = "%"

    //中间文字-左边数值大 默认多
    var mCenterTextMany = "多"

    //中间文字-左边数值小 默认少
    var mCenterTextFew = "空"

    //中间显示文字
    var mCenterText = "平"

    //中间圆形半径  默认不显示 如果是0则不显示
    var mRadius = 0f

    //中间圆形背景色
    var mCricularBgColor = Color.parseColor("#ffffff")

    //中间分隔符的倾斜度-90~90 默认60f,对于模式三来说倾斜度只改变方向
    var mTilt = 60f

    //中间分隔线的宽度 默认为0
    var mLineSize = 0f

    //中间分隔线颜色
    var mLineColor = Color.parseColor("#ffffff")

    //是否有极限值 没有
    var mHaveLimitValue = false

    //是否显示左边进度值
    var showLeftText = true

    //是否显示右边进度值
    var showRightText = true

    //左边值大-center字体颜色
    var mCenterTextManyColor = mLeftColor

    //左边值小-center字体颜色
    var mCenterTextFewColor = mRightColor

    //中间字体颜色(不变)
    var mCenterTextColorAlways = 0

    //左右值持平时字体颜色 默认橙色
    var mCenterTextColor = Color.parseColor("#fd7f3e")

    fun setMinHeight(mMinHeight: Float): PercentageViewBuilder {
        this.mMinHeight = mMinHeight
        return this
    }

    fun setMinWidth(mMinWidth: Float): PercentageViewBuilder {
        this.mMinWidth = mMinWidth
        return this
    }

    fun setTextLeftColor(mTextLeftColor: Int): PercentageViewBuilder {
        this.mTextLeftColor = mTextLeftColor
        return this
    }

    fun build(context: Context?): PercentageView {
        return PercentageView(context, this)
    }

    fun setLeftValue(mLeftValue:Float):PercentageViewBuilder{
        this.mLeftValue = mLeftValue
        return this
    }
}
