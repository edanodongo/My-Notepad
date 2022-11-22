package com.notes.mynotepad

import android.content.Context
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Language( var context: Context) {

    fun setLocale(lang: String) {
        if (lang.equals("", ignoreCase = true)) return
        val myLocale: Locale = Locale(lang)
        Locale.setDefault(myLocale)
        val resources: Resources = context.resources
        val config = resources.configuration
        config.locale = myLocale
        resources.updateConfiguration(
            config, resources.displayMetrics
        )
    }

    //Load language saved in shared preferences
    fun loadLocale(){
        val prefs = context.getSharedPreferences("setting", AppCompatActivity.MODE_PRIVATE)
        val language: String = prefs.getString("My_lang", "").toString()
        setLocale(language)
    }
}