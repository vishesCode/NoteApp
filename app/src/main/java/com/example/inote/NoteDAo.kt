package com.example.inote

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.*

@Dao
interface NoteDAo {

    @Insert(onConflict=OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)
    @Delete
    suspend fun delete(note: Note)
    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

}