package com.notes.mynotepad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.notes.mynotepadbeta10.R

class OCRActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ocr)

        //OCR text recognition guide
        //https://www.pdftron.com/blog/android/build-a-document-scanner-with-ocr/
        //https://developers.google.com/ml-kit/vision/text-recognition/android#before-you-begin
    }
}