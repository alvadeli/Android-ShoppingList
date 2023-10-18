package com.example.shoppinglist

data class ItemState(
    val items: List<Item> = emptyList(),
    val quantity: String = "",
    val name: String = "",
    val completed: Boolean = false,
    val isAddingItem: Boolean = false,
)

