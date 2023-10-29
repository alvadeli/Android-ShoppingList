package com.example.shoppinglist.data

import androidx.room.*
import com.example.shoppinglist.data.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Upsert
    suspend fun upsertItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("SELECT * FROM item WHERE id = :id")
    suspend fun getItemById(id:Int): Item?

    @Query("SELECT * FROM item")
    fun getItems(): Flow<List<Item>>

    @Query("DELETE FROM item WHERE isDone = 1")
    suspend fun deleteCompletedItems()
}