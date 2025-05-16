package com.ebc.customanimation

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

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




}