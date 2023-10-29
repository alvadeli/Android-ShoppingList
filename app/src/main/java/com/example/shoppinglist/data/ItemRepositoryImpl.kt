package com.example.shoppinglist.data

import kotlinx.coroutines.flow.Flow

class ItemRepositoryImpl(
    private val dao:ItemDao
): ItemRepository {

    override suspend fun upsertItem(item: Item){
        dao.upsertItem(item)
    }

    override suspend fun deleteItem(item: Item) {
        dao.deleteItem(item)
    }

    override suspend fun deleteCompletedItems() {
        dao.deleteCompletedItems()
    }

    override suspend fun getItemById(id: Int): Item? {
        return dao.getItemById(id)
    }

    override fun getItems(): Flow<List<Item>> {
        return dao.getItems()
    }
}