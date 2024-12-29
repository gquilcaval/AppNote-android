package com.example.note.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.giancarlosquilca.note.entity.Nota

@Dao
interface NotaDao {
    @Query("SELECT * FROM nota")
    fun getAll(): LiveData<List<Nota>>

  /*  @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>*/

    /*@Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User*/

    @Insert
    suspend fun insertAll(vararg nota: Nota)

    @Update
    suspend fun update(nota: Nota)


    @Query("DELETE FROM nota WHERE uid=:id")
    suspend fun delete(id: String)
}