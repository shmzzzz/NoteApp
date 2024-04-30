package com.example.noteapp.repository

import com.example.noteapp.data.NoteDatabaseDao
import com.example.noteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDatabaseDao) {
    suspend fun addNote(note: Note) = noteDao.insert(note = note)
    suspend fun updateNote(note: Note) = noteDao.update(note = note)
    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note = note)
    suspend fun deleteAllNotes() = noteDao.deleteAll()
    suspend fun getAllNotes(): Flow<List<Note>> = noteDao.getAll().flowOn(Dispatchers.IO).conflate()
    suspend fun getNoteById(id: String) = noteDao.getNoteById(id = id)
}
