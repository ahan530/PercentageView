//package com.ywy.myapplication.other
//
//import android.content.Context
//import android.graphics.*
//import android.util.AttributeSet
//import android.util.Log
//import com.ywy.myapplication.R
//
//import kotlin.math.abs
//import kotlin.math.roundToInt
//
///**
// * CreateTime:2020/8/12
// * Author:ahan
// * Description: 百分比图形
// **/
//const val TAG2 = "PercentageView2"
//class PercentageViewOld: android.view.View {
//
//    private var mType = Type.NORMAL
//
//    enum class Type {
//        PURE, //模式- 纯净模式下没有分割线,但是可以绘制左右进度文字
//        NORMAL, //模式二 默认模式
//       // DIVISION //模式三 分割模式，对分割首尾没有限制但不能设置倾斜度
//    }
//
//
//    constructor(context: Context?) : super(context) {
//        initData(context, null)
//    }
//
//    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
//        initData(context, attrs)
//    }
//
//    constructor(context: Context?, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
//        context,
//        attributeSet,
//        defStyleAttr
//    ) {
//        initData(context, attributeSet)
//    }
//
//    //最小高度 默認40f
//    private var mMinHeight = 40f
//
//    //最小寬度 默認180f
//    private var mMinWidth = 180f
//
//    //百分比左邊文字顏色
//    private var mTextLeftColor = Color.parseColor("#ffffff")
//
//    //左边坐标进度 布局设置0~100
//    var mLeftValue = 0.00f
//
//    //总值
//    var mTotailValue = 100.0f
//
//    //左边进度条颜色 默认蓝色
//    var mLeftColor = Color.parseColor("#5498f0")
//
//    //左边进度值
//    var mRightValue = 100.0f
//
//    //进度条弧度
//    var mProgressRadius = 0f
//
//    //百分比右邊字體顏色
//    var mTextRightColor = Color.parseColor("#ffffff")
//
//    //右边进度颜色 默认红色
//    var mRightColor = Color.parseColor("#e14c5b")
//
//    //百分比字體大小
//    var mValueTextSize = 22f
//
//    //中間字體大小
//    var mCenterTextSize = 20f
//
//    //进度文字左右padding
//    var mPadding = 8f
//
//    //是否显示两边进度值 默认显示
//    var mShowProgressText = true
//
//    //进度单位
//    var mProgressUnit = "%"
//
//    //中间文字-左边数值大 默认多
//    var mCenterTextMany = "多"
//
//    //中间文字-左边数值小 默认少
//    var mCenterTextFew = "空"
//
//    //中间显示文字
//    var mCenterText = "平"
//
//    //中间圆形半径  默认不显示 如果是0则不显示
//    var mRadius: Float = 0f
//
//    //中间圆形背景色
//    var mCircularBgColor = Color.parseColor("#ffffff")
//
//    //中间背景自定义画笔
//    var mPaintCircularbg: Paint? = null
//
//    //中间文字的画笔
//    var mPaintCircularText: Paint? = null
//
//    //中间分隔符的倾斜度-90~90 默认60f,对于模式三来说倾斜度只改变方向
//    var mTilt = 60f
//
//    //中间分隔线的宽度 默认为0
//    var mLineSize = 0f
//
//    //中间分隔线颜色
//    var mLineColor = Color.parseColor("#ffffff")
//
//    //是否有极限值 没有
//    private var mHaveLimitValue = false
//
//    //图形画笔
//    private var mPaint: Paint? = null
//
//    //文字画笔
//    private var mPaintText: Paint? = null
//
//    //图形区域
//    private var mRectF: Rect? = null
//
//    //图形路径
//    private var mPathChart: Path? = null
//
//    //图形辅助画笔
//    private var mPathChartAdd: Path? = null
//
//    //是否显示左边进度值
//    private var showLeftText = true
//
//    //是否显示右边进度值
//    private var showRightText = true
//
//    //左边值大-中间字体颜色
//    var mCenterTextManyColor = mLeftColor
//
//    //左边值小-中间字体颜色
//    var mCenterTextFewColor = mRightColor
//
//    //中间字体颜色(不变)
//    var mCenterTextColorAlways = 0
//
//    //左右值持平时字体颜色 默认橙色
//    var mCenterTextColor = Color.parseColor("#fd7f3e")
//
//
//    //一：初始化
//    private fun initData(context: Context?, attrs: AttributeSet?) {
//        if (attrs != null) {
//            val obt = context?.obtainStyledAttributes(attrs, R.styleable.PercentageView)
//            val height = obt?.getDimension(R.styleable.PercentageView_ProgressMinHeight, mMinHeight)
//            if (height != null) {
//                mMinHeight = height
//            }
//            val wight = obt?.getDimension(R.styleable.PercentageView_ProgressMinWidth, mMinWidth)
//            if (wight != null) {
//                mMinWidth = wight
//            }
//            val tilt = obt?.getFloat(R.styleable.PercentageView_CenterLineTilt, 60f)
//            if (tilt != null) {
//                mTilt = tilt
//            }
//            val lineSize = obt?.getDimension(R.styleable.PercentageView_CenterLineSize, mLineSize)
//            if (lineSize != null) {
//                mLineSize = lineSize
//            }
//            val progressRadius = obt?.getDimension(R.styleable.PercentageView_ProgressRadius, 10f)
//            if (progressRadius != null) {
//                mProgressRadius = progressRadius
//            }
//            val progressUnit = obt?.getString(R.styleable.PercentageView_ProgressUnit)
//            if (progressUnit != null) {
//                mProgressUnit = progressUnit
//            }
//
//            val lineColor = obt?.getColor(R.styleable.PercentageView_CenterLineColor, mLineColor)
//            if (lineColor != null) {
//                mLineColor = lineColor
//            }
//
//              val HaveLimitValue = obt?.getBoolean(R.styleable.PercentageView_HaveLimitValue, mHaveLimitValue)
//            if (HaveLimitValue != null) {
//                mHaveLimitValue = HaveLimitValue
//            }
//
//            val leftColor = obt?.getColor(R.styleable.PercentageView_textLeftColor, mTextLeftColor)
//            if (leftColor != null) {
//                mTextLeftColor = leftColor
//            }
//            val rightColor =
//                obt?.getColor(R.styleable.PercentageView_textRightColor, mTextRightColor)
//            if (rightColor != null) {
//                mTextRightColor = rightColor
//            }
//
//            val textSize =
//                obt?.getDimension(R.styleable.PercentageView_android_textSize, mValueTextSize)
//            if (textSize != null) {
//                mValueTextSize = textSize
//            }
//
//            val showProgressText =
//                obt?.getBoolean(R.styleable.PercentageView_ShowProgressText, mShowProgressText)
//            if (showProgressText != null) {
//                mShowProgressText = showProgressText
//            }
//
//            val radius = obt?.getDimension(R.styleable.PercentageView_Radius, mRadius)
//            if (radius != null) {
//                mRadius = radius
//            }
//            val CircularBgColor =
//                obt?.getColor(R.styleable.PercentageView_CircularBgColor, mCircularBgColor)
//            if (CircularBgColor != null) {
//                mCircularBgColor = CircularBgColor
//            }
//
//            val leftValue = obt?.getFloat(R.styleable.PercentageView_LeftProgress, 0.00f)
//            if (leftValue != null) {
//                if (mLeftValue > 0f && mLeftValue <= 100f) {
//                    mLeftValue = leftValue
//                    mTotailValue = 100f - mLeftValue
//                }
//            }
//
//            val rightValue = obt?.getFloat(R.styleable.PercentageView_RightProgress, 0.00f)
//            if (rightValue != null) {
//                mRightValue = rightValue
//            }
//
//            val padding = obt?.getDimension(R.styleable.PercentageView_textPadding, mPadding)
//            if (padding != null) {
//                mPadding = padding
//            }
//
//            val centerTextSize =
//                obt?.getDimension(R.styleable.PercentageView_CenterTextSize, mCenterTextSize)
//            if (centerTextSize != null) {
//                mCenterTextSize = centerTextSize
//            }
//
//            val centerTextMany = obt?.getString(R.styleable.PercentageView_CenterTextMany)
//            if (centerTextMany != null) {
//                mCenterTextMany = centerTextMany
//            }
//
//            val centerTextFew = obt?.getString(R.styleable.PercentageView_CenterTextFew)
//            if (centerTextFew != null) {
//                mCenterTextFew = centerTextFew
//            }
//            val centerText = obt?.getString(R.styleable.PercentageView_CenterText)
//            if (centerText != null) {
//                mCenterText = centerText
//            }
//
//            val centerTextManyColor =
//                obt?.getColor(R.styleable.PercentageView_CenterTextColorMany, mCenterTextManyColor)
//            if (centerTextManyColor != null) {
//                mCenterTextManyColor = centerTextManyColor
//            }
//
//            val centerTextFewColor =
//                obt?.getColor(R.styleable.PercentageView_CenterTextColorFew, mCenterTextFewColor)
//            if (centerTextFewColor != null) {
//                mCenterTextFewColor = centerTextFewColor
//            }
//
//            val centerTextColor =
//                obt?.getColor(R.styleable.PercentageView_CenterTextColor, mCenterTextColor)
//            if (centerTextColor != null) {
//                mCenterTextColor = centerTextColor
//            }
//
//            val centerTextColorAlways = obt?.getColor(
//                R.styleable.PercentageView_CenterTextColorAlways,
//                mCenterTextColorAlways
//            )
//            if (centerTextColorAlways != null) {
//                mCenterTextColorAlways = centerTextColorAlways
//            }
//
//            val leftProgressColor =
//                obt?.getColor(R.styleable.PercentageView_LeftProgressColor, mLeftColor)
//            if (leftProgressColor != null) {
//                mLeftColor = leftProgressColor
//            }
//            val rightProgressColor =
//                obt?.getColor(R.styleable.PercentageView_RightProgressColor, mRightColor)
//            if (rightProgressColor != null) {
//                mRightColor = rightProgressColor
//            }
//            obt?.recycle()
//        }
//        //图形画笔
//        mPaint = Paint()
//        //文字画笔
//        mPaintText = Paint()
//        //中间图形区域
//        mRectF = Rect()
//        //图形绘制路径
//        mPathChart = Path()
//        //图形绘制补充路径
//        mPathChartAdd = Path()
//    }
//
//    //二:测量宽高
//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//
//        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
//        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
//
//        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
//        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
//
//        Log.e(TAG2, "宽: $widthSize 高：$heightSize")
//
//        when (widthMode) {
//            MeasureSpec.EXACTLY -> { //父容器已经为子容器设置了尺寸,子容器应当服从这些边界,不论子容器想要多大的空间.
//                when (heightMode) {
//                    MeasureSpec.EXACTLY -> {
//                        setMeasuredDimension(widthSize, heightSize)
//                        Log.e( "宽高一定", "宽: $widthSize 高：$heightSize")
//                    }
//                    MeasureSpec.AT_MOST, MeasureSpec.UNSPECIFIED -> {
//                        setMeasuredDimension(widthSize, mMinHeight.toInt())
//                        Log.e("宽一定,高不定", "宽: $widthSize 高：$mMinHeight")
//                    }
//                }
//            }
//            MeasureSpec.AT_MOST -> { //子容器可以是声明大小内的任意大小 wrap
//                when (heightMode) {
//                    MeasureSpec.EXACTLY -> {
//                        setMeasuredDimension(mMinWidth.toInt(), heightSize)
//                        Log.e("宽wrap高一定", "宽: $mMinWidth 高：$heightSize")
//
//                    }
//                    MeasureSpec.AT_MOST, MeasureSpec.UNSPECIFIED -> {
//                        setMeasuredDimension(mMinWidth.toInt(), mMinHeight.toInt())
//                        Log.e("宽高均wrap", "宽: $mMinWidth 高：$mMinHeight")
//                    }
//                }
//            }
//            MeasureSpec.UNSPECIFIED -> { //父容器对子容器无限制
//                when (heightMode) {
//                    MeasureSpec.EXACTLY -> {
//                        setMeasuredDimension(mMinWidth.toInt(), heightSize)
//                    }
//                    MeasureSpec.AT_MOST, MeasureSpec.UNSPECIFIED -> {
//                        setMeasuredDimension(mMinWidth.toInt(), mMinHeight.toInt())
//                    }
//                }
//            }
//        }
//
//        //保存测量结果 无更改则不保存
//        //  setMeasuredDimension(widthSize, heightSize)
//    }
//
//    //画笔样式
//    //Paint.Style.FILL //填充
//    //Paint.Style.FILL_AND_STROKE //描边加填充
//    //Paint.Style.STROKE;//描边
//
//    //笔帽样式 笔头
//    //mPaint.setStrokeCap(Paint.Cap.BUTT);//没有样式
//    //mPaint.setStrokeCap(Paint.Cap.ROUND);//圆形
//    //mPaint.setStrokeCap(Paint.Cap.SQUARE);//方形
//
//    //两线相交样式
//    //mPaint.setStrokeJoin(Paint.Join.MITER);//锐角
//    //mPaint.setStrokeJoin(Paint.Join.ROUND);//圆角
//    //mPaint.setStrokeJoin(Paint.Join.BEVEL);//直线
//
//    //三：绘制
//    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
//        when (mType) {
//            //纯净模式
//            Type.PURE -> {
//                //绘制纯净进度条
//                drawPureBar(canvas)
//                //如果需要绘制文字
//                if (mShowProgressText) {
//                    //绘制左右两边边文字
//                    drawProgressText(canvas)
//                }
//            }
//            //模式三 临界值不完善
////            Type.DIVISION -> {
////                drawDivision(canvas)
////                if (mShowProgressText) {
////                    //绘制左右两边边文字
////                    drawProgressText(canvas)
////                }
////            }
//            //默认模式
//            else -> {
//
//
//                //无圆角
//                if ( mProgressRadius <= 0 || mHaveLimitValue ) {
//                    /**
//                     * step1:绘制中间分隔线
//                     */
//                    drawCutLine(canvas)
//                    /**
//                     * step2:绘制显示进度-左边
//                     */
//                    drawRectLeft(canvas)
//
//                    /**
//                     * step3:绘制显示进度-右边
//                     */
//                    drawRectRight(canvas)
//                } else { //有圆角
//                    /**
//                     * step 2-3:绘制无分割线圆角
//                     */
//                    drawCornerProgress(canvas)
//                }
//
//                //如果需要绘制文字
//                if (mShowProgressText) {
//                    /**
//                     * step4:绘制左右两边边文字
//                     */
//                    drawProgressText(canvas)
//                }
//
//                if (mRadius > 0f) {
//                    /**
//                     * step5:绘制中间圆形背景
//                     */
//                    drawCenterBg(canvas)
//                    /**
//                     * step6:绘制圆上的字
//                     */
//                    drawCenterText(canvas)
//                }
//            }
//
//
//        }
//    }
//
//
//    //---------------------------------------绘制步骤----------------------------------------
//
//    //0:画纯净bar
//    private fun drawPureBar(canvas: Canvas?) {
//
//        var pathOne: Path? = getFirstPath()
//
//
//        var paintOne: Paint? = getColorPaint(mRightColor)
//
//        if (paintOne != null && pathOne != null) {
//            canvas?.drawPath(pathOne, paintOne)
//        }
//
//        val pathTwo = getNewPath(
//            null,
//            0f,
//            0f,
//            width * (mLeftValue / mTotailValue),
//            height.toFloat(),
//            mProgressRadius,
//            mProgressRadius,
//            mProgressRadius,
//            mProgressRadius
//        )
//        var paintTwo: Paint? = getColorPaint(mLeftColor)
//        if (paintTwo != null && pathTwo != null) {
//            canvas?.drawPath(pathTwo, paintTwo)
//        }
//    }
//
//    //0.1:分割符bar
//    private fun drawDivision(canvas: Canvas?) {
//       mPathChart?:return
//
//         getNewPath(
//            mPathChart,
//            0f,
//            0f,
//            width * (mLeftValue / mTotailValue),
//            height.toFloat(),
//            mProgressRadius,
//            mProgressRadius,
//            0f,
//            0f
//        )?.let {
//             getColorPaint(mLeftColor)?.let {it1->
//                canvas?.drawPath(it,it1)
//            }
//        }
//
//         getNewPath(
//             mPathChart,
//            width * (mLeftValue /mTotailValue),
//            0f,
//            width.toFloat(),
//            height.toFloat(),
//            0f,
//            0f,
//            mProgressRadius,
//            mProgressRadius
//        )?.let {
//             getColorPaint(mRightColor)?.let {it1->
//                 canvas?.drawPath(it,it1)
//             }
//         }
//
//        val currentValue = width * (mLeftValue / mTotailValue)
//        var cutlinePath= if (mTilt > 0) {
//             getRagularPath(
//                mPathChart!!,
//                currentValue, 0f,
//                currentValue + mLineSize, 0f,
//                currentValue, height.toFloat(),
//                currentValue - mLineSize, height.toFloat()
//            )
//        }else{
//            getRagularPath(
//                mPathChart!!,
//                currentValue-mLineSize, 0f,
//                currentValue , 0f,
//                currentValue+mLineSize, height.toFloat(),
//                currentValue , height.toFloat()
//            )
//        }
//        cutlinePath?.let {
//            getColorPaint(mLineColor)?.let {it1->
//                canvas?.drawPath(it,it1)
//            }
//        }
//    }
//
//    //1:画分隔符
//    private fun drawCutLine(canvas: Canvas?) {
//        //获取分隔线的path
//        var pathCutLine: Path? = getCutLinePath()
//
//        if (pathCutLine != null) {
//            //获取分割线画笔
//            var paintCutLine = getColorPaint(mLineColor)
//            if (paintCutLine != null) {
//                canvas?.drawPath(pathCutLine, paintCutLine)
//            }
//        }
//    }
//
//    //2：绘制无圆角左边进度
//    private fun drawRectLeft(canvas: Canvas?) {
//        var pathLeft = if (mProgressRadius<=0){
//            getRectLeftPath()
//        }else{
//            getRadiusLeftPath(mProgressRadius)
//        }
//
//        //获取进度画笔 画左边
//        val progressLeftPaint = getColorPaint(mLeftColor)
//
//        //开始绘制
//        if (progressLeftPaint != null && pathLeft != null) {
//            canvas!!.drawPath(pathLeft!!, progressLeftPaint!!)
//        }
//    }
//
//    //3:绘制无圆角右边进度
//    private fun drawRectRight(canvas: Canvas?) {
//        //右边图形路径
//        var pathRight = getRadiusRightPath(mProgressRadius)
//        //右边画笔
//        val progressRightPaint = getColorPaint(mRightColor)
//
//        if (pathRight != null && progressRightPaint != null) {
//            canvas?.drawPath(pathRight, progressRightPaint)
//        }
//    }
//
//    //2-3：绘制圆角无分割线进度条
//    private fun drawCornerProgress(canvas: Canvas?) {
//        Log.i(TAG2, "onDraw: 有圆角")
//        //获取第一层path
//        var pathOne: Path? = getFirstPath()
//        var paintOne: Paint? = getColorPaint(mRightColor)
//        //获取第二层path
//        if (paintOne != null && pathOne != null) {
//            //现将第一层画出
//            canvas?.drawPath(pathOne, paintOne)
//            //获取第二层path
//            var pathTwo: Path? = getSecoundPath()
//            //将路径一和二去交集作为二层涂层
//            mRectF?.top = 0
//            mRectF?.left = 0
//            mRectF?.right = width
//            mRectF?.bottom = height
//            val region = Region(mRectF!!)
//            val region2 = Region()
//            val region3 = Region()
//            region2.setPath(pathOne!!, region)
//            region3.setPath(pathTwo!!, region)
//            val op = region3.op(region2, Region.Op.INTERSECT)
//            //改变画笔颜色
//            paintOne.color = mLeftColor
//            if (op) {
//                val iterator = RegionIterator(region3)
//                val rect = Rect()
//                while (iterator.next(rect)) {
//                    canvas!!.drawRect(rect, paintOne)
//                }
//            }
//
//            //有分割线
//            if (haveCutLine()){
//                val  region4 = Region()
//                 getCutPathByTilt()?.let {
//                     region4.setPath(it,region)
//                    val op2 = region4.op(region2,Region.Op.INTERSECT)
//                     paintOne.color = mLineColor
//
//                     Log.i(TAG2, "onDraw: 绘制分割线$op2")
//                     if (op2){
//                         val iterator = RegionIterator(region4)
//                         val rect = Rect()
//                         while (iterator.next(rect)) {
//                             canvas!!.drawRect(rect, paintOne)
//                         }
//                     }
//                 }
//            }
//        }
//    }
//
//    //4：绘制左右文字
//    private fun drawProgressText(canvas: Canvas?) {
//        //获取进度值画笔
//        var mTextPaint = getProgressTextPaint()
//        //绘制左边进度文字
//        if (showLeftText) {
//            if (mTextPaint != null) {
//                val valueString = if (mLeftTrueString.isNotEmpty()) {
//                    mLeftTrueString + "$mProgressUnit"
//                } else {
//                    "$mLeftValue$mProgressUnit"
//                }
//                val fontMetrics =
//                    mTextPaint!!.fontMetrics   //文字高度相关的信息都存在FontMetrics对象中
//                val y: Float =
//                    (height) / 2 + (abs(fontMetrics!!.ascent) - fontMetrics!!.descent) / 2 //|ascent|=descent+ 2 * ( 2号线和3号线之间的距离 )
//                Log.i(TAG2, "左边onDraw:绘制内容： $valueString")
//                //绘制
//                canvas?.drawText(
//                    valueString,
//                    0f + mPadding,
//                    y,
//                    mTextPaint!!
//                )
//            }
//        }
//        //绘制右边文字
//        if (showRightText) {
//            if (mTextPaint != null) {
//                val valueString = if (mRightTrueString.isNotEmpty()) {
//                    mRightTrueString + "$mProgressUnit"
//                } else {
//                    "$mRightValue$mProgressUnit"
//                }
//                val stringWidth = mTextPaint!!.measureText(valueString)
//                val x = (width - stringWidth - mPadding)
//                val fontMetrics = mTextPaint!!.fontMetrics
//                val y: Float =
//                    (height) / 2 + (abs(fontMetrics!!.ascent) - fontMetrics!!.descent) / 2 //|ascent|=descent+ 2 * ( 2号线和3号线之间的距离 )
//                canvas?.drawText(valueString, x, y, mTextPaint!!)
//            }
//        }
//    }
//
//    //5：绘制中间圆形背景
//    private fun drawCenterBg(canvas: Canvas?) {
//        //获取圆的画笔
//        var paintCircular = getPaintCricular(mPaintCircularbg)
//        if (paintCircular != null) {
//            canvas?.drawCircle(
//                width * mLeftValue / mTotailValue - abs(mTilt) / 2,
//                height / 2.toFloat(),
//                getRadius(),
//                paintCircular!!
//            )
//        }
//    }
//
//    //6：绘制中间圆上的字
//    private fun drawCenterText(canvas: Canvas?) {
//        //画圆中间的字
//        var mPaintCenterText: Paint? = getPaintCenterText(mPaintCircularText)
//
//        if (mPaintCenterText != null) {
//            val stringWidth = mPaintCenterText!!.measureText("$mCenterText")
//            val x =
//                (width * mLeftValue / mTotailValue) - abs(mTilt) / 2 - stringWidth / 2
//            val fontMetrics = mPaint!!.fontMetrics
//            val y: Float =
//                (height) / 2 + (abs(fontMetrics!!.ascent) - fontMetrics!!.descent) / 2 //|ascent|=descent+ 2 * ( 2号线和3号线之间的距离 )
//            canvas?.drawText("$mCenterText", x, y, mPaintCenterText!!) //绘制
//        }
//    }
//
//
//    //-------------------------------------------------------------------------------
//
//
//    //矩形左边区域
//    private fun getRectLeftPath(): Path? {
//        mPathChart?.reset()
//        mPathChart?.moveTo(0f, 0f) //起始点
//        if (mTilt > 0) {
//            mPathChart?.lineTo(width * (mLeftValue / mTotailValue) - mLineSize / 2, 0f) //第二个点
//            mPathChart?.lineTo(
//                width * (mLeftValue / mTotailValue) - mTilt - mLineSize / 2,
//                height.toFloat()
//            )
//        } else {
//            mPathChart?.lineTo(
//                width * (mLeftValue / mTotailValue) - mLineSize / 2 + mTilt,
//                0f
//            ) //第二个点
//            mPathChart?.lineTo(
//                width * (mLeftValue / mTotailValue) - mLineSize / 2,
//                height.toFloat()
//            )
//        }
//        mPathChart?.lineTo(0f, height.toFloat())
//        mPathChart?.close() //闭合图形
//        return mPathChart
//    }
//
//
//    //圆角无分隔符情况下的第二层的路径
//    private fun getSecoundPath(): Path? {
//        var mPathChartadd = Path()
//        mPathChartadd?.moveTo(0f, 0f)
//        if (mTilt > 0) {
//            mPathChartadd?.lineTo(width * (mLeftValue / mTotailValue), 0f)
//            mPathChartadd?.lineTo(width * (mLeftValue / mTotailValue) - mTilt, height.toFloat())
//
//        } else {
//            mPathChartadd?.lineTo(width * (mLeftValue / mTotailValue) + mTilt, 0f)
//            mPathChartadd?.lineTo(width * (mLeftValue / mTotailValue), height.toFloat())
//        }
//        mPathChartadd?.lineTo(0f, height.toFloat())
//        mPathChartadd?.close()
//        return mPathChartadd
//    }
//
//
//    //圆角无分隔符情况下的第一层的路径
//    private fun getFirstPath(): Path? {
//        return getNewPath(
//            null,
//            0f,
//            0f,
//            width.toFloat(),
//            height.toFloat(),
//            mProgressRadius,
//            mProgressRadius,
//            mProgressRadius,
//            mProgressRadius
//        )
//    }
//
//
//    //中间文字画笔
//    private fun getPaintCenterText(mPaintCircularText: Paint?): Paint? {
//        if (mPaintCircularText == null) {
//            mPaint?.reset()
//            mPaint?.style = Paint.Style.FILL //画笔样式
//            mPaint?.isAntiAlias = true //抗锯齿
//            mPaint?.textSize = mCenterTextSize //字体大小
//            mPaint?.typeface = Typeface.DEFAULT_BOLD
//            when {
//                mLeftValue > mTotailValue / 2 -> {
//                    mPaint?.color = mCenterTextManyColor//画笔颜色
//                    mCenterText = mCenterTextMany //中间显示内容
//                }
//                mLeftValue < mTotailValue / 2 -> {
//                    mPaint?.color = mCenterTextFewColor
//                    mCenterText = mCenterTextFew
//                }
//                else -> {
//                    mPaint?.color = mCenterTextColor
//                    mCenterText = mCenterText
//                }
//            }
//            if (mCenterTextColorAlways != 0) {
//                mPaint?.color = mCenterTextColorAlways
//            }
//            mPaint?.strokeWidth = 10f  //画笔宽度
//            return mPaint
//        } else {
//            return mPaintCircularText
//        }
//    }
//
//    //验证圆半径，不能大于高度的一半
//    private fun getRadius(): Float {
//        return if (mRadius > height / 2) {
//            height / 2.toFloat()
//        } else {
//            mRadius
//        }
//    }
//
//    //获取圆形图案画笔
//    private fun getPaintCricular(mPaintCircularbg: Paint?): Paint? {
//        return if (mPaintCircularbg != null) {
//            mPaintCircularbg
//        } else {
//            mPaint?.reset()
//            mPaint?.style = Paint.Style.FILL //画笔样式
//            mPaint?.isAntiAlias = true //抗锯齿
//            mPaint?.color = mCircularBgColor //画笔颜色
//            mPaint
//        }
//    }
//
//    //获取进度值画笔
//    private fun getProgressTextPaint(): Paint? {
//        //画文字左边 https://www.jianshu.com/p/3aa9dc7d3320
//        mPaint?.reset()
//        mPaint?.style = Paint.Style.FILL //画笔样式
//        mPaint?.isAntiAlias = true //抗锯齿
//        mPaint?.color = mTextLeftColor //画笔颜色
//        mPaint?.strokeWidth = 2f  //画笔宽度
//
//        mPaint?.strokeCap = Paint.Cap.BUTT //笔帽样式
//        mPaint?.strokeJoin = Paint.Join.BEVEL //相交样式
//        mPaint?.textSize = mValueTextSize//字体大小
//        mPaint?.textAlign = Paint.Align.LEFT//左对齐
//        //        mPaint?.isDither = true //图像抖动
//        //        mPaint?.letterSpacing = 4f //字符间距
//        //        mPaint?.textSkewX = -0.25f //文字倾斜 默认0，官方推荐的-0.25f是斜体
//
//        //计算制定长度的字符串
//        //measureForwards为true,从头开始测否则从尾向前测 maxWidth 最大的宽度 measuredWidth实测长度
//        //        var breadText = mPaint?.breakText("$mLeftValue", true, width/2-mPadding, floatArrayOf(width/3-mPadding))
//
//        //获取文本的矩形区域
//        //        val bounds = Rect()
//        //        val textBounds = mPaint?.getTextBounds("$mLeftValue", 0, "$mLeftValue".length - 1, bounds)
//
//        //        var measuredWidth =  FloatArray("$mLeftValue".length) //新建长度为内容长度的数组
//        //measuredWidth得到每一个字符的宽度；textWidths字符数
//        //        var textWidths = mPaint?.getTextWidths("$mLeftValue", measuredWidth)
//
//        //粗略获取文本宽度
//        //        val measureText = mPaint?.measureText("$mLeftValue")
//        //        val textWidths1 = mPaint?.getTextWidths("$mLeftValue", 0, "$mLeftValue".length - 1, measuredWidth)
//        return mPaint
//    }
//
//    //获取分割线path
//    private fun getCutLinePath(): Path? {
//        //确定有无分隔线
//        if (haveCutLine()) {
//            resetLeftValue()
//            if (mPathChart == null) return null
//            mPathChart?.reset()
//            return getCutPathByTilt()
//        }
//        return null
//    }
//
//
//
//
//    //重置进度值,使分隔线宽度>0时可以显示完全
//    private var mLeftTrueString = ""
//    private var mRightTrueString = ""
//    private fun resetLeftValue() {
//
//        var minProgressValue = if (mProgressRadius > 0) {
//            abs(mTilt) + mLineSize / 2 + height / 2
//        } else {
//            abs(mTilt) + mLineSize / 2
//        }
//
//        if (mLeftValue > mTotailValue / 2) {
//            if ((width - width * (mLeftValue / mTotailValue)) <= minProgressValue) {
//                showRightText = false
//
//                if (mProgressRadius > 0) {
//                    mLeftTrueString = "$mLeftValue"
//                    mLeftValue = (width - (height / 2 + mLineSize / 2)) / width * mTotailValue
//                    mRightValue = mTotailValue - mLeftValue
//                    showRightText = false
//                } else {
//                    mLeftTrueString = ""
//                    showRightText = true
//                }
//                Log.i(TAG2, "控件宽度：$width 最小宽度：$minProgressValue 当前总值：$mTotailValue")
//                Log.i(TAG2, "resetLeftValue: 重置value值2：$mLeftValue")
//
//            } else {
//                showRightText = true
//            }
//        } else {
//            if (width * (mLeftValue / mTotailValue) <= minProgressValue) {
//                mRightTrueString = "$mRightValue"
//                mLeftValue = (minProgressValue / width) * mTotailValue
//                mRightValue = mTotailValue - mLeftValue
//                showLeftText = false
//                Log.i(TAG2, "resetLeftValue: 重置value值1：$mLeftValue")
//            } else {
//                mRightTrueString = ""
//                showLeftText = true
//            }
//        }
//
//    }
//
//
//
//
//
//    //获取带指定圆角的path对象
//    private fun getRadiusLeftPath(mProgressRadius: Float): Path? {
//        /**
//         * 有圆角
//         */
//        if (mProgressRadius > 0f) {
//            mPathChart?.reset()
//            mPathChartAdd?.reset()
//            mPathChartAdd?.moveTo(height.toFloat() / 2, 0f) //起始点
//            if (mTilt > 0) {
//                mPathChartAdd?.lineTo(
//                    width * (mLeftValue / mTotailValue) - mLineSize / 2,
//                    0f
//                ) //第二个点
//                mPathChartAdd?.lineTo(
//                    width * (mLeftValue / mTotailValue) - mTilt - mLineSize / 2,
//                    height.toFloat()
//                )
//            } else {
//                mPathChartAdd?.lineTo(
//                    width * (mLeftValue / mTotailValue) - mLineSize / 2 + mTilt,
//                    0f
//                ) //第二个点
//                mPathChartAdd?.lineTo(
//                    width * (mLeftValue / mTotailValue) - mLineSize / 2,
//                    height.toFloat()
//                )
//            }
//            mPathChartAdd?.lineTo(height.toFloat() / 2, height.toFloat())
//
//            val rectF = RectF(0f, 0f, height.toFloat() / 2, height.toFloat())
//            val floatArrayOf = floatArrayOf(
//                mProgressRadius,
//                mProgressRadius,
//                0f,
//                0f,
//                0f,
//                0f,
//                mProgressRadius,
//                mProgressRadius
//            )
//            mPathChart?.addRoundRect(rectF, floatArrayOf, Path.Direction.CW)
//
//            if (mPathChartAdd != null) {
//                mPathChart?.addPath(mPathChartAdd!!)
//            }
//        }
//        /**
//         * 无圆角
//         */
//        else {
//            mPathChart?.reset()
//            mPathChart?.moveTo(0f, 0f) //起始点
//            if (mTilt > 0) {
//                mPathChart?.lineTo(width * (mLeftValue / mTotailValue) - mLineSize / 2, 0f) //第二个点
//                mPathChart?.lineTo(
//                    width * (mLeftValue / mTotailValue) - mTilt - mLineSize / 2,
//                    height.toFloat()
//                )
//            } else {
//                mPathChart?.lineTo(
//                    width * (mLeftValue / mTotailValue) - mLineSize / 2 + mTilt,
//                    0f
//                ) //第二个点
//                mPathChart?.lineTo(
//                    width * (mLeftValue / mTotailValue) - mLineSize / 2,
//                    height.toFloat()
//                )
//            }
//            mPathChart?.lineTo(0f, height.toFloat())
//        }
//        mPathChart?.close() //闭合图形
//        return mPathChart
//    }
//
//    //获取右边进度路径
//    private fun getRadiusRightPath(mProgressRadius: Float): Path {
//        val path = Path()
//        if (mProgressRadius > 0f) {
//            if (mTilt > 0) {
//                path.moveTo(width * (mLeftValue / mTotailValue) + mLineSize / 2, 0f) //起始点
//                path.lineTo(width.toFloat() - height / 2, 0f) //第二个点
//                path.lineTo(width.toFloat() - height / 2, height.toFloat())
//                path.lineTo(
//                    width * (mLeftValue / mTotailValue) + mLineSize / 2 - mTilt,
//                    height.toFloat()
//                ) //第四个点
//            } else {
//                path.moveTo(width * (mLeftValue / mTotailValue) + mLineSize / 2 + mTilt, 0f) //起始点
//                path.lineTo(width.toFloat() - height / 2, 0f) //第二个点
//                path.lineTo(width.toFloat() - height / 2, height.toFloat())
//                path.lineTo(width * (mLeftValue / mTotailValue) + mLineSize / 2, height.toFloat())
//            }
//            val rectF = RectF((width - height / 2).toFloat(), 0f, width.toFloat(), height.toFloat())
//            val floatArrayOf = floatArrayOf(
//                0f,
//                0f,
//                mProgressRadius,
//                mProgressRadius,
//                mProgressRadius,
//                mProgressRadius,
//                0f,
//                0f
//            )
//            path.addRoundRect(rectF, floatArrayOf, Path.Direction.CW)
//
//            path.addPath(path)
//        } else {
//            if (mTilt > 0) {
//                path.moveTo(width * (mLeftValue / mTotailValue) + mLineSize / 2, 0f) //起始点
//                path.lineTo(width.toFloat(), 0f) //第二个点
//                path.lineTo(width.toFloat(), height.toFloat())
//                path.lineTo(
//                    width * (mLeftValue / mTotailValue) + mLineSize / 2 - mTilt,
//                    height.toFloat()
//                )
//            } else {
//                path.moveTo(width * (mLeftValue / mTotailValue) + mLineSize / 2 + mTilt, 0f) //起始点
//                path.lineTo(width.toFloat(), 0f) //第二个点
//                path.lineTo(width.toFloat(), height.toFloat())
//                path.lineTo(width * (mLeftValue / mTotailValue) + mLineSize / 2, height.toFloat())
//            }
//        }
//        path.close()
//        return path
//    }
//
//    //-----------------------------------------------公共方法-----------------------------------
//
//
//    fun setType(ttt: Type) {
//        this.mType = ttt
//    }
//
//
//    //设置数据
//    fun setData(many: Float, few: Float, toTail: Float) {
//        mLeftValue = ((many * 100).roundToInt() / 100).toFloat()
//        mRightValue = ((few * 100).roundToInt() / 100).toFloat()
//        mTotailValue = toTail
//        if (mLeftValue + mRightValue != mTotailValue) {
//            mTotailValue = mLeftValue + mRightValue
//        }
//        //  invalidate()
//        if (toTail > 1) {
//
//        } else {
//            mLeftValue *= 100
//            mTotailValue *= 100
//            mRightValue *= 100
//        }
//
//        Log.i(TAG2, "setData: 左边值：$mLeftValue 右边值：$mRightValue 总值：$mTotailValue")
//    }
//
//    //设置数据
//    fun setData(many: Float, toTail: Float) {
//        mLeftValue = ((many * 100).roundToInt() / 100).toFloat() //如果要求精确4位就*10000然后/10000
//        mTotailValue = ((toTail * 100).roundToInt() / 100).toFloat()
//        mRightValue = mTotailValue - mLeftValue
//
//        if (toTail > 1) {
//
//        } else {
//            mLeftValue *= 100
//            mTotailValue *= 100
//            mRightValue *= 100
//        }
//    }
//
//    //重绘制
//    fun invaLidate() {
//        postInvalidate()
//    }
//
//
//    //-----------------------------------------------内部通用方法-----------------------------------
//
//    //获取指定颜色的填充画笔
//    private fun getColorPaint(mColor: Int): Paint? {
//        mPaint?.reset()
//        mPaint?.style = Paint.Style.FILL //画笔样式
//        mPaint?.isAntiAlias = true //抗锯齿
//        mPaint?.color = mColor //画笔颜色
//        return mPaint
//    }
//
//    //获取四个点的封闭路径
//    private fun getRagularPath(
//        path: Path, onePointx: Float, onePointy: Float,
//        twoPointx: Float, twoPointy: Float,
//        threePointx: Float, threePointy: Float,
//        fourPointx: Float, fourPointy: Float
//    ): Path {
//        path?.reset()
//        path?.moveTo(onePointx, onePointy) //第1个点
//        path?.lineTo(twoPointx, twoPointy) //第2个点
//        path?.lineTo(threePointx, threePointy)//第3个点
//        path?.lineTo(fourPointx, fourPointy)//第4个点
//        path?.close()
//        return path
//    }
//
//    //是否有分割线
//    private fun haveCutLine(): Boolean {
//        return mLineSize > 0f
//    }
//
//    //建一个带圆角的起始位置为0,0 终点位置不定的Path路径
//    private fun getNewPath(
//        mPathOld: Path?,
//        beginX: Float,
//        beginY: Float,
//        lastX: Float,
//        lastY: Float,
//        leftTop: Float,
//        leftBottom: Float,
//        rightTop: Float,
//        rightBottom: Float
//    ): Path? {
//        val rectF = RectF(beginX, beginY, lastX, lastY)
//        val floatArrayOf = floatArrayOf(
//            leftTop,
//            leftTop,
//            rightTop,
//            rightTop,
//            rightBottom,
//            rightBottom,
//            leftBottom,
//            leftBottom
//        )
//        return if (mPathOld == null) {
//            var mPath = Path()
//            mPath.addRoundRect(rectF, floatArrayOf, Path.Direction.CW)
//            mPath
//        } else {
//            mPathOld.reset()
//            mPathOld?.addRoundRect(rectF, floatArrayOf, Path.Direction.CW)
//            mPathOld
//        }
//    }
//
//    //通过倾斜度获取完整的分割线路径
//    private fun getCutPathByTilt(): Path? {
//        return if (mTilt > 0) {
//            getRagularPath(
//                mPathChart!!,
//                width * (mLeftValue / mTotailValue) - mLineSize / 2, 0f,
//                width * (mLeftValue / mTotailValue) + mLineSize / 2, 0f,
//                width * (mLeftValue / mTotailValue) - mTilt + mLineSize / 2, height.toFloat(),
//                width * (mLeftValue / mTotailValue) - mTilt - mLineSize / 2, height.toFloat()
//            )
//        } else {
//            getRagularPath(
//                mPathChart!!,
//                width * (mLeftValue / mTotailValue) - mLineSize / 2 + mTilt, 0f,
//                width * (mLeftValue / mTotailValue) + mLineSize / 2 + mTilt, 0f,
//                width * (mLeftValue / mTotailValue) + mLineSize / 2, height.toFloat(),
//                width * (mLeftValue / mTotailValue) - mLineSize / 2, height.toFloat()
//            )
//        }
//    }
//
//}