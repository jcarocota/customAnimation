package com.ebc.customanimation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class CustomGaugeView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null
): View(context, attr) {

    //Colores a emplear
    private val paintBackground = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.LTGRAY; strokeWidth = 20f
        style = Paint.Style.STROKE
    }

    private val paintTick = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.DKGRAY; strokeWidth = 4f
    }

    private val paintFill = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.CYAN; strokeWidth = 20f
        style = Paint.Style.STROKE
    }

    private val paintNeedle = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED; strokeWidth = 8f
    }

    private val sweepAngle = 0f
    private val needleAngle = 0f

    val currentNeedleAngle: Float
        get() = needleAngle


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Calculamos el centro y el radio del velocímetro
        val cx = width / 2f
        val cy = height / 2f + 100f
        val radius = min(width, height) / 2f - 100f

        //Dibujamos el fondo del velocímetro
        val oval = RectF(cx-radius, cy-radius, cx+radius, cy+radius)
        canvas.drawArc(oval, 180f, 180f, false, paintBackground)


        //Dibujar los ticks
        // 2) Ticks cada 10°
        for (i in 0..18) {
            val angle = 180f + i * 10f  // posición de cada marca
            val rad = Math.toRadians(angle.toDouble())

            /**
             * En un sistema de coordenadas cartesianas, un punto sobre el perímetro de un círculo de radio R y centro (cx, cy) está en:
             * x = cx + cos(rad) * R
             * y = cy + sin(rad) * R
             */

            // Punto inicial más cerca del centro
            val x1 = cx + cos(rad).toFloat() * (radius - 10)
            val y1 = cy + sin(rad).toFloat() * (radius - 10)

            // Punto final en el perímetro del óvalo
            val x2 = cx + cos(rad).toFloat() * radius
            val y2 = cy + sin(rad).toFloat() * radius
            canvas.drawLine(x1, y1, x2, y2, paintTick)
        }

        canvas.save()
        canvas.clipPath(Path().apply {
            addArc(oval, 180f, 180f)
        })
        canvas.drawArc(oval, 180f,sweepAngle, false, paintFill)
        canvas.restore()





    }


}