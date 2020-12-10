package com.ywy.percentageview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import com.ywy.percentageview.paints.PaintFactory
import com.ywy.percentageview.paints.SolidPaint
import com.ywy.percentageview.paints.TextPaint
import com.ywy.percentageview.paths.FilletPath
import com.ywy.percentageview.paths.PathFactory
import com.ywy.percentageview.paths.PoinEntity
import com.ywy.percentageview.paths.RectanglePath
import com.ywy.percentageview.utils.NumberUtils
import java.math.BigDecimal
import java.text.NumberFormat
import kotlin.math.abs
import kotlin.math.roundToInt


/**
 * CreateTime:2020/8/12
 * Author:ahan
 * Description: 百分比图形
 **/
const val TAG = "PercentageView"

class PercentageView : android.view.View {

    private var mType = Type.NORMAL

    enum class Type {
        PURE, //模式- 纯净模式下没有分割线,但是可以绘制左右进度文字
        NORMAL, //模式二 默认模式
        // DIVISION //模式三 分割模式，对分割首尾没有限制但不能设置倾斜度
    }

    constructor(context: Context?) : super(context) {
        initData(context, null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initData(context, attrs)
    }

    constructor(context: Context?, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        initData(context, attributeSet)
    }

    //最小高度 默認40f
    var mMinHeight = 40f

    //最小寬度 默認180f
    var mMinWidth = 180f

    //百分比左邊文字顏色
    var mTextLeftColor = Color.parseColor("#ffffff")

    //左边坐标进度 布局设置0~100
    var mLeftValue = 0f

    //总值
    var mTotailValue = 100.0f

    //左边进度条颜色 默认蓝色
    var mLeftColor = Color.parseColor("#5498f0")

    //左边进度值
    private var mRightValue = 100.0f

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

    //图形画笔
    private var mPaint: Paint? = null

    //文字画笔
    private var mPaintText: Paint? = null

    //图形区域
    private var mRect = Rect()

    //图形路径
    private var mPathChart: Path? = null

    //图形辅助画笔
    private var mPathChartAdd: Path? = null

    //是否显示左边进度值
    var showLeftText = true

    //是否显示右边进度值
    var showRightText = true

    //是否显示左边前单位
    var showLeftBeforeUnit = false

    //是否显示左边前单位
    var showRightBeforeUnit = false

    //左边值大-center字体颜色
    var mCenterTextManyColor = mLeftColor

    //左边值小-center字体颜色
    var mCenterTextFewColor = mRightColor

    //中间字体颜色(不变)
    var mCenterTextColorAlways = 0

    //左右值持平时字体颜色 默认橙色 如果mCenterTextColorAlways有色值 则字体颜色为mCenterTextColorAlways
    var mCenterTextColor = Color.parseColor("#fd7f3e")

    //如果是平则中间颜色为 默认蓝色
    var mCenterValuesameBgColor = Color.parseColor("#3b8ced")

    //中间文字背景是否一直完全显示
    var mCenterBgAllwayShow = false

    //重置进度值,使分隔线宽度>0时可以显示完全
    private var mLeftTrueString = ""
    private var mRightTrueString = ""

    //上下间距
    var mTopBottomPadding = 0f

    //一：初始化
    private fun initData(context: Context?, attrs: AttributeSet?) {
        if (attrs != null) {
            val obt = context?.obtainStyledAttributes(attrs, R.styleable.PercentageView)

            obt?.getDimension(R.styleable.PercentageView_ProgressMinHeight, mMinHeight)?.let {
                mMinHeight = it
            }

            obt?.getDimension(R.styleable.PercentageView_TopBottomPadding, mTopBottomPadding)?.let {
                mTopBottomPadding = it
            }


            obt?.getDimension(R.styleable.PercentageView_ProgressMinWidth, mMinWidth)?.let {
                mMinWidth = it
            }

            obt?.getFloat(R.styleable.PercentageView_CenterLineTilt, mTilt)?.let {
                mTilt = it
            }

            obt?.getDimension(R.styleable.PercentageView_CenterLineSize, mLineSize)
                ?.let {
                    mLineSize = it
                }

            obt?.getDimension(R.styleable.PercentageView_ProgressRadius, mProgressRadius)?.let {
                mProgressRadius = it
            }

            obt?.getString(R.styleable.PercentageView_ProgressUnit)
                ?.let {
                    mProgressUnit = it
                }

            obt?.getColor(R.styleable.PercentageView_CenterLineColor, mLineColor)
                ?.let {
                    mLineColor = it
                }


            obt?.getBoolean(R.styleable.PercentageView_HaveLimiValue, mHaveLimitValue)
                ?.let {
                    mHaveLimitValue = it
                }

            obt?.getColor(R.styleable.PercentageView_textLeftColor, this.mTextLeftColor)
                ?.let {
                    this.mTextLeftColor = it
                }

            obt?.getColor(R.styleable.PercentageView_textRightColor, mTextRightColor)
                ?.let {
                    mTextRightColor = it
                }


            obt?.getDimension(R.styleable.PercentageView_android_textSize, mValueTextSize)
                ?.let {
                    mValueTextSize = it
                }


            obt?.getBoolean(R.styleable.PercentageView_ShowProgressText, mShowProgressText)
                ?.let {
                    mShowProgressText = it
                }

            obt?.getBoolean(R.styleable.PercentageView_ShowLeftBeforeUnit, showLeftBeforeUnit)
                ?.let {
                    showLeftBeforeUnit = it
                }

            obt?.getBoolean(R.styleable.PercentageView_ShowRightBeforeUnit, showRightBeforeUnit)
                ?.let {
                    showRightBeforeUnit = it
                }

            obt?.getDimension(R.styleable.PercentageView_Radius, mRadius)
                ?.let {
                    mRadius = it
                }

            obt?.getColor(R.styleable.PercentageView_CricularBgColor, mCricularBgColor)
                ?.let {
                    mCricularBgColor = it
                }

            obt?.getFloat(R.styleable.PercentageView_LeftProgress, 0.00f)
                ?.let {
                    if (mLeftValue > 0f && mLeftValue <= 100f) {
                        mLeftValue = it
                        mTotailValue = 100f - mLeftValue
                    }
                }

            obt?.getFloat(R.styleable.PercentageView_RightProgress, 0.00f)
                ?.let {
                    mRightValue = it
                }

            obt?.getDimension(R.styleable.PercentageView_textPadding, mPadding)
                ?.let {
                    mPadding = it
                }


            obt?.getDimension(R.styleable.PercentageView_CenterTextSize, mCenterTextSize)
                ?.let {
                    mCenterTextSize = it
                }

            obt?.getString(R.styleable.PercentageView_CenterTextMany)
                ?.let {
                    mCenterTextMany = it
                }

            obt?.getString(R.styleable.PercentageView_CenterTextFew)
                ?.let {
                    mCenterTextFew = it
                }
            obt?.getString(R.styleable.PercentageView_CenterText)
                ?.let {
                    mCenterText = it
                }


            obt?.getColor(R.styleable.PercentageView_CenterTextColorMany, mCenterTextManyColor)
                ?.let {
                    mCenterTextManyColor = it
                }


            obt?.getColor(R.styleable.PercentageView_CenterTextColorFew, mCenterTextFewColor)
                ?.let {
                    mCenterTextFewColor = it
                }


            obt?.getColor(R.styleable.PercentageView_CenterTextColor, mCenterTextColor)
                ?.let {
                    mCenterTextColor = it
                }

            obt?.getColor(
                R.styleable.PercentageView_CenterValuesameBgColor,
                mCenterValuesameBgColor
            )
                ?.let {
                    mCenterValuesameBgColor = it
                }

            obt?.getBoolean(R.styleable.PercentageView_CenterBgAllwaysShow, mCenterBgAllwayShow)
                ?.let {
                    mCenterBgAllwayShow = it
                }


            obt?.getColor(
                R.styleable.PercentageView_CenterTextColorAlways,
                mCenterTextColorAlways
            )
                ?.let {
                    mCenterTextColorAlways = it
                }

            obt?.getColor(R.styleable.PercentageView_LeftProgressColor, mLeftColor)
                ?.let {
                    mLeftColor = it
                }

            obt?.getColor(R.styleable.PercentageView_RightProgressColor, mRightColor)
                ?.let {
                    mRightColor = it
                }
            obt?.recycle()
        }
        //图形画笔
        mPaint = Paint()
        //文字画笔
        mPaintText = Paint()
        //图形绘制路径
        mPathChart = Path()
        //图形绘制补充路径
        mPathChartAdd = Path()
    }

    //二:测量宽高
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        // Log.e(TAG, "宽: $widthSize 高：$heightSize")

        when (widthMode) {
            MeasureSpec.EXACTLY -> { //父容器已经为子容器设置了尺寸,子容器应当服从这些边界,不论子容器想要多大的空间.
                when (heightMode) {
                    MeasureSpec.EXACTLY -> {
                        setMeasuredDimension(widthSize, heightSize)
                        //   Log.e(TAG + "宽高一定", "宽: $widthSize 高：$heightSize")
                    }
                    MeasureSpec.AT_MOST, MeasureSpec.UNSPECIFIED -> {
                        setMeasuredDimension(widthSize, mMinHeight.toInt())
                        //  Log.e(TAG + "宽一定,高不定", "宽: $widthSize 高：$mMinHeight")
                    }
                }
            }
            MeasureSpec.AT_MOST -> { //子容器可以是声明大小内的任意大小 wrap
                when (heightMode) {
                    MeasureSpec.EXACTLY -> {
                        setMeasuredDimension(mMinWidth.toInt(), heightSize)
//                        Log.e(TAG + "宽wrap高一定", "宽: $mMinWidth 高：$heightSize")

                    }
                    MeasureSpec.AT_MOST, MeasureSpec.UNSPECIFIED -> {
                        setMeasuredDimension(mMinWidth.toInt(), mMinHeight.toInt())
//                        Log.e(TAG + "宽高均wrap", "宽: $mMinWidth 高：$mMinHeight")
                    }
                }
            }
            MeasureSpec.UNSPECIFIED -> { //父容器对子容器无限制
                when (heightMode) {
                    MeasureSpec.EXACTLY -> {
                        setMeasuredDimension(mMinWidth.toInt(), heightSize)
                    }
                    MeasureSpec.AT_MOST, MeasureSpec.UNSPECIFIED -> {
                        setMeasuredDimension(mMinWidth.toInt(), mMinHeight.toInt())
                    }
                }
            }
        }
    }

