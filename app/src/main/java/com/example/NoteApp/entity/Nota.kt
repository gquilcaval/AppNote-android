package com.example.NoteApp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Nota(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "descripcion") val descripcion: String?,
    @ColumnInfo(name = "fecha") val fecha: String,
    @ColumnInfo(name = "color") val color: String,
    @ColumnInfo(name = "idColor") val idColor: Int,

    )