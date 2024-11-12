package com.android.persistence.backend

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update

@Entity
data class ElementoLista(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val nombre: String?,
    val comprado: String?
)

@Dao
interface ElementoListaDao {
    @Query("SELECT COUNT(*) FROM elementolista")
    fun count(): Int

    @Query("SELECT * FROM elementolista")
    fun getAll(): List<ElementoLista>

    @Query("SELECT * FROM elementolista WHERE id = :id")
    fun findById(id: Long): ElementoLista

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(elemento: ElementoLista): Long

    @Insert
    fun insertAll(vararg elementos: ElementoLista)

    @Update
    fun update(vararg elementos: ElementoLista)

    @Delete
    fun delete(elemento: ElementoLista)
}