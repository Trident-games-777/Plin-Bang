package ru.serebryakovas.lukoilmobileap.legacy.fluffygames

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Point
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnLayout
import ru.serebryakovas.lukoilmobileap.legacy.cortex.Offset
import ru.serebryakovas.lukoilmobileap.R
import ru.serebryakovas.lukoilmobileap.legacy.cortex.contains
import ru.serebryakovas.lukoilmobileap.databinding.FragmentFluffyGamesBinding
import kotlin.random.Random

class FluffyController(
    private val binding: FragmentFluffyGamesBinding
) {

    private val random = Random(System.currentTimeMillis())
    private val colors = listOf(
        Color.RED,
        Color.BLUE,
        Color.GREEN,
        Color.YELLOW,
        Color.CYAN,
        Color.MAGENTA,
    )
    private val searchIndices = listOf(
        Point(-1, 0),
        Point(0, 1),
        Point(1, 0),
        Point(0, -1),
    )

    private val balls = mutableListOf<ImageView?>()
    private val same = mutableListOf<Int>()

    private var ball: ImageView? = null
    private val ballHolderRect = Rect()
    private var ballMotionAnimator: ValueAnimator? = null

    private var cols = 0
    private var rows = 0

    private var isActiveTouch = false
    private var isBallMoving = false

    private var balance = 10000
        set(value) {
            field = value
            binding.balance.text = value.toString()
        }

    @SuppressLint("ClickableViewAccessibility")
    fun start() {
        isActiveTouch = false
        isBallMoving = false
        balance = 5000

        binding.ballHolder.doOnLayout {
            val point = IntArray(2)
            it.getLocationOnScreen(point)
            val (x, y) = point
            ballHolderRect.set(x, y, x + it.width, y + it.height)
        }

        binding.ballsPool.doOnLayout {
            val pool = binding.ballsPool
            val with = pool.width
            val height = pool.height

            val ballSize = height / 7f
            cols = (with / ballSize).toInt()
            rows = (height / ballSize).toInt()
            val xOffset = (with - cols * ballSize) * 0.5f

            for (i in 0 until rows) {
                for (j in 0 until cols) {
                    val ball = ImageView(pool.context)
                    pool.addView(ball)
                    ball.setImageResource(R.drawable.ball)
                    val layoutParams = FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT
                    )
                    layoutParams.width = ballSize.toInt()
                    layoutParams.height = ballSize.toInt()
                    ball.layoutParams = layoutParams
                    ball.x = xOffset + j * ballSize
                    ball.y = i * ballSize
                    val color = colors.random(random)
                    ball.tag = color
                    ball.imageTintList = ColorStateList.valueOf(color)
                    ball.imageTintMode = android.graphics.PorterDuff.Mode.MULTIPLY
                    balls.add(ball)
                }
            }

            ball = ImageView(binding.root.context).apply {
                setImageResource(R.drawable.ball)
                val color = colors.random(random)
                tag = color
                imageTintList = ColorStateList.valueOf(color)
                imageTintMode = android.graphics.PorterDuff.Mode.MULTIPLY
                val layoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                )
                layoutParams.width = ballSize.toInt()
                layoutParams.height = ballSize.toInt()
                layoutParams.topToTop = binding.ballHolder.id
                layoutParams.startToStart = binding.ballHolder.id
                layoutParams.endToEnd = binding.ballHolder.id
                layoutParams.bottomToBottom = binding.ballHolder.id
                binding.root.addView(this, layoutParams)
            }
        }

        binding.root.setOnTouchListener { _, motionEvent ->
            when(motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!isBallMoving) isActiveTouch = true
                    if (isActiveTouch) {
                        drawTrace(
                            Offset(
                                motionEvent.x,
                                motionEvent.y
                            )
                        )
                    }
                }
                MotionEvent.ACTION_MOVE -> {
                    if (isActiveTouch) {
                        drawTrace(
                            Offset(
                                motionEvent.x,
                                motionEvent.y
                            )
                        )
                    }
                }
                MotionEvent.ACTION_UP -> {
                    if (isActiveTouch) {
                        isActiveTouch = false
                        isBallMoving = true
                        balance -= 100
                        if (balance <= 0) {
                            onLose()
                        } else {
                            moveBall(
                                Offset(
                                    motionEvent.x,
                                    motionEvent.y
                                )
                            )
                        }
                    }
                }
            }
            isActiveTouch
        }
    }

    fun clearGame() {
        ballMotionAnimator?.cancel()
        ballMotionAnimator = null
        binding.root.removeView(ball)
        ball = null
        same.clear()
        binding.balance.text = ""
        balls.clear()
        binding.ballsPool.removeAllViews()
        binding.ballPlay.visibility = View.VISIBLE
        binding.lineView.drawTrace(null)
    }

    private fun drawTrace(endOffset: Offset) {
        val startOffset = ball?.let {
            Offset(it.x + it.width * 0.5f, it.y + it.height * 0.5f)
        } ?: Offset.zero

        binding.lineView.drawTrace(
            Pair(
                startOffset,
                endOffset
            ),
            ball?.width?.let { it * 0.5f } ?: 0f
        )
    }

    private fun moveBall(endOffset: Offset) {
        val startOffset = ball?.let {
            Offset(it.x + it.width * 0.5f, it.y + it.height * 0.5f)
        } ?: Offset.zero
        val direction = (endOffset - startOffset).normalized

        ballMotionAnimator = ValueAnimator.ofFloat(0f, 1f)
            .apply {
                repeatCount = ValueAnimator.INFINITE
                interpolator = LinearInterpolator()

                addUpdateListener {
                    ball!!.x += direction.x * 20f
                    ball!!.y += direction.y * 20f

                    val ballCenter = Offset(
                        ball!!.x + ball!!.width * 0.5f,
                        ball!!.y + ball!!.height * 0.5f
                    )
                    if (!binding.root.rect.contains(ballCenter)) {
                        resetBall()
                        return@addUpdateListener
                    }
                    if (binding.hole1.rect.contains(ballCenter)) {
                        onWin(2.5f)
                        return@addUpdateListener
                    }
                    if (binding.hole2.rect.contains(ballCenter)) {
                        onWin(0.5f)
                        return@addUpdateListener
                    }
                    if (binding.hole3.rect.contains(ballCenter)) {
                        onWin(0.5f)
                        return@addUpdateListener
                    }
                    if (binding.hole4.rect.contains(ballCenter)) {
                        onWin(1f)
                        return@addUpdateListener
                    }

                    for (i in balls.indices.reversed()) {
                        val currBall = balls[i]
                        if (currBall?.visibility == ImageView.VISIBLE && currBall.rect.contains(
                                ballCenter.x.toInt(),
                                ballCenter.y.toInt()
                            )
                        ) {
                            if (ball?.tag == currBall.tag) {
                                same.clear()
                                same.add(i)
                                findSame(i, same)
                                same.forEach {
                                    balls[it]?.visibility = View.GONE
                                }
                            }
                            resetBall()
                            return@addUpdateListener
                        }
                    }
                }
                start()
            }
        binding.lineView.drawTrace(null)
    }

    fun pauseGame() {
        ballMotionAnimator?.pause()
    }

    fun resumeGame() {
        ballMotionAnimator?.resume()
    }

    private fun findSame(current: Int, same: MutableList<Int>) {
        val x = current % cols
        val y = current /  cols

        searchIndices.forEach {
            val point = Point(x + it.x, y + it.y)
            val index = point.x + point.y * cols
            if (isValidIndex(point)) {
                val sameBall = balls[index]
                if (!same.contains(index)
                    && sameBall?.tag == ball?.tag
                    && sameBall?.visibility == View.VISIBLE
                ) {
                    same.add(index)
                    findSame(index, same)
                }
            }
        }
    }

    private fun isValidIndex(index: Point): Boolean {
        return index.x in 0 until cols && index.y in 0 until rows
    }

    private fun resetBall() {
        ballMotionAnimator?.cancel()
        ballMotionAnimator = null
        ball?.let {
            val color = colors.random(random)
            it.tag = color
            it.imageTintList = ColorStateList.valueOf(color)
            it.translationX = 0f
            it.translationY = 0f
        }
        isBallMoving = false
    }

    private fun onWin(coefficient: Float) {
        balance = (balance * coefficient).toInt()
        binding.balanceStatus.text = balance.toString()
        binding.status.text = binding.root.context.getString(R.string.you_win)
        binding.balanceStatus.visibility = View.VISIBLE
        binding.status.visibility = View.VISIBLE
        clearGame()
    }

    private fun onLose() {
        clearGame()
        binding.status.text = binding.root.context.getString(R.string.you_lose)
        binding.status.visibility = View.VISIBLE
    }

    private val View.rect: Rect
        get() {
            val point = IntArray(2)
            getLocationOnScreen(point)
            val (x, y) = point
            return Rect(x, y, x + width, y + height)
        }

}




