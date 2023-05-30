package ru.serebryakovas.lukoilmobileap.legacy.cortex.views

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import kotlin.math.sqrt

@Suppress("unused")
class LVBlock : LVBase {
    private var mPaint: Paint? = null
    private var mPaintShadow: Paint? = null
    private var mPaintLeft: Paint? = null
    private var mPaintRight: Paint? = null
    private var mWidth = 0f
    private var moveYtoCenter = 0f
    private var rhomboidsX = 0f
    private var rhomboidsY = 0f

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    )
    constructor(context: Context?, attrs: AttributeSet? = null) : super(context, attrs)

    init {
        initPaint()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = if (measuredWidth > height) measuredHeight.toFloat() else measuredWidth.toFloat()
        rhomboidsX = (3 * mWidth / 16 / sqrt(3.0)).toFloat()
        rhomboidsY = mWidth / 16
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.save()
        moveYtoCenter = if (!mShadow) {
            mWidth / 4
        } else {
            0f
        }
        if (mAnimatedValue >= 0 && mAnimatedValue < 1.0f / 3) {
            drawStep1(canvas, mAnimatedValue)
            if (mShadow) drawShadowStep1(canvas, mAnimatedValue)
        } else if (mAnimatedValue >= 1.0f / 3 && mAnimatedValue < 1.0f / 3 * 2) {
            drawStep2(canvas, mAnimatedValue)
            if (mShadow) drawShadowStep2(canvas, mAnimatedValue)
        } else if (mAnimatedValue >= 1.0f / 3 * 2 && mAnimatedValue <= 1.0f) {
            drawStep3(canvas, mAnimatedValue)
            if (mShadow) drawShadowStep3(canvas, mAnimatedValue)
        }
        canvas.restore()
    }

    private fun drawStep1(canvas: Canvas, time: Float) {
        val moveX = rhomboidsX / 2.0f * time / (1.0f / 3)
        val moveY = rhomboidsY / 2.0f * time / (1.0f / 3)
        val p = Path()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 - moveX,
            rhomboidsY * 12 - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX - moveX,
            rhomboidsY * 11 - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(mWidth / 2 - moveX, mWidth / 4 * 3 - moveY - mWidth / 2 + moveYtoCenter)
        p.lineTo(
            mWidth / 2 - rhomboidsX - moveX,
            rhomboidsY * 13 - moveY - mWidth / 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaint!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 - moveX,
            rhomboidsY * 12 - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX - moveX,
            rhomboidsY * 13 - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX - moveX,
            rhomboidsY * 13 - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX * 2 - moveX,
            rhomboidsY * 12 - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaintLeft!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX + moveX,
            rhomboidsY * 12 - rhomboidsY + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX + rhomboidsX + moveX,
            rhomboidsY * 11 - rhomboidsY + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + rhomboidsX + moveX,
            mWidth / 4 * 3 - rhomboidsY + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX + moveX,
            rhomboidsY * 13 - rhomboidsY + moveY - mWidth / 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaint!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX + moveX,
            rhomboidsY * 12 + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX + moveX,
            rhomboidsY * 11 + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + rhomboidsX + rhomboidsX + moveX,
            mWidth / 4 * 3 + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + moveX,
            rhomboidsY * 13 + moveY - mWidth / 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaint!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX + moveX,
            rhomboidsY * 12 + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + moveX,
            rhomboidsY * 13 + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + moveX,
            rhomboidsY * 13 + moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX + moveX,
            rhomboidsY * 12 + moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaintLeft!!)
        p.reset()
        p.moveTo(
            mWidth / 2 + rhomboidsX + rhomboidsX + moveX,
            mWidth / 4 * 3 + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + moveX,
            rhomboidsY * 13 + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + moveX,
            rhomboidsY * 13 + moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + rhomboidsX + rhomboidsX + moveX,
            mWidth / 4 * 3 + moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaintRight!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX - moveX,
            rhomboidsY * 12 + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX + rhomboidsX - moveX,
            rhomboidsY * 11 + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + rhomboidsX - moveX,
            mWidth / 4 * 3 + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX - moveX,
            rhomboidsY * 13 + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaint!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX - moveX,
            rhomboidsY * 12 + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX - moveX,
            rhomboidsY * 13 + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX - moveX,
            rhomboidsY * 13 + rhomboidsY - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX - moveX,
            rhomboidsY * 12 + rhomboidsY - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaintLeft!!)
        p.reset()
        p.moveTo(
            mWidth / 2 + rhomboidsX - moveX,
            mWidth / 4 * 3 + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX - moveX,
            rhomboidsY * 13 + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX - moveX,
            rhomboidsY * 13 + rhomboidsY - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + rhomboidsX - moveX,
            mWidth / 4 * 3 + rhomboidsY - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaintRight!!)
    }

    private fun drawStep2(canvas: Canvas, time: Float) {
        val moveX = rhomboidsX * (time - 1.0f / 3) / (1.0f / 3)
        val moveY = rhomboidsY * (time - 1.0f / 3) / (1.0f / 3)
        val p = Path()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 - rhomboidsX / 2.0f + moveX,
            rhomboidsY * 12 - rhomboidsY / 2.0f - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX - rhomboidsX / 2.0f + moveX,
            rhomboidsY * 11 - rhomboidsY / 2.0f - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX / 2.0f + moveX,
            mWidth / 4 * 3 - rhomboidsY / 2.0f - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX - rhomboidsX / 2.0f + moveX,
            rhomboidsY * 13 - rhomboidsY / 2.0f - moveY - mWidth / 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaint!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 - rhomboidsX / 2.0f + moveX,
            rhomboidsY * 12 - rhomboidsY / 2.0f - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX - rhomboidsX / 2.0f + moveX,
            rhomboidsY * 13 - rhomboidsY / 2.0f - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX - rhomboidsX / 2.0f + moveX,
            rhomboidsY * 13 - rhomboidsY / 2.0f - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX * 2 - rhomboidsX / 2.0f + moveX,
            rhomboidsY * 12 - rhomboidsY / 2.0f - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaintLeft!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX / 2.0f,
            rhomboidsY * 12 - rhomboidsY + rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX / 2.0f,
            rhomboidsY * 11 - rhomboidsY + rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + rhomboidsX + rhomboidsX / 2.0f,
            mWidth / 4 * 3 - rhomboidsY + rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX / 2.0f,
            rhomboidsY * 13 - rhomboidsY + rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaint!!)
        p.reset()
        p.moveTo(
            mWidth / 2 + rhomboidsX + rhomboidsX / 2.0f,
            mWidth / 4 * 3 - rhomboidsY + rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX / 2.0f,
            rhomboidsY * 13 - rhomboidsY + rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX / 2.0f,
            rhomboidsY * 13 - rhomboidsY + rhomboidsY / 2.0f - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + rhomboidsX + rhomboidsX / 2.0f,
            mWidth / 4 * 3 - rhomboidsY + rhomboidsY / 2.0f - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaintRight!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX - rhomboidsX / 2.0f,
            rhomboidsY * 12 + rhomboidsY - rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX + rhomboidsX - rhomboidsX / 2.0f,
            rhomboidsY * 11 + rhomboidsY - rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + rhomboidsX - rhomboidsX / 2.0f,
            mWidth / 4 * 3 + rhomboidsY - rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX - rhomboidsX / 2.0f,
            rhomboidsY * 13 + rhomboidsY - rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaint!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX - rhomboidsX / 2.0f,
            rhomboidsY * 12 + rhomboidsY - rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX - rhomboidsX / 2.0f,
            rhomboidsY * 13 + rhomboidsY - rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX - rhomboidsX / 2.0f,
            rhomboidsY * 13 + rhomboidsY - rhomboidsY / 2.0f - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX - rhomboidsX / 2.0f,
            rhomboidsY * 12 + rhomboidsY - rhomboidsY / 2.0f - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaintLeft!!)
        p.reset()
        p.moveTo(
            mWidth / 2 + rhomboidsX - rhomboidsX / 2.0f,
            mWidth / 4 * 3 + rhomboidsY - rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX - rhomboidsX / 2.0f,
            rhomboidsY * 13 + rhomboidsY - rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX - rhomboidsX / 2.0f,
            rhomboidsY * 13 + rhomboidsY - rhomboidsY / 2.0f - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + rhomboidsX - rhomboidsX / 2.0f,
            mWidth / 4 * 3 + rhomboidsY - rhomboidsY / 2.0f - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaintRight!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX,
            rhomboidsY * 12 + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX,
            rhomboidsY * 11 + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX,
            mWidth / 4 * 3 + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX,
            rhomboidsY * 13 + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaint!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX,
            rhomboidsY * 12 + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX,
            rhomboidsY * 13 + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX,
            rhomboidsY * 13 + rhomboidsY / 2.0f + moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX,
            rhomboidsY * 12 + rhomboidsY / 2.0f + moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaintLeft!!)
        p.reset()
        p.moveTo(
            mWidth / 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX,
            mWidth / 4 * 3 + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX,
            rhomboidsY * 13 + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX,
            rhomboidsY * 13 + rhomboidsY / 2.0f + moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX,
            mWidth / 4 * 3 + rhomboidsY / 2.0f + moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaintRight!!)
    }

    private fun drawStep3(canvas: Canvas, time: Float) {
        val moveX = rhomboidsX / 2.0f * (time - 1.0f / 3 * 2) / (1.0f / 3)
        val moveY = rhomboidsY / 2.0f * (time - 1.0f / 3 * 2) / (1.0f / 3)
        val p = Path()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 - rhomboidsX / 2.0f + rhomboidsX + moveX,
            rhomboidsY * 12 - rhomboidsY / 2.0f - rhomboidsY + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX - rhomboidsX / 2.0f + rhomboidsX + moveX,
            rhomboidsY * 11 - rhomboidsY / 2.0f - rhomboidsY + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX / 2.0f + rhomboidsX + moveX,
            mWidth / 4 * 3 - rhomboidsY / 2.0f - rhomboidsY + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX - rhomboidsX / 2.0f + rhomboidsX + moveX,
            rhomboidsY * 13 - rhomboidsY / 2.0f - rhomboidsY + moveY - mWidth / 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaint!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 - rhomboidsX / 2.0f + rhomboidsX + moveX,
            rhomboidsY * 12 - rhomboidsY / 2.0f - rhomboidsY + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX - rhomboidsX / 2.0f + rhomboidsX + moveX,
            rhomboidsY * 13 - rhomboidsY / 2.0f - rhomboidsY + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX - rhomboidsX / 2.0f + rhomboidsX + moveX,
            rhomboidsY * 13 - rhomboidsY / 2.0f - rhomboidsY + moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX * 2 - rhomboidsX / 2.0f + rhomboidsX + moveX,
            rhomboidsY * 12 - rhomboidsY / 2.0f - rhomboidsY + moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaintLeft!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX / 2.0f + moveX,
            rhomboidsY * 12 - rhomboidsY + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX / 2.0f + moveX,
            rhomboidsY * 11 - rhomboidsY + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + rhomboidsX + rhomboidsX / 2.0f + moveX,
            mWidth / 4 * 3 - rhomboidsY + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX / 2.0f + moveX,
            rhomboidsY * 13 - rhomboidsY + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaint!!)
        p.reset()
        p.moveTo(
            mWidth / 2 + rhomboidsX + rhomboidsX / 2.0f + moveX,
            mWidth / 4 * 3 - rhomboidsY + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX / 2.0f + moveX,
            rhomboidsY * 13 - rhomboidsY + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX / 2.0f + moveX,
            rhomboidsY * 13 - rhomboidsY + rhomboidsY / 2.0f + moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + rhomboidsX + rhomboidsX / 2.0f + moveX,
            mWidth / 4 * 3 - rhomboidsY + rhomboidsY / 2.0f + moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaintRight!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX,
            rhomboidsY * 12 + rhomboidsY / 2.0f + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX,
            rhomboidsY * 11 + rhomboidsY / 2.0f + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX,
            mWidth / 4 * 3 + rhomboidsY / 2.0f + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX,
            rhomboidsY * 13 + rhomboidsY / 2.0f + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaint!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX,
            rhomboidsY * 12 + rhomboidsY / 2.0f + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX,
            rhomboidsY * 13 + rhomboidsY / 2.0f + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX,
            rhomboidsY * 13 + rhomboidsY / 2.0f + rhomboidsY - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX,
            rhomboidsY * 12 + rhomboidsY / 2.0f + rhomboidsY - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaintLeft!!)
        p.reset()
        p.moveTo(
            mWidth / 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX,
            mWidth / 4 * 3 + rhomboidsY / 2.0f + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX,
            rhomboidsY * 13 + rhomboidsY / 2.0f + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX,
            rhomboidsY * 13 + rhomboidsY / 2.0f + rhomboidsY - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX,
            mWidth / 4 * 3 + rhomboidsY / 2.0f + rhomboidsY - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaintRight!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX - rhomboidsX / 2.0f - moveX,
            rhomboidsY * 12 + rhomboidsY - rhomboidsY / 2.0f - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX + rhomboidsX - rhomboidsX / 2.0f - moveX,
            rhomboidsY * 11 + rhomboidsY - rhomboidsY / 2.0f - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + rhomboidsX - rhomboidsX / 2.0f - moveX,
            mWidth / 4 * 3 + rhomboidsY - rhomboidsY / 2.0f - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX - rhomboidsX / 2.0f - moveX,
            rhomboidsY * 13 + rhomboidsY - rhomboidsY / 2.0f - moveY - mWidth / 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaint!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX - rhomboidsX / 2.0f - moveX,
            rhomboidsY * 12 + rhomboidsY - rhomboidsY / 2.0f - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX - rhomboidsX / 2.0f - moveX,
            rhomboidsY * 13 + rhomboidsY - rhomboidsY / 2.0f - moveY - mWidth / 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX - rhomboidsX / 2.0f - moveX,
            rhomboidsY * 13 + rhomboidsY - rhomboidsY / 2.0f - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX - rhomboidsX / 2.0f - moveX,
            rhomboidsY * 12 + rhomboidsY - rhomboidsY / 2.0f - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter
        )
        p.close()
        canvas.drawPath(p, mPaintLeft!!)
    }

    private fun drawShadowStep1(canvas: Canvas, time: Float) {
        val moveX = rhomboidsX / 2.0f * time / (1.0f / 3)
        val moveY = rhomboidsY / 2.0f * time / (1.0f / 3)
        val p = Path()
        p.moveTo(mWidth / 2 - rhomboidsX * 2 - moveX, rhomboidsY * 12 - moveY)
        p.lineTo(mWidth / 2 - rhomboidsX - moveX, rhomboidsY * 11 - moveY)
        p.lineTo(mWidth / 2 - moveX, mWidth / 4 * 3 - moveY)
        p.lineTo(mWidth / 2 - rhomboidsX - moveX, rhomboidsY * 13 - moveY)
        p.close()
        canvas.drawPath(p, mPaintShadow!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX + moveX,
            rhomboidsY * 12 - rhomboidsY + moveY
        )
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX + moveX, rhomboidsY * 11 - rhomboidsY + moveY)
        p.lineTo(mWidth / 2 + rhomboidsX + moveX, mWidth / 4 * 3 - rhomboidsY + moveY)
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX + moveX,
            rhomboidsY * 13 - rhomboidsY + moveY
        )
        p.close()
        canvas.drawPath(p, mPaintShadow!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX + moveX,
            rhomboidsY * 12 + moveY
        )
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX + moveX, rhomboidsY * 11 + moveY)
        p.lineTo(mWidth / 2 + rhomboidsX + rhomboidsX + moveX, mWidth / 4 * 3 + moveY)
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + moveX,
            rhomboidsY * 13 + moveY
        )
        p.close()
        canvas.drawPath(p, mPaintShadow!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX - moveX,
            rhomboidsY * 12 + rhomboidsY - moveY
        )
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX - moveX, rhomboidsY * 11 + rhomboidsY - moveY)
        p.lineTo(mWidth / 2 + rhomboidsX - moveX, mWidth / 4 * 3 + rhomboidsY - moveY)
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX - moveX,
            rhomboidsY * 13 + rhomboidsY - moveY
        )
        p.close()
        canvas.drawPath(p, mPaintShadow!!)
    }

    private fun drawShadowStep2(canvas: Canvas, time: Float) {
        val moveX = rhomboidsX * (time - 1.0f / 3) / (1.0f / 3)
        val moveY = rhomboidsY * (time - 1.0f / 3) / (1.0f / 3)
        val p = Path()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 - rhomboidsX / 2.0f + moveX,
            rhomboidsY * 12 - rhomboidsY / 2.0f - moveY
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX - rhomboidsX / 2.0f + moveX,
            rhomboidsY * 11 - rhomboidsY / 2.0f - moveY
        )
        p.lineTo(mWidth / 2 - rhomboidsX / 2.0f + moveX, mWidth / 4 * 3 - rhomboidsY / 2.0f - moveY)
        p.lineTo(
            mWidth / 2 - rhomboidsX - rhomboidsX / 2.0f + moveX,
            rhomboidsY * 13 - rhomboidsY / 2.0f - moveY
        )
        p.close()
        canvas.drawPath(p, mPaintShadow!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX / 2.0f,
            rhomboidsY * 12 - rhomboidsY + rhomboidsY / 2.0f
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX / 2.0f,
            rhomboidsY * 11 - rhomboidsY + rhomboidsY / 2.0f
        )
        p.lineTo(
            mWidth / 2 + rhomboidsX + rhomboidsX / 2.0f,
            mWidth / 4 * 3 - rhomboidsY + rhomboidsY / 2.0f
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX / 2.0f,
            rhomboidsY * 13 - rhomboidsY + rhomboidsY / 2.0f
        )
        p.close()
        canvas.drawPath(p, mPaintShadow!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX,
            rhomboidsY * 12 + rhomboidsY / 2.0f + moveY
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX,
            rhomboidsY * 11 + rhomboidsY / 2.0f + moveY
        )
        p.lineTo(
            mWidth / 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX,
            mWidth / 4 * 3 + rhomboidsY / 2.0f + moveY
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX,
            rhomboidsY * 13 + rhomboidsY / 2.0f + moveY
        )
        p.close()
        canvas.drawPath(p, mPaintShadow!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX - rhomboidsX / 2.0f,
            rhomboidsY * 12 + rhomboidsY - rhomboidsY / 2.0f
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX + rhomboidsX - rhomboidsX / 2.0f,
            rhomboidsY * 11 + rhomboidsY - rhomboidsY / 2.0f
        )
        p.lineTo(
            mWidth / 2 + rhomboidsX - rhomboidsX / 2.0f,
            mWidth / 4 * 3 + rhomboidsY - rhomboidsY / 2.0f
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX - rhomboidsX / 2.0f,
            rhomboidsY * 13 + rhomboidsY - rhomboidsY / 2.0f
        )
        p.close()
        canvas.drawPath(p, mPaintShadow!!)
    }

    private fun drawShadowStep3(canvas: Canvas, time: Float) {
        val moveX = rhomboidsX / 2.0f * (time - 1.0f / 3 * 2) / (1.0f / 3)
        val moveY = rhomboidsY / 2.0f * (time - 1.0f / 3 * 2) / (1.0f / 3)
        val p = Path()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 - rhomboidsX / 2.0f + rhomboidsX + moveX,
            rhomboidsY * 12 - rhomboidsY / 2.0f - rhomboidsY + moveY
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX - rhomboidsX / 2.0f + rhomboidsX + moveX,
            rhomboidsY * 11 - rhomboidsY / 2.0f - rhomboidsY + moveY
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX / 2.0f + rhomboidsX + moveX,
            mWidth / 4 * 3 - rhomboidsY / 2.0f - rhomboidsY + moveY
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX - rhomboidsX / 2.0f + rhomboidsX + moveX,
            rhomboidsY * 13 - rhomboidsY / 2.0f - rhomboidsY + moveY
        )
        p.close()
        canvas.drawPath(p, mPaintShadow!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX / 2.0f + moveX,
            rhomboidsY * 12 - rhomboidsY + rhomboidsY / 2.0f + moveY
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX / 2.0f + moveX,
            rhomboidsY * 11 - rhomboidsY + rhomboidsY / 2.0f + moveY
        )
        p.lineTo(
            mWidth / 2 + rhomboidsX + rhomboidsX / 2.0f + moveX,
            mWidth / 4 * 3 - rhomboidsY + rhomboidsY / 2.0f + moveY
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX / 2.0f + moveX,
            rhomboidsY * 13 - rhomboidsY + rhomboidsY / 2.0f + moveY
        )
        p.close()
        canvas.drawPath(p, mPaintShadow!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX,
            rhomboidsY * 12 + rhomboidsY / 2.0f + rhomboidsY - moveY
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX,
            rhomboidsY * 11 + rhomboidsY / 2.0f + rhomboidsY - moveY
        )
        p.lineTo(
            mWidth / 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX,
            mWidth / 4 * 3 + rhomboidsY / 2.0f + rhomboidsY - moveY
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX,
            rhomboidsY * 13 + rhomboidsY / 2.0f + rhomboidsY - moveY
        )
        p.close()
        canvas.drawPath(p, mPaintShadow!!)
        p.reset()
        p.moveTo(
            mWidth / 2 - rhomboidsX * 2 + rhomboidsX - rhomboidsX / 2.0f - moveX,
            rhomboidsY * 12 + rhomboidsY - rhomboidsY / 2.0f - moveY
        )
        p.lineTo(
            mWidth / 2 - rhomboidsX + rhomboidsX - rhomboidsX / 2.0f - moveX,
            rhomboidsY * 11 + rhomboidsY - rhomboidsY / 2.0f - moveY
        )
        p.lineTo(
            mWidth / 2 + rhomboidsX - rhomboidsX / 2.0f - moveX,
            mWidth / 4 * 3 + rhomboidsY - rhomboidsY / 2.0f - moveY
        )
        p.lineTo(
            mWidth / 2 + -rhomboidsX + rhomboidsX - rhomboidsX / 2.0f - moveX,
            rhomboidsY * 13 + rhomboidsY - rhomboidsY / 2.0f - moveY
        )
        p.close()
        canvas.drawPath(p, mPaintShadow!!)
    }

    private fun initBlockPaint() {
        mPaint = Paint()
        mPaint!!.isAntiAlias = true
        mPaint!!.style = Paint.Style.FILL_AND_STROKE
        mPaint!!.color = Color.rgb(247, 202, 42)
        mPaint!!.strokeWidth = 1f
        mPaintShadow = Paint()
        mPaintShadow!!.isAntiAlias = true
        mPaintShadow!!.style = Paint.Style.FILL_AND_STROKE
        mPaintShadow!!.color = Color.rgb(0, 0, 0)
        mPaintShadow!!.strokeWidth = 1f
        mPaintLeft = Paint()
        mPaintLeft!!.isAntiAlias = true
        mPaintLeft!!.style = Paint.Style.FILL_AND_STROKE
        mPaintLeft!!.color = Color.rgb(227, 144, 11)
        mPaintLeft!!.strokeWidth = 1f
        mPaintRight = Paint()
        mPaintRight!!.isAntiAlias = true
        mPaintRight!!.style = Paint.Style.FILL_AND_STROKE
        mPaintRight!!.color = Color.rgb(188, 91, 26)
        mPaintRight!!.strokeWidth = 1f
    }

    fun setViewColor(color: Int) {
        mPaint!!.color = color
        val red = color and 0xff0000 shr 16
        val green = color and 0x00ff00 shr 8
        val blue = color and 0x0000ff
        mPaintLeft!!.color = Color.rgb(
            if (red - 15 > 0) red - 15 else 0,
            if (green - 58 > 0) green - 58 else 0,
            if (blue - 31 > 0) blue - 31 else 0
        )
        mPaintRight!!.color = Color.rgb(
            if (red - 59 > 0) red - 59 else 0,
            if (green - 111 > 0) green - 111 else 0,
            if (blue - 16 > 0) blue - 16 else 0
        )
        postInvalidate()
    }

    fun setShadowColor(color: Int) {
        mPaintShadow!!.color = color
        postInvalidate()
        postInvalidate()
    }

    private var mAnimatedValue = 0f
    override fun initPaint() {
        initBlockPaint()
    }

    override fun onAnimationUpdate(valueAnimator: ValueAnimator?) {
        mAnimatedValue = valueAnimator!!.animatedValue as Float
        invalidate()
    }

    override fun onAnimationRepeat(animation: Animator?) {}
    override fun onStopAnim(): Int {
        mAnimatedValue = 0f
        postInvalidate()
        return 1
    }

    override fun setAnimRepeatMode(): Int {
        return ValueAnimator.RESTART
    }

    private var mShadow = true
    fun isShadow(show: Boolean) {
        mShadow = show
        invalidate()
    }

    override fun animIsRunning() {}
    override fun setAnimRepeatCount(): Int {
        return ValueAnimator.INFINITE
    }
}