    //三：绘制
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        when (mType) {
            //1.纯净模式
            Type.PURE -> {
                //绘制纯净进度条
                drawPureBar(canvas)
                //需要绘制文字
                if (mShowProgressText) {
                    drawProgressText(canvas)
                }
            }
            //2.默认模式
            else -> {
                if (mProgressRadius <= 0 || mHaveLimitValue) { //无圆角||有极限值
                    /**
                     * step1:绘制中间分隔线
                     */
                    drawCutLine(canvas)
                    /**
                     * step2:绘制显示进度-左边
                     */
                    drawRectLeft(canvas)
                    /**
                     * step3:绘制显示进度-右边
                     */
                    drawRectRight(canvas)
                } else { //有圆角
                    /**
                     * step 1-3:绘制圆角||无极限值
                     */
                    drawCornerProgress(canvas)
                }

                //需要绘制文字
                if (mShowProgressText) {
                    /**
                     * step4:绘制左右两边边文字
                     */
                    drawProgressText(canvas)
                }

                if (mRadius > 0f) {

                    if (needDrawCenter()) {

                        /**
                         * step5:绘制中间圆形背景
                         */
                        drawCenterBg(canvas)
                        /**
                         * step6:绘制圆上的字
                         */
                        drawCenterText(canvas)
                    }
                }
            }
        }
    }


    private fun needDrawCenter(): Boolean {
        getRadius()
        if (mProgressRadius > 0 && !mHaveLimitValue) { //无极限值 越界情况
            if (mCenterBgAllwayShow) return true //如果是需要展示则直接返回ture

            if ((mLeftValue / mTotailValue) * width < mRadius) {
                return false
            } else if ((mLeftValue / mTotailValue) * width > (width - mRadius)) {
                return false
            }
        }
        return true
    }


    //---------------------------------------绘制步骤----------------------------------------

    //0:画纯净bar
    private fun drawPureBar(canvas: Canvas?) {
        val pathOne: Path? = PathFactory.createPath(
            FilletPath(
                RectF(0f, 0f, width.toFloat(), height.toFloat()),
                Path(),
                mProgressRadius,
                mProgressRadius,
                mProgressRadius,
                mProgressRadius
            )
        )
        val paintOne: Paint? = PaintFactory.createPaint(SolidPaint(mPaint, mRightColor))

        pathOne?.let {
            paintOne?.let { it2 ->
                canvas?.drawPath(it, it2)
            }
        }

        val pathTwo = PathFactory.createPath(
            FilletPath(
                RectF(
                    -width * (mTotailValue - mLeftValue / mTotailValue),
                    0f,
                    width * (mLeftValue / mTotailValue),
                    height.toFloat()
                ),
                Path(),
                mProgressRadius, mProgressRadius, mProgressRadius, mProgressRadius
            )
        )
        paintOne?.color = mLeftColor
        pathOne?.let { pathO ->
            pathTwo?.let {
                paintOne?.let { it2 ->
//                canvas?.drawPath(it, it2)

                    mRect.top = 0
                    mRect.left = 0
                    mRect.right = width
                    mRect.bottom = height
                    val region = Region(mRect)
                    val region2 = Region()
                    val region3 = Region()
                    region2.setPath(pathO, region)
                    region3.setPath(it, region)
                    val op = region3.op(region2, Region.Op.INTERSECT)
                    //改变画笔颜色
                    if (op) {
//                    Log.i(TAG, "drawPureBar: 开始绘制纯净模式")
                        val iterator = RegionIterator(region3)
                        val rect = Rect()
                        while (iterator.next(rect)) {
                            canvas?.drawRect(rect, it2)
                        }
                    }
                }
            }
        }


    }

    //1:画分隔符
    private fun drawCutLine(canvas: Canvas?) {
        if (haveCutLine()) {
            //获取分隔线的path
            getCutLinePath()?.let {
                //获取分割线画笔
                PaintFactory.createPaint(SolidPaint(mPaint, mLineColor))?.let { it2 ->
                    it2.isAntiAlias = true
                    canvas?.drawPath(it, it2)
                }
            }
        }
    }

    //2：绘制左边进度
    private fun drawRectLeft(canvas: Canvas?) {
        //获取坐标路径
        val pathLeft = if (mProgressRadius <= 0) {
            getRectLeftPath()
        } else {
            getRadiusLeftPath(mProgressRadius)
        }
        //获取进度画笔
        val progressLeftPaint = PaintFactory.createPaint(SolidPaint(mPaint, mLeftColor))

        pathLeft?.let {
            progressLeftPaint?.let { it2 -> canvas?.drawPath(it, it2) }
        }
    }

    //3:绘制右边进度
    private fun drawRectRight(canvas: Canvas?) {
        //右边图形路径
        val pathRight = getRadiusRightPath(mProgressRadius)
        //右边画笔
        PaintFactory.createPaint(SolidPaint(mPaint, mRightColor))?.let {
            pathRight?.let { pathH ->
                canvas?.drawPath(pathH, it)
            }

        }
    }

    //2-3：绘制圆角无分割线进度条
    private fun drawCornerProgress(canvas: Canvas?) {
//        Log.i(TAG, "onDraw: 有圆角")

        //获取第一层path
        val pathOne: Path? = PathFactory.createPath(
            FilletPath(
                RectF(
                    0f,
                    0f + mTopBottomPadding,
                    width.toFloat(),
                    height.toFloat() - mTopBottomPadding
                ),
                mPathChart,
                mProgressRadius,
                mProgressRadius,
                mProgressRadius,
                mProgressRadius
            )
        )

        val paintOne: Paint? = PaintFactory.createPaint(SolidPaint(mPaint, mRightColor))

        //获取第二层path
        if (paintOne != null && pathOne != null) {
            //现将第一层画出
            paintOne.isAntiAlias = true
            canvas?.drawPath(pathOne, paintOne)
            //获取第二层path
            val pathTwo: Path? = getSecoundPath()
            //将路径一和二去交集作为二层涂层
            mRect.top = 0 + mTopBottomPadding.toInt()
            mRect.left = 0
            mRect.right = width
            mRect.bottom = height - mTopBottomPadding.toInt()
            val region = Region(mRect)
            val region2 = Region()
            val region3 = Region()
            region2.setPath(pathOne, region)

            pathTwo?.let {
                region3.setPath(it, region)
                val op = region3.op(region2, Region.Op.INTERSECT)
                //改变画笔颜色
                paintOne.color = mLeftColor
                if (op) {
                    val iterator = RegionIterator(region3)
                    val rect = Rect()
                    while (iterator.next(rect)) {
                        canvas?.drawRect(rect, paintOne)
                    }
                }
            }


            //有分割线
            if (haveCutLine()) {
                val region4 = Region()
                getCutPathByTilt()?.let {
                    region4.setPath(it, region)
                    val op2 = region4.op(region2, Region.Op.INTERSECT)
                    paintOne.color = mLineColor

//                    Log.i(TAG2, "onDraw: 绘制分割线$op2")
                    if (op2) {
                        val iterator = RegionIterator(region4)
                        val rect = Rect()
                        while (iterator.next(rect)) {
                            paintOne.isAntiAlias = true
                            canvas?.drawRect(rect, paintOne)
                        }
                    }
                }
            }
        }
    }

    //4：绘制左右文字
    private fun drawProgressText(canvas: Canvas?) {
        //获取进度值画笔
        PaintFactory.createPaint(TextPaint(mPaintText, mTextLeftColor, mValueTextSize))?.let {
            //绘制左边进度文字
            if (showLeftText) {
                val valueString = if (mLeftTrueString.isNotEmpty()) {
                    if (showLeftBeforeUnit) {
                        mCenterTextMany
                    } else {
                        ""
                    } + mLeftTrueString + this.mProgressUnit
                } else {
                    if (showLeftBeforeUnit) {
                        mCenterTextMany
                    } else {
                        ""
                    } + "${NumberUtils.setFloatTo45(this.mLeftValue,2)}$mProgressUnit"
                }
                val fontMetrics = it.fontMetrics   //文字高度相关的信息都存在FontMetrics对象中
                val y: Float =
                    (height) / 2 + (abs(fontMetrics.ascent) - fontMetrics.descent) / 2 //|ascent|=descent+ 2 * ( 2号线和3号线之间的距离 )
//                Log.i(TAG, "左边onDraw:绘制内容： $valueString")
                //绘制
                canvas?.drawText(
                    valueString, 0f + mPadding, y, it
                )
            }

            //绘制右边文字
            if (showRightText) {
                val valueString = if (mRightTrueString.isNotEmpty()) {
                    if (showRightBeforeUnit) {
                        mCenterTextFew
                    } else {
                        ""
                    } + mRightTrueString + mProgressUnit
                } else {
                    if (showRightBeforeUnit) {
                        mCenterTextFew
                    } else {
                        ""
                    } + "${NumberUtils.setFloatTo45(mRightValue,2)}$mProgressUnit"
                }
                val stringWidth = it.measureText(valueString)
                val x = (width - stringWidth - mPadding)
                val fontMetrics = it.fontMetrics
                it.color = mTextRightColor
                val y: Float =
                    (height) / 2 + (abs(fontMetrics.ascent) - fontMetrics.descent) / 2 //|ascent|=descent+ 2 * ( 2号线和3号线之间的距离 )
                canvas?.drawText(valueString, x, y, it)
            }
        }
    }

    //5：绘制中间圆形背景
    private fun drawCenterBg(canvas: Canvas?) {
        Log.i(TAG, "drawCenterBg: 画背景")
        val bgColor = if (mLeftValue == 50f) {
            mCenterValuesameBgColor
        } else {
            mCricularBgColor
        }
        PaintFactory.createPaint(SolidPaint(mPaint, bgColor))?.let {
            val radius = getRadius()

            //中间圆形背景完全显示
            var centerX = width * mLeftValue / mTotailValue - abs(mTilt) / 2

            if (mCenterBgAllwayShow) {
                if (centerX < radius) {
                    centerX = radius
                } else if ((width * mRightValue / mTotailValue) < radius) {
                    centerX = width - radius
                }
            }
            canvas?.drawCircle(
                centerX,
                height / 2.toFloat(),
                radius,
                it
            )
        }
    }

    //6：绘制中间圆上的字
    private fun drawCenterText(canvas: Canvas?) {
        //画圆中间的字
        getPaintCenterText()?.let {
            val stringWidth = it.measureText(mCenterText)

            var centerX = (width * mLeftValue / mTotailValue) - abs(mTilt) / 2
            //极限值处理
            if (mCenterBgAllwayShow) {
                val radius = getRadius()
                if (centerX < radius) {
                    centerX = radius
                } else if ((width * mRightValue / mTotailValue) < radius) {
                    centerX = width - radius
                }
            }
            val x = centerX - stringWidth / 2
            val fontMetrics = it.fontMetrics
            val y: Float =
                (height) / 2 + (abs(fontMetrics.ascent) - fontMetrics.descent) / 2 //|ascent|=descent+ 2 * ( 2号线和3号线之间的距离 )
            canvas?.drawText(mCenterText, x, y, it)
        }
    }


    //-------------------------------------------------------------------------------

    //矩形左边区域
    private fun getRectLeftPath(): Path? {
        return if (mTilt > 0) {
            val arrayOf1 = arrayOf(
                PoinEntity(0f, 0f),
                PoinEntity(width * (mLeftValue / mTotailValue) - mLineSize / 2, 0f),
                PoinEntity(
                    width * (mLeftValue / mTotailValue) - mTilt - mLineSize / 2,
                    height.toFloat()
                ),
                PoinEntity(0f, height.toFloat())
            )
            PathFactory.createPath(RectanglePath(mPathChart, arrayOf1))

        } else {
            val arrayOf2 = arrayOf(
                PoinEntity(0f, 0f),
                PoinEntity(width * (mLeftValue / mTotailValue) - mLineSize / 2 + mTilt, 0f),
                PoinEntity(width * (mLeftValue / mTotailValue) - mLineSize / 2, height.toFloat()),
                PoinEntity(0f, height.toFloat())
            )
            PathFactory.createPath(RectanglePath(mPathChart, arrayOf2))
        }
    }

    //圆角无分隔符情况下的第二层的路径
    private fun getSecoundPath(): Path? {
        return if (mTilt > 0) {
            val listPoint1 = arrayOf(
                PoinEntity(0f, 0f),
                PoinEntity(width * (mLeftValue / mTotailValue), 0f),
                PoinEntity(width * (mLeftValue / mTotailValue) - mTilt, height.toFloat()),
                PoinEntity(0f, height.toFloat())
            )
            PathFactory.createPath(RectanglePath(listPoint1))
        } else {
            val listPoint2 = arrayOf(
                PoinEntity(0f, 0f),
                PoinEntity(width * (mLeftValue / mTotailValue) + mTilt, 0f),
                PoinEntity(width * (mLeftValue / mTotailValue), height.toFloat()),
                PoinEntity(0f, height.toFloat())
            )
            PathFactory.createPath(RectanglePath(listPoint2))
        }
    }

    //中间文字画笔
    private fun getPaintCenterText(): Paint? {
        var paint: Paint? = null
        PaintFactory.createPaint(TextPaint(mPaint, mCenterTextSize, 10f))?.let {
            it.typeface = Typeface.DEFAULT_BOLD
            when {
                mLeftValue > mTotailValue / 2 -> {
                    it.color = mCenterTextManyColor//画笔颜色
                    this.mCenterText = this.mCenterTextMany //中间显示内容
                }
                mLeftValue < mTotailValue / 2 -> {
                    it.color = mCenterTextFewColor
                    this.mCenterText = mCenterTextFew
                }
                else -> {
                    it.color = mCenterTextColor
                }
            }
            if (mCenterTextColorAlways != 0) {
                it.color = mCenterTextColorAlways
            }
            paint = it
        }
        return paint
    }

    //验证圆半径，不能大于高度的一半
    private fun getRadius(): Float {
        return if (mRadius > height / 2) {
            height / 2.toFloat()
        } else {
            mRadius
        }
    }

    //获取分割线path
    private fun getCutLinePath(): Path? {
        resetLeftValue()
        return getCutPathByTilt()
    }

    //通过重新设置进度值保证分割线有边界
    private fun resetLeftValue() {

        val minProgressValue =
            if (mProgressRadius > 0) (abs(mTilt) + mLineSize / 2 + height / 2) else {
                abs(mTilt) + mLineSize / 2
            }

        val rightReduce = if (mProgressRadius > 0) {
            height / 2 + mLineSize / 2
        } else {
            mLineSize / 2
        }

        //右极限
        if (mLeftValue > mTotailValue / 2) {
            if ((width - width * (mLeftValue / mTotailValue)) <= minProgressValue) {
                showRightText = false
                mLeftTrueString = "${NumberUtils.setFloatTo45(this.mLeftValue,2)}"
                mLeftValue = (width - rightReduce) / width * mTotailValue
                mRightValue = mTotailValue - mLeftValue
                showRightText = false
            } else {
                showRightText = true
            }
        }
        //左极限
        else {
            if (width * (mLeftValue / mTotailValue) <= minProgressValue) {
                mRightTrueString = "${NumberUtils.setFloatTo45(mRightValue,2)}"
                mLeftValue = (minProgressValue / width) * mTotailValue
                mRightValue = mTotailValue - mLeftValue
                showLeftText = false
//                Log.i(TAG, "resetLeftValue: 重置value值1：$mLeftValue")
            } else {
                mRightTrueString = ""
                showLeftText = true
            }
        }
    }

    //获取带指定圆角的path对象
    private fun getRadiusLeftPath(mProgressRadius: Float): Path? {
        val pathNew = if (mTilt > 0) {
            val pointList1 = arrayOf(
                PoinEntity(height.toFloat() / 2, 0f),
                PoinEntity(width * (mLeftValue / mTotailValue) - mLineSize / 2, 0f),
                PoinEntity(
                    width * (mLeftValue / mTotailValue) - mTilt - mLineSize / 2,
                    height.toFloat()
                ),
                PoinEntity(height.toFloat() / 2, height.toFloat())
            )
            PathFactory.createPath(RectanglePath(mPathChartAdd, pointList1))
        } else {
            val pointList2 = arrayOf(
                PoinEntity(height.toFloat() / 2, 0f),
                PoinEntity(width * (mLeftValue / mTotailValue) - mLineSize / 2 + mTilt, 0f),
                PoinEntity(width * (mLeftValue / mTotailValue) - mLineSize / 2, height.toFloat()),
                PoinEntity(height.toFloat() / 2, height.toFloat())
            )
            PathFactory.createPath(RectanglePath(mPathChartAdd, pointList2))
        }
        val path = PathFactory.createPath(
            FilletPath(
                RectF(0f, 0f, height.toFloat() / 2, height.toFloat()),
                mPathChart,
                mProgressRadius, 0f, 0f, mProgressRadius
            )
        )

        pathNew?.let {
            path?.addPath(it)
            path?.close() //闭合图形
        }
        return path
    }

    //获取右边进度路径
    private fun getRadiusRightPath(mProgressRadius: Float): Path? {

        if (mProgressRadius > 0f) {
            val path = if (mTilt > 0) {
                val listPoint = arrayOf(
                    PoinEntity(width * (mLeftValue / mTotailValue) + mLineSize / 2, 0f),
                    PoinEntity(width.toFloat() - height / 2, 0f),
                    PoinEntity(width.toFloat() - height / 2, height.toFloat()),
                    PoinEntity(
                        width * (mLeftValue / mTotailValue) + mLineSize / 2 - mTilt,
                        height.toFloat()
                    )
                )
                PathFactory.createPath(RectanglePath(mPathChart, listPoint))
            } else {
                val listPoint2 = arrayOf(
                    PoinEntity(width * (mLeftValue / mTotailValue) + mLineSize / 2 + mTilt, 0f),
                    PoinEntity(width.toFloat() - height / 2, 0f),
                    PoinEntity(width.toFloat() - height / 2, height.toFloat()),
                    PoinEntity(
                        width * (mLeftValue / mTotailValue) + mLineSize / 2,
                        height.toFloat()
                    )
                )
                PathFactory.createPath(RectanglePath(mPathChart, listPoint2))
            }

            val rectF = RectF((width - height / 2).toFloat(), 0f, width.toFloat(), height.toFloat())
            PathFactory.createPath(FilletPath(rectF, 0f, mProgressRadius, mProgressRadius, 0f))
                ?.let {
                    path?.addPath(it)
                }
            return path

        } else {
            return if (mTilt > 0) {
                val listPoint1 = arrayOf(
                    PoinEntity(width * (mLeftValue / mTotailValue) + mLineSize / 2, 0f),
                    PoinEntity(width.toFloat(), 0f),
                    PoinEntity(width.toFloat(), height.toFloat()),
                    PoinEntity(
                        width * (mLeftValue / mTotailValue) + mLineSize / 2 - mTilt,
                        height.toFloat()
                    )
                )
                PathFactory.createPath(RectanglePath(mPathChart, listPoint1))

            } else {
                val listPoint2 = arrayOf(
                    PoinEntity(width * (mLeftValue / mTotailValue) + mLineSize / 2 + mTilt, 0f),
                    PoinEntity(width.toFloat(), 0f),
                    PoinEntity(width.toFloat(), height.toFloat()),
                    PoinEntity(
                        width * (mLeftValue / mTotailValue) + mLineSize / 2,
                        height.toFloat()
                    )
                )
                PathFactory.createPath(RectanglePath(mPathChart, listPoint2))
            }
        }
    }

