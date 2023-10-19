package com.example.shoppinglist.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shoppinglist.data.Item
import com.example.shoppinglist.data.ItemDao

@Database(
    entities = [Item::class],
    version = 2
)
abstract class ItemDatabase : RoomDatabase() {
    abstract val dao: ItemDao
}