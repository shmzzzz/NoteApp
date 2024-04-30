package com.example.noteapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp.model.Note
import java.util.UUID

@Dao
interface NoteDatabaseDao {
    @Query("SELECT * FROM notes_table")
    fun getNotes(): List<Note>

    @Query("SELECT * FROM notes_table WHERE id = :id")
    fun getNoteById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(note: Note)

    @Query("DELETE FROM notes_table")
    fun deleteAll()

    @Delete
    fun deleteNote(note: Note)

}
