package com.notes.mynotepad

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("Update notesTable Set pinned = :pin Where id = :id")
    fun pinned(id: Int, pin: Boolean)

    @Query("Select * from notesTable order by id DESC")
    fun readData(): LiveData<List<Note>>//getAllNotes

    @Query("Select * from notesTable where title like :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<Note>>

}