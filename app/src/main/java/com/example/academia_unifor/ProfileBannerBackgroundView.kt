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
        color = Color.parseColor("#387EF8")
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

        // Uniform scaling based on width 360dp so circles remain perfectly circular (not oval)
        val scale = width / 360f
        canvas.scale(scale, scale)

        // ==========================================
        // 1. TOP-LEFT: Perfect Circle and Downward Triangle (beneath back button)
        // ==========================================
        // Circle (cx=62, cy=86, r=24) - fully circular, no oval distortion
        canvas.drawCircle(62f, 86f, 24f, paint)

        // Inverted triangle beneath the circle
        path.reset()
        path.moveTo(40f, 122f)
        path.lineTo(84f, 122f)
        path.lineTo(62f, 144f)
        path.close()
        canvas.drawPath(path, paint)

        // ==========================================
        // 2. TOP-RIGHT: Semicircle Arch, Baseline and Roof
        // ==========================================
        rectF.set(224f, 26f, 324f, 126f)
        canvas.drawArc(rectF, 180f, 180f, false, paint)
        line(canvas, 224f, 76f, 324f, 76f)

        path.reset()
        path.moveTo(224f, 26f)
        path.lineTo(256f, 8f)
        path.lineTo(292f, 8f)
        path.lineTo(324f, 26f)
        canvas.drawPath(path, paint)

        // ==========================================
        // 3. MIDDLE-LEFT: Two Clean Upward-pointing Triangles
        // ==========================================
        path.reset()
        path.moveTo(0f, 230f)
        path.lineTo(34f, 202f)
        path.lineTo(68f, 230f)
        path.close()
        canvas.drawPath(path, paint)

        path.reset()
        path.moveTo(0f, 268f)
        path.lineTo(34f, 240f)
        path.lineTo(68f, 268f)
        path.close()
        canvas.drawPath(path, paint)

        // ==========================================
        // 4. MIDDLE-RIGHT: Left-pointing Arrow Chevrons
        // ==========================================
        path.reset()
        path.moveTo(360f, 150f)
        path.lineTo(295f, 190f)
        path.lineTo(360f, 230f)
        canvas.drawPath(path, paint)

        path.reset()
        path.moveTo(360f, 220f)
        path.lineTo(315f, 250f)
        path.lineTo(360f, 280f)
        canvas.drawPath(path, paint)

        canvas.restore()
    }
}