//-----------------------------------------------公共方法-----------------------------------

    //设置类型
    fun setType(mType: Type) {
        this.mType = mType
    }

    //设置数据
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
////        Log.i(TAG, "setData: 左边值：$mLeftValue 右边值：$mRightValue 总值：$mTotailValue")
//    }

    //设置数据
    fun setData(many: Float, toTail: Float) {

        mTotailValue = toTail
        mLeftValue = many
        mRightValue = toTail - mLeftValue


        if (toTail <= 1) {
            mLeftValue *= 100
            mTotailValue *= 100
            mRightValue *= 100
        }
    }

    //重绘制
    fun invaLidate() {
        postInvalidate()
    }


//-----------------------------------------------内部通用方法-----------------------------------


    //是否有分割线
    private fun haveCutLine(): Boolean {
        return mLineSize > 0f
    }

    //通过倾斜度获取完整的分割线路径
    private fun getCutPathByTilt(): Path? {
        return if (mTilt > 0) {
            PathFactory.createPath(
                RectanglePath(
                    mPathChart,
                    arrayOf(
                        PoinEntity(width * (mLeftValue / mTotailValue) - mLineSize / 2, 0f),
                        PoinEntity(width * (mLeftValue / mTotailValue) + mLineSize / 2, 0f),
                        PoinEntity(
                            width * (mLeftValue / mTotailValue) - mTilt + mLineSize / 2,
                            height.toFloat()
                        ),
                        PoinEntity(
                            width * (mLeftValue / mTotailValue) - mTilt - mLineSize / 2,
                            height.toFloat()
                        )
                    )
                )
            )

        } else {
            PathFactory.createPath(
                RectanglePath(
                    mPathChart,
                    arrayOf(
                        PoinEntity(width * (mLeftValue / mTotailValue) - mLineSize / 2 + mTilt, 0f),
                        PoinEntity(width * (mLeftValue / mTotailValue) + mLineSize / 2 + mTilt, 0f),
                        PoinEntity(
                            width * (mLeftValue / mTotailValue) + mLineSize / 2,
                            height.toFloat()
                        ),
                        PoinEntity(
                            width * (mLeftValue / mTotailValue) - mLineSize / 2, height.toFloat()
                        )
                    )
                )
            )
        }
    }


