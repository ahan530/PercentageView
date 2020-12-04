package com.ywy.percentageview.paints

import android.graphics.Paint

/**
 * CreateTime:2020/9/3
 * Author:admin
 * Description: 用于绘制文字的画笔
 **/

class TextPaint :BasePaint{

    private var widthPaint = 2f //画笔宽度
    private var textSize = 5f //字体大小

    constructor(paint: Paint?, color:Int,textSize:Float){
        this.paint = paint
        this.color = color
        this.textSize = textSize
        initPaint()
    }

//    constructor(paint: Paint?, textSize:Float){
//        this.paint = paint
//        this.paint?.reset()
//        this.textSize = textSize
//
//        setDefaultPaint()
//        initPaint()
//    }

    constructor(paint: Paint?, textSize:Float,widthPaint:Float){
        this.paint = paint
        this.paint?.reset()
        this.textSize = textSize
        this.widthPaint = widthPaint

        setDefaultPaint()
        initPaint()
    }

//    constructor(paint: Paint?, color:Int){
//        this.paint = paint
//        this.color = color
//        this.textSize = textSize
//        initPaint()
//    }

//    constructor(color:Int,textSize:Float){
//        this.paint = Paint()
//        this.color = color
//        this.textSize = textSize
//
//        setDefaultPaint()
//        initPaint()
//    }

//    constructor(color:Int,textSize:Float,widthPaint:Float){
//        this.paint = Paint()
//        this.color = color
//        this.textSize = textSize
//        this.widthPaint = widthPaint
//
//        setDefaultPaint()
//        initPaint()
//    }

//    constructor(color:Int){
//        this.paint = Paint()
//        this.color = color
//
//        setDefaultPaint()
//        initPaint()
//    }

    private fun setDefaultPaint() {
        paint?.style = Paint.Style.FILL //画笔样式
        paint?.isAntiAlias = true //抗锯齿
        paint?.strokeCap = Paint.Cap.BUTT //笔帽样式
        paint?.strokeJoin = Paint.Join.BEVEL //相交样式
        paint?.textAlign = Paint.Align.LEFT//左对齐
    }

    private fun initPaint() {
        paint?.reset()
        paint?.let {
            it.textSize = textSize
            it.strokeWidth = widthPaint
            it.isAntiAlias = true
            color.let { it2->
                it.color = it2
            }
        }
    }
}