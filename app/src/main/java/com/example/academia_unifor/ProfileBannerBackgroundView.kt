package com.example.academia_unifor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

/**
 * Geometric background decorations for the Profile top curved card matching mockup.
 */
class ProfileBannerBackgroundView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#387DF8")
        strokeWidth = 1.5f
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }

    private val path = Path()
    private val rectF = RectF()

    private fun line(canvas: Canvas, vararg points: Float) {
        path.reset()
        if (points.size >= 2) {
            path.moveTo(points[0], points[1])
            for (i in 2 until points.size step 2) {
                path.lineTo(points[i], points[i + 1])
            }
            canvas.drawPath(path, paint)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.save()

        // Normalize coordinate system to reference 360 x 305
        canvas.scale(width / 360f, height / 305f)

        // 1. TOP-LEFT: Circle and Downward Triangle
        canvas.drawCircle(62f, 75f, 28f, paint)
        line(canvas, 34f, 115f, 90f, 115f, 62f, 140f, 34f, 115f)

        // 2. TOP-RIGHT: Arch, Chevron and Line
        line(canvas, 222f, 22f, 252f, 0f, 292f, 0f, 322f, 22f, 222f, 22f)
        rectF.set(220f, 32f, 324f, 136f)
        canvas.drawArc(rectF, 180f, 180f, false, paint)
        line(canvas, 220f, 84f, 324f, 84f)

        // 3. MIDDLE-LEFT: Two Upward Triangles/Chevrons
        line(canvas, 0f, 240f, 22f, 240f, 62f, 270f, 0f, 270f)
        line(canvas, 0f, 215f, 18f, 202f, 62f, 238f, 18f, 238f)

        // 4. MIDDLE-RIGHT: Left-pointing Arrow Chevrons
        line(canvas, 360f, 155f, 290f, 200f, 360f, 245f)
        line(canvas, 360f, 178f, 322f, 200f, 360f, 222f)
        line(canvas, 360f, 255f, 315f, 280f, 360f, 305f)

        canvas.restore()
    }
}
