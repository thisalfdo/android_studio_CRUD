package com.example.crud_application.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.crud_application.database.Note
import com.example.crud_application.repository.NoteRepository

class NoteViewModel(application: Application): ViewModel() {
    private val mNoteRepository : NoteRepository = NoteRepository(application)

    fun insert(note: Note){
        mNoteRepository.insert(note)
    }

    fun update(note: Note){
        mNoteRepository.update(note)
    }

    fun delete(note: Note){
        mNoteRepository.delete(note)
    }

    fun getAllNotes(): LiveData<List<Note>> = mNoteRepository.getAllNote()
}