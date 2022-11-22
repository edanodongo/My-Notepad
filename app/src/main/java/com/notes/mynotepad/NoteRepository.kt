package com.notes.mynotepad

import androidx.lifecycle.LiveData


class NoteRepository(private val notesDao: NotesDao) {

    val allNotes: LiveData<List<Note>> = notesDao.readData()

    fun insert(note: Note){
        notesDao.insert(note)
    }

    fun delete(note: Note){
        notesDao.delete(note)
    }

    fun update(note: Note){
        notesDao.update(note)
    }

    fun readData(): LiveData<List<Note>> {
        return notesDao.readData()
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Note>> {
        return notesDao.searchDatabase(searchQuery)
    }

}