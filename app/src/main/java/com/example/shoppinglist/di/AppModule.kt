package com.example.shoppinglist.di

import android.content.Context
import androidx.room.Room
import com.example.shoppinglist.data.ItemDatabase
import com.example.shoppinglist.data.ItemRepository
import com.example.shoppinglist.data.ItemRepositoryImpl

interface AppModule {
    val itemDatabase: ItemDatabase
    val itemRepository: ItemRepository
}


class AppModuleImpl (
    private val appContext: Context,

): AppModule {
    override val itemDatabase: ItemDatabase by lazy {
        Room.databaseBuilder(
            appContext,
            ItemDatabase::class.java,
            "item_db"
        ).build()
    }

    override val itemRepository: ItemRepository by lazy {
        ItemRepositoryImpl(itemDatabase.dao)
    }
}