package ru.serebryakovas.lukoilmobileap.legacy.cortex.views

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator

@Suppress("SameParameterValue", "MemberVisibilityCanBePrivate", "unused")
abstract class LVBase @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    fun startAnim() {
        stopAnim()
        startViewAnim(0f, 1f, 500)
    }

    fun startAnim(time: Int) {
        stopAnim()
        startViewAnim(0f, 1f, time.toLong())
    }

    fun stopAnim() {
        if (valueAnimator != null) {
            clearAnimation()
            valueAnimator!!.repeatCount = 0
            valueAnimator!!.cancel()
            valueAnimator!!.end()
            if (onStopAnim() == 0) {
                valueAnimator!!.repeatCount = 0
                valueAnimator!!.cancel()
                valueAnimator!!.end()
            }
        }
    }

    private var valueAnimator: ValueAnimator? = null

    private fun startViewAnim(startF: Float, endF: Float, time: Long): ValueAnimator? {
        valueAnimator = ValueAnimator.ofFloat(startF, endF).also { valueAnimator ->
            valueAnimator.duration = time
            valueAnimator.interpolator = LinearInterpolator()
            valueAnimator.repeatCount = setAnimRepeatCount()
            if (ValueAnimator.RESTART == setAnimRepeatMode()) {
                valueAnimator.repeatMode = ValueAnimator.RESTART
            } else if (ValueAnimator.REVERSE == setAnimRepeatMode()) {
                valueAnimator.repeatMode = ValueAnimator.REVERSE
            }
            valueAnimator.addUpdateListener { animator ->
                onAnimationUpdate(
                    animator
                )
            }
            valueAnimator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationRepeat(animation: Animator) {
                    super.onAnimationRepeat(animation)
                    this@LVBase.onAnimationRepeat(animation)
                }
            })
            if (!valueAnimator.isRunning) {
                animIsRunning()
                valueAnimator.start()
            }
        }
        return valueAnimator
    }

    protected abstract fun initPaint()
    protected abstract fun onAnimationUpdate(valueAnimator: ValueAnimator?)
    protected abstract fun onAnimationRepeat(animation: Animator?)
    protected abstract fun onStopAnim(): Int
    protected abstract fun setAnimRepeatMode(): Int
    protected abstract fun setAnimRepeatCount(): Int
    protected abstract fun animIsRunning()

    fun dip2px(dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun getFontLength(paint: Paint, str: String): Float {
        val rect = Rect()
        paint.getTextBounds(str, 0, str.length, rect)
        return rect.width().toFloat()
    }

    fun getFontHeight(paint: Paint, str: String): Float {
        val rect = Rect()
        paint.getTextBounds(str, 0, str.length, rect)
        return rect.height().toFloat()
    }

    fun getFontHeight(paint: Paint): Float {
        val fm = paint.fontMetrics
        return fm.descent - fm.ascent
    }
}