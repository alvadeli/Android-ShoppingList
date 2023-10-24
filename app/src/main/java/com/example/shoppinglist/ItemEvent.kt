package com.example.shoppinglist

import com.example.shoppinglist.data.Item

sealed interface ItemEvent {
    object SaveItem: ItemEvent
    data class SetQuantity(val quantity: String) : ItemEvent
    data class SetName(val name: String) : ItemEvent
    data class OnDoneChange(val item: Item, val isCompleted: Boolean) : ItemEvent
    data class OnItemClick(val item: Item): ItemEvent
    object ShowDialog: ItemEvent
    object HideDialog: ItemEvent

    data class DeleteItem(val item: Item) : ItemEvent
}