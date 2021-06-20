package com.example.NoteApp.viewModels

import android.app.Application
import androidx.lifecycle.*
import com.example.NoteApp.db.AppDatabase
import com.example.NoteApp.entity.Nota
import kotlinx.coroutines.launch


class MainActivityVM(application: Application): AndroidViewModel(application) {

    private val db:AppDatabase = AppDatabase.getInstance(getApplication())


    internal val notas : LiveData<List<Nota>> = db.notaDao.getAll()

     fun getNotas(): LiveData<List<Nota>> {
        return notas
     }



    fun insertNota(nota: Nota){

        viewModelScope.launch {
            db.notaDao.insertAll(nota)
        }

    }
    fun updateNota(nota: Nota){

        viewModelScope.launch {
            db.notaDao.update(nota)
        }

    }
    fun deleteNota(id: String){

        viewModelScope.launch {
            db.notaDao.delete(id)
        }
    }

}