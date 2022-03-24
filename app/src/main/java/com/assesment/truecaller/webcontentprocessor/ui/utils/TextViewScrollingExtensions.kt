package com.assesment.truecaller.webcontentprocessor.ui.utils

import android.text.method.ScrollingMovementMethod
import android.view.MotionEvent
import android.widget.TextView

object TextViewScrollingExtensions {
    fun TextView.makeTextViewScrollableInsideScrollView() {
        movementMethod = ScrollingMovementMethod()
        setOnTouchListener { v, event ->
            v.parent.requestDisallowInterceptTouchEvent(true)
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_UP -> {
                    v.parent.requestDisallowInterceptTouchEvent(false)
                    performClick()
                }
            }
            false
        }
    }
}