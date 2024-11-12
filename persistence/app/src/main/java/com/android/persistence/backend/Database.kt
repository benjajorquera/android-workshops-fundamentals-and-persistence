package com.android.persistence.backend

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ElementoLista::class], version = 1, exportSchema = false)
abstract class ComprasDB : RoomDatabase() {
    abstract fun elementoListaDao(): ElementoListaDao

    companion object {
        @Volatile
        private var BASE_DATOS: ComprasDB? = null
        fun getInstance(contexto: Context): ComprasDB {
            return BASE_DATOS ?: synchronized(this) {
                Room.databaseBuilder(
                    contexto.applicationContext,
                    ComprasDB::class.java,
                    "Compras.db"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
                    .also { BASE_DATOS = it }
            }
        }
    }
}