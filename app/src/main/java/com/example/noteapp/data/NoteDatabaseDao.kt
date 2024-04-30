package com.example.noteapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp.model.Note

@Dao
interface NoteDatabaseDao {
    @Query("SELECT * FROM notes_table")
    fun getAll(): kotlinx.coroutines.flow.Flow<List<Note>>

    @Query("SELECT * FROM notes_table WHERE id = :id")
    suspend fun getNoteById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(note: Note)

}
