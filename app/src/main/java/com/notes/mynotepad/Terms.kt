package com.notes.mynotepad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.notes.mynotepadbeta10.R
import java.util.*

class Terms : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }
}