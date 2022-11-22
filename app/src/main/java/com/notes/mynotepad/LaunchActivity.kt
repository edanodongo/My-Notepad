package com.notes.mynotepad

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.notes.mynotepadbeta10.R


class LaunchActivity : AppCompatActivity() {

    lateinit var viewModel: NoteViewModel
    var int: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NoteViewModel::class.java)

        Handler().postDelayed({
            viewModel.allNotes.observe(this, Observer{ list->
                list?.let {
                    if (it.isEmpty()){
                        int = Intent(this, AddEditNoteActivity::class.java)
                    }else{
                        int = Intent(this, MainActivity::class.java)
                    }
                    startActivity(int)
                    finish()
                }
            })
        }, 1500)

    }

}