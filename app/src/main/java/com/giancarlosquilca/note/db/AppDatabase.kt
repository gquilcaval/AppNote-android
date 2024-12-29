package com.giancarlosquilca.note.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.note.dao.NotaDao
import com.giancarlosquilca.note.entity.Nota

@Database(entities = arrayOf(Nota::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val notaDao: NotaDao
    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null
       fun getInstance(context: Context):AppDatabase{
           val tempInstance = INSTANCE
           if (tempInstance != null) {
               return tempInstance
           }
           synchronized(this) {
               val instance = Room.databaseBuilder(
                   context.applicationContext,
                   AppDatabase::class.java,
                   "CineDB"
               )
                   .build()
               INSTANCE = instance
               return instance
            }
        }
    }
}
