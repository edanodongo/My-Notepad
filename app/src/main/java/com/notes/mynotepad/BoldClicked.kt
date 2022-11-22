package com.notes.mynotepad

import android.graphics.Typeface
import android.text.Spannable
import android.text.style.StyleSpan

interface BoldClicked {
    fun onBoldClicked(){
        val boldSpan = StyleSpan(Typeface.BOLD)
        val start: Int = 0
        //val end: Int = getSelectionEnd()
        val flag = Spannable.SPAN_INCLUSIVE_INCLUSIVE
    }
}