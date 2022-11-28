package com.example.roomdatabase

import android.app.Application
import com.example.roomdatabase.room.ProductRepository
import com.example.roomdatabase.room.ProductRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class InjectedApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    private val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { ProductRoomDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { ProductRepository(database.productDao()) }
}