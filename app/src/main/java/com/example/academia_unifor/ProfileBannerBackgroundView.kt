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
        color = Color.parseColor("#3874E8")
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
        canvas.scale(width / 350f, height / 300f)

        // Top right arc and horizontal lines
        canvas.drawArc(222f, 15f, 316f, 105f, 180f, 180f, false, paint)
        line(canvas, 222f, 60f, 316f, 60f)

        // Top left circle and triangle
        canvas.drawCircle(65f, 48f, 28f, paint)
        line(canvas, 37f, 95f, 93f, 95f, 65f, 120f, 37f, 95f)

        // Left chevrons
        line(canvas, 0f, 220f, 22f, 205f, 60f, 240f, 20f, 240f, 60f, 275f, 0f, 275f)

        // Right chevrons / arrows
        line(canvas, 350f, 130f, 315f, 150f, 350f, 170f)
        line(canvas, 350f, 180f, 325f, 200f, 350f, 220f)

        canvas.restore()
    }
}
