package com.ywy.percentageview.paths.path

import android.graphics.Path
import android.graphics.RectF
import com.ywy.percentageview.paths.PointEntity

/**
 *Time:2020/9/2
 *Author:ahan
 *Description: 带圆角边的矩形path路径
 */
class RectanglePath : BasePath {
    private var rectF: RectF?=null

    private var leftTopRadius = 0f
    private var rightTopRadius = 0f
    private var rightBottomRadius = 0f
    private var leftBottomRadius = 0f

    //区域、复用路径、4个圆角
    constructor(rectF: RectF?, path: Path?, leftTopRadius:Float, rightTopRadius:Float, rightBottomRadius:Float, leftBottomRadius:Float){
        this.rectF = rectF
        path?.reset()
        this.path = path

        this.leftTopRadius = leftTopRadius
        this.rightTopRadius = rightTopRadius
        this.rightBottomRadius = rightBottomRadius
        this.leftBottomRadius = leftBottomRadius

        initData()
    }

    //区域、4个圆角
    constructor(rectF: RectF, leftTopRadius:Float, rightTopRadius:Float, rightBottomRadius:Float, leftBottomRadius:Float){
        this.rectF = rectF
        this.path = Path()

        this.leftTopRadius = leftTopRadius
        this.rightTopRadius = rightTopRadius
        this.rightBottomRadius = rightBottomRadius
        this.leftBottomRadius = leftBottomRadius

        initData()
    }

    //复用路径,区域点集合、4个圆角
    constructor(path: Path, rectFPoint:Array<PointEntity>, leftTopRadius:Float, rightTopRadius:Float, rightBottomRadius:Float, leftBottomRadius:Float){
        path.reset()
        this.path = path

        if (rectFPoint.size == 2){
            this.rectF = RectF(rectFPoint[0].x,rectFPoint[0].y,rectFPoint[1].x,rectFPoint[1].y)
        }else{
            this.rectF = RectF()
        }

        this.leftTopRadius = leftTopRadius
        this.rightTopRadius = rightTopRadius
        this.rightBottomRadius = rightBottomRadius
        this.leftBottomRadius = leftBottomRadius
        initData()
    }

    //区域点集合、4个圆角
    constructor(rectFPoint:Array<PointEntity>, leftTopRadius:Float, rightTopRadius:Float, rightBottomRadius:Float, leftBottomRadius:Float){
        this.path = Path()
        if (rectFPoint.size == 2){
            this.rectF = RectF(rectFPoint[0].x,rectFPoint[0].y,rectFPoint[1].x,rectFPoint[1].y)
        }else{
            this.rectF = RectF()
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
        rectF?.let {
            path?.addRoundRect(it, floatArrayValue, Path.Direction.CW)
        }
    }
}