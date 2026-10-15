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
 * Geometric background decorations exactly matching the Academia UNIFOR mobile mockups.
 */
class RegistrationBackgroundView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#416592")
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

        // Normalize coordinate system to reference 360 x 780
        canvas.scale(width / 360f, height / 780f)

        // ==========================================
        // 1. TOP-LEFT: Circle and Downward Triangle
        // ==========================================
        // Circle (x=62, y=88, r=30)
        canvas.drawCircle(62f, 88f, 30f, paint)
        // Downward triangle right beneath the circle
        line(canvas, 32f, 132f, 92f, 132f, 62f, 160f, 32f, 132f)

        // ==========================================
        // 2. TOP-RIGHT: Arch, Chevron and Line
        // ==========================================
        // Chevron/cap at top-right
        line(canvas, 222f, 22f, 252f, 0f, 292f, 0f, 322f, 22f, 222f, 22f)
        // Semicircular dome arch
        rectF.set(220f, 32f, 324f, 136f)
        canvas.drawArc(rectF, 180f, 180f, false, paint)
        // Horizontal baseline beneath arch
        line(canvas, 220f, 84f, 324f, 84f)

        // ==========================================
        // 3. MIDDLE-LEFT: Two Upward Triangles/Chevrons
        // ==========================================
        // Lower triangle
        line(canvas, 0f, 290f, 24f, 290f, 68f, 322f, 0f, 322f)
        line(canvas, 0f, 290f, 14f, 290f, 0f, 300f)
        // Upper triangle
        line(canvas, 0f, 262f, 20f, 248f, 68f, 288f, 20f, 288f)

        // ==========================================
        // 4. MIDDLE-RIGHT: Left-pointing Arrow Chevrons
        // ==========================================
        // Large upper left-pointing arrow
        line(canvas, 360f, 155f, 285f, 205f, 360f, 255f)
        line(canvas, 360f, 180f, 320f, 205f, 360f, 230f)
        // Lower left-pointing chevron
        line(canvas, 360f, 275f, 310f, 305f, 360f, 335f)

        // ==========================================
        // 5. LOWER-CENTER: Vertical Double-Ended Arrow / Hourglass
        // ==========================================
        // Top arrow pointing UP
        line(canvas, 142f, 502f, 191f, 465f, 240f, 502f, 142f, 502f)
        // Curved waist brackets ( )
        rectF.set(150f, 492f, 232f, 572f)
        canvas.drawArc(rectF, 65f, 50f, false, paint)
        canvas.drawArc(rectF, 245f, 50f, false, paint)
        // Bottom arrow pointing DOWN
        line(canvas, 142f, 542f, 240f, 542f, 191f, 579f, 142f, 542f)

        // ==========================================
        // 6. BOTTOM-LEFT: Large Circle with Dual Horizontal Bars
        // ==========================================
        rectF.set(-60f, 560f, 120f, 740f)
        canvas.drawOval(rectF, paint)
        line(canvas, 0f, 642f, 120f, 642f)
        line(canvas, 0f, 664f, 120f, 664f)

        // ==========================================
        // 7. BOTTOM-RIGHT: Bowtie / Facing Triangles
        // ==========================================
        line(canvas, 312f, 546f, 344f, 590f, 344f, 546f, 360f, 560f)
        line(canvas, 312f, 546f, 312f, 644f, 344f, 600f, 344f, 644f, 360f, 630f)

        canvas.restore()
    }
}
