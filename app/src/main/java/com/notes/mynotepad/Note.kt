package com.notes.mynotepad

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
class Note(
    @ColumnInfo(name = "title") val noteTitle: String,
    @ColumnInfo(name = "description") val noteDescription: String,
    @ColumnInfo(name = "timestamp") val timeStamp: String,
    @ColumnInfo(name = "relativeBG") val relativeBG: Int,
    @ColumnInfo(name = "pinned") var notePinned: Boolean = false
)
{
    @PrimaryKey(autoGenerate = true)
    var id = 0
}