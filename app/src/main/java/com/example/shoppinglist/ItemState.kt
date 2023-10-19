package com.example.shoppinglist

import com.example.shoppinglist.data.Item

data class ItemState(
    val items: List<Item> = emptyList(),
    val quantity: String = "",
    val name: String = "",
    val completed: Boolean = false,
    val isAddingItem: Boolean = false,
)

