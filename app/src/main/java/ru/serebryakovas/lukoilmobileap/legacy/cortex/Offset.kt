package ru.serebryakovas.lukoilmobileap.legacy.cortex

import android.graphics.Rect
import kotlin.math.*

data class Offset(
    val x: Float,
    val y: Float
) {

    private val length: Float
        get() = sqrt(x * x + y * y)

    val normalized: Offset
        get() = length.let { mag ->
            if (mag > kEpsilon) this / mag
            else zero
        }

    operator fun minus(p: Offset): Offset {
        return Offset(
            x = x - p.x,
            y = y - p.y
        )
    }
    operator fun times(f: Float): Offset {
        return Offset(
            x = x * f,
            y = y * f
        )
    }
    operator fun times(i: Int): Offset {
        return Offset(
            x = x * i,
            y = y * i
        )
    }
    operator fun times(d: Double): Offset {
        return this * d.toFloat()
    }
    operator fun div(f: Float): Offset {
        return Offset(
            x = x / f,
            y = y / f
        )
    }
    companion object {
        const val kEpsilon = 0.00001f
        val zero: Offset = Offset(x = 0f, y = 0f)
    }
}

fun Rect.contains(offset: Offset): Boolean {
    return contains(offset.x.toInt(), offset.y.toInt())
}