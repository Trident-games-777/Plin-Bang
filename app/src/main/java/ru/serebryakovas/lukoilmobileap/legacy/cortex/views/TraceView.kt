package ru.serebryakovas.lukoilmobileap.legacy.cortex.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import ru.serebryakovas.lukoilmobileap.legacy.cortex.Offset

class TraceView : View {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet? = null) : super(context, attrs)
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
    ) : super(context, attrs, defStyleAttr)

    private val tracePaint = Paint().apply {
        isAntiAlias = true
    }
    private var trace: Pair<Offset, Offset>? = null

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            trace?.let { (start, end) ->
                canvas.drawLine(start.x, start.y, end.x, end.y, tracePaint)
            }
        }
    }

    fun drawTrace(pos: Pair<Offset, Offset>?, width: Float = 10f) {
        this.trace = pos
        pos?.let {
            tracePaint.strokeWidth = width
            tracePaint.shader = LinearGradient(
                pos.first.x,
                pos.first.y,
                pos.second.x,
                pos.second.y,
                Color.WHITE,
                Color.TRANSPARENT,
                Shader.TileMode.CLAMP
            )
        }
        invalidate()
    }
}