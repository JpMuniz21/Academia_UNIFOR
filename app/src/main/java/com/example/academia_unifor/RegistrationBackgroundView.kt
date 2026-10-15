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
 * Geometric background decorations exactly matching the original Academia UNIFOR design.
 */
class RegistrationBackgroundView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#426593")
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

        // Uniform scaling based on reference width 360dp to keep perfect circles and proportions
        val scale = width / 360f
        canvas.scale(scale, scale)

        // ==========================================
        // 1. TOP-LEFT: Perfect Circle and Downward Triangle (below back button)
        // ==========================================
        // Circle (cx=62, cy=74, r=26)
        canvas.drawCircle(62f, 74f, 26f, paint)

        // Clean inverted triangle directly below the circle
        path.reset()
        path.moveTo(38f, 114f)
        path.lineTo(86f, 114f)
        path.lineTo(62f, 138f)
        path.close()
        canvas.drawPath(path, paint)

        // ==========================================
        // 2. TOP-RIGHT: Semicircle Arch, Baseline and Roof
        // ==========================================
        // Semicircular dome arch
        rectF.set(224f, 26f, 324f, 126f)
        canvas.drawArc(rectF, 180f, 180f, false, paint)
        // Horizontal baseline of the arch
        line(canvas, 224f, 76f, 324f, 76f)
        // Roof chevron above the dome
        path.reset()
        path.moveTo(224f, 26f)
        path.lineTo(256f, 8f)
        path.lineTo(292f, 8f)
        path.lineTo(324f, 26f)
        canvas.drawPath(path, paint)

        // ==========================================
        // 3. MIDDLE-LEFT: Two Clean Upward-pointing Triangles
        // ==========================================
        // Upper triangle pointing UP
        path.reset()
        path.moveTo(0f, 248f)
        path.lineTo(36f, 216f)
        path.lineTo(72f, 248f)
        path.close()
        canvas.drawPath(path, paint)

        // Lower triangle pointing UP directly beneath it
        path.reset()
        path.moveTo(0f, 290f)
        path.lineTo(36f, 258f)
        path.lineTo(72f, 290f)
        path.close()
        canvas.drawPath(path, paint)

        // ==========================================
        // 4. MIDDLE-RIGHT: Left-pointing Arrow Chevrons
        // ==========================================
        // Upper left-pointing arrow
        path.reset()
        path.moveTo(360f, 160f)
        path.lineTo(290f, 205f)
        path.lineTo(360f, 250f)
        canvas.drawPath(path, paint)

        // Lower left-pointing chevron
        path.reset()
        path.moveTo(360f, 240f)
        path.lineTo(315f, 270f)
        path.lineTo(360f, 300f)
        canvas.drawPath(path, paint)

        // ==========================================
        // 5. LOWER-CENTER: Vertical Double-Ended Arrow (Hourglass without crossing circle)
        // ==========================================
        val cx = 180f
        val topApexY = 445f
        val topBaseY = 485f
        val halfBaseW = 40f
        val bottomBaseY = 545f
        val bottomApexY = 585f

        // Top triangle (pointing UP)
        path.reset()
        path.moveTo(cx, topApexY)
        path.lineTo(cx + halfBaseW, topBaseY)
        path.lineTo(cx - halfBaseW, topBaseY)
        path.close()
        canvas.drawPath(path, paint)

        // Bottom triangle (pointing DOWN)
        path.reset()
        path.moveTo(cx - halfBaseW, bottomBaseY)
        path.lineTo(cx + halfBaseW, bottomBaseY)
        path.lineTo(cx, bottomApexY)
        path.close()
        canvas.drawPath(path, paint)

        // Smooth curved waist brackets connecting the two triangle bases
        // Left curve (
        path.reset()
        path.moveTo(cx - halfBaseW, topBaseY)
        path.quadTo(cx - halfBaseW - 14f, (topBaseY + bottomBaseY) / 2f, cx - halfBaseW, bottomBaseY)
        canvas.drawPath(path, paint)

        // Right curve )
        path.reset()
        path.moveTo(cx + halfBaseW, topBaseY)
        path.quadTo(cx + halfBaseW + 14f, (topBaseY + bottomBaseY) / 2f, cx + halfBaseW, bottomBaseY)
        canvas.drawPath(path, paint)

        // ==========================================
        // 6. BOTTOM-LEFT: Large Circle with Single Horizontal Line
        // ==========================================
        canvas.drawCircle(45f, 680f, 62f, paint)
        line(canvas, 0f, 680f, 107f, 680f)

        // ==========================================
        // 7. BOTTOM-RIGHT: Opposing Triangles (Bowtie)
        // ==========================================
        path.reset()
        path.moveTo(310f, 550f)
        path.lineTo(345f, 585f)
        path.lineTo(310f, 620f)
        path.close()
        canvas.drawPath(path, paint)

        path.reset()
        path.moveTo(360f, 550f)
        path.lineTo(325f, 585f)
        path.lineTo(360f, 620f)
        path.close()
        canvas.drawPath(path, paint)

        canvas.restore()
    }
}
