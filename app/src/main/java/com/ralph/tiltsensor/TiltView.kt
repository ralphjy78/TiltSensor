package com.ralph.tiltsensor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.SensorEvent
import android.view.View

class TiltView(context: Context?) : View(context) {

    private val greenPaint: Paint = Paint()
    private val blackPaint: Paint = Paint()
    private var cX: Float = 0f
    private var cY: Float = 0f

    private var xCoord: Float = 0f
    private var yCoord: Float = 0f

    init {
        greenPaint.color = Color.GREEN
        blackPaint.style = Paint.Style.STROKE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        cX = w / 2f
        cY = h / 2f

        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawCircle(cX, cY, 100f, blackPaint)            // 기준 원
        canvas?.drawCircle(cX + xCoord, cY + yCoord, 100f, greenPaint)            // 녹색 원
        canvas?.drawLine(cX - 20f, cY, cX + 20f, cY, blackPaint)    // 십자 가로
        canvas?.drawLine(cX, cY - 20f, cX, cY + 20f, blackPaint)    // 십자 세로

        super.onDraw(canvas)
    }

    fun onSensorEvent(event: SensorEvent) {
        yCoord = event.values[0] * 20
        xCoord = event.values[1] * 20

        this.invalidate()
    }

}