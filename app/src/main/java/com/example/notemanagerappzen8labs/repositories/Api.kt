package com.example.notemanagerappzen8labs.repositories

import com.example.notemanagerappzen8labs.Model.NoteItem

interface Api {
    suspend fun login(username : String, password : String): Boolean
    suspend fun loadNotes(): List<NoteItem>
    suspend fun addNote(title : String, content : String)
    suspend fun editNote(id : Long, title: String, content: String)
    suspend fun deleteNote(dt : Long)
}