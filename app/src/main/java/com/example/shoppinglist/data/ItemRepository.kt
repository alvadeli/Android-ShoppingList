package com.example.shoppinglist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    suspend fun upsertItem(item: Item)

    suspend fun deleteItem(item: Item)

    suspend fun deleteCompletedItems()

    suspend fun getItemById(id:Int): Item?

    fun getItems(): Flow<List<Item>>


}