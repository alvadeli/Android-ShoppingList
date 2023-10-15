package com.example.shoppinglist

sealed interface ItemEvent {
    object SaveItem: ItemEvent
    data class SetQuantity(val quantity: String) : ItemEvent
    data class SetName(val name: String) : ItemEvent

    object ShowDialog: ItemEvent
    object HideDialog: ItemEvent

    data class DeleteItem(val item: Item) : ItemEvent
}