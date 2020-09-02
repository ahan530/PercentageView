package com.ywy.percentageview.path

import android.graphics.Path
import android.graphics.RectF

/**
 *Time:2020/9/2
 *Author:ahan
 *Description: 带圆角边的path路径
 */
class FilletPath : BasePath{
    private var rectf: RectF?=null

    private var leftTopRadius = 0f
    private var rightTopRadius = 0f
    private var rightBottomRadius = 0f
    private var leftBottomRadius = 0f

    constructor(rectf: RectF?,path: Path?,leftTopRadius:Float,rightTopRadius:Float,rightBottomRadius:Float,leftBottomRadius:Float){
        this.rectf = rectf
        path?.reset()
        this.path = path

        this.leftTopRadius = leftTopRadius
        this.rightTopRadius = rightTopRadius
        this.rightBottomRadius = rightBottomRadius
        this.leftBottomRadius = leftBottomRadius

        initData()
    }

    constructor(rectf: RectF,points:Array<PoinEntity>,leftTopRadius:Float,rightTopRadius:Float,rightBottomRadius:Float,leftBottomRadius:Float){
        this.rectf = rectf
        this.path = Path()

        this.leftTopRadius = leftTopRadius
        this.rightTopRadius = rightTopRadius
        this.rightBottomRadius = rightBottomRadius
        this.leftBottomRadius = leftBottomRadius

        initData()
    }

    constructor(path: Path,rectfPoint:Array<PoinEntity>,leftTopRadius:Float,rightTopRadius:Float,rightBottomRadius:Float,leftBottomRadius:Float){
        path.reset()
        this.path = path

        if (rectfPoint.size == 2){
            this.rectf = RectF(rectfPoint[0].x,rectfPoint[0].y,rectfPoint[1].x,rectfPoint[1].y)
        }else{
            this.rectf = RectF()
        }

        this.leftTopRadius = leftTopRadius
        this.rightTopRadius = rightTopRadius
        this.rightBottomRadius = rightBottomRadius
        this.leftBottomRadius = leftBottomRadius
        initData()
    }

    constructor(rectfPoint:Array<PoinEntity>,leftTopRadius:Float,rightTopRadius:Float,rightBottomRadius:Float,leftBottomRadius:Float){
        this.path = Path()
        if (rectfPoint.size == 2){
            this.rectf = RectF(rectfPoint[0].x,rectfPoint[0].y,rectfPoint[1].x,rectfPoint[1].y)
        }else{
            this.rectf = RectF()
        }
        this.leftTopRadius = leftTopRadius
        this.rightTopRadius = rightTopRadius
        this.rightBottomRadius = rightBottomRadius
        this.leftBottomRadius = leftBottomRadius
        initData()
    }

    private fun initData() {
        val floatArrayValue = floatArrayOf(
            leftTopRadius,
            leftTopRadius,
            rightTopRadius,
            rightTopRadius,
            rightBottomRadius,
            rightBottomRadius,
            leftBottomRadius,
            leftBottomRadius
        )
        rectf?.let {
            path?.addRoundRect(it, floatArrayValue, Path.Direction.CW)
        }
    }
}