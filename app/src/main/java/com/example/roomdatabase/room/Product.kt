package com.example.roomdatabase.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
class Product(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "energy_value") val energyValue: Int
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}