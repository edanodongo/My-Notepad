package com.notes.mynotepad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.notes.mynotepadbeta10.R

class Privacy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()

    }
}