//-----------------------------------------------Builder模式创建-----------------------------------


    constructor(context: Context?, build: PercentageViewBuilder) : super(context) {
        this.mMinHeight = build.mMinHeight
        this.mMinWidth = build.mMinWidth
        this.mTextLeftColor = build.mTextLeftColor
        this.mLeftValue = build.mLeftValue
        this.mTotailValue = build.mTotailValue
        this.mLeftColor = build.mLeftColor
        this.mProgressRadius = build.mProgressRadius
        this.mTextRightColor = build.mTextRightColor
        this.mRightColor = build.mRightColor
        this.mValueTextSize = build.mValueTextSize
        this.mCenterTextSize = build.mCenterTextSize
        this.mPadding = build.mPadding
        this.mShowProgressText = build.mShowProgressText
        this.mProgressUnit = build.mProgressUnit
        this.mCenterTextMany = build.mCenterTextMany
        this.mCenterTextFew = build.mCenterTextFew
        this.mCenterText = build.mCenterText
        this.mRadius = build.mRadius
        this.mCricularBgColor = build.mCricularBgColor
        this.mTilt = build.mTilt
        this.mLineSize = build.mLineSize
        this.mLineColor = build.mLineColor
        this.mHaveLimitValue = build.mHaveLimitValue
        this.showLeftText = build.showLeftText
        this.showRightText = build.showRightText
        this.mCenterTextManyColor = build.mCenterTextManyColor
        this.mCenterTextFewColor = build.mCenterTextFewColor
        this.mCenterTextColorAlways = build.mCenterTextColorAlways
        this.mCenterTextColor = build.mCenterTextColor

        initData(context, null)
    }
}