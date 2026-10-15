package com.example.academia_unifor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * Geometric background decorations for the Profile top curved card.
 */
class ProfileBannerBackgroundView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#2E6CE3")
        strokeWidth = 1.0f
        style = Paint.Style.STROKE
    }

    private fun line(canvas: Canvas, vararg points: Float) {
        val path = Path().apply {
            moveTo(points[0], points[1])
            for (i in 2 until points.size step 2) lineTo(points[i], points[i + 1])
        }
        canvas.drawPath(path, paint)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.save()
        canvas.scale(width / 350f, height / 305f)

        // Top left: circle and downward triangle
        canvas.drawCircle(58f, 54f, 26f, paint)
        line(canvas, 32f, 96f, 84f, 96f, 58f, 118f, 32f, 96f)

        // Top right: arc, chevron and horizontal line
        line(canvas, 223f, 19f, 250f, 0f, 289f, 0f, 315f, 19f, 223f, 19f)
        canvas.drawArc(222f, 31f, 316f, 117f, 180f, 180f, false, paint)
        line(canvas, 222f, 74f, 316f, 74f)

        // Middle left: triangles/chevrons
        line(canvas, 0f, 240f, 19f, 227f, 69f, 264f, 25f, 264f, 69f, 298f, 0f, 298f)

        // Middle right: left-pointing arrows
        line(canvas, 350f, 160f, 315f, 185f, 350f, 210f)
        line(canvas, 350f, 230f, 320f, 255f, 350f, 280f)

        canvas.restore()
    }
}
