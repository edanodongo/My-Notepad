package com.notes.mynotepad

import androidx.cardview.widget.CardView

interface NoteClickListener {
    fun onDeleteIconClick(note: Note)
    fun onNoteClick(note: Note)
    fun onNoteLongClicked(note: Note, cardView: CardView)
}