package com.example.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.Item
import com.example.shoppinglist.data.ItemDao
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ShoppingListViewModel(private val dao: ItemDao) :
    ViewModel() {
    private val _items = dao.getItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(ItemState())

    val state = combine(_state, _items) { state, items ->
        state.copy(items = items)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ItemState())

    fun onEvent(event: ItemEvent) {
        when (event) {

            ItemEvent.HideDialog -> {
                _state.update{ it.copy(
                    isAddingItem = false
                ) }
            }

            ItemEvent.ShowDialog -> {
                    _state.update { it.copy(
                    isAddingItem = true
                ) }
            }

            is ItemEvent.DeleteItem -> TODO()

            ItemEvent.SaveItem -> {
                val name =state.value.name
                val quantity = state.value.quantity

                if(name.isBlank() || quantity.isBlank()){
                    return
                }

                val item = Item(
                    quantity = quantity,
                    name = name,
                    isDone = false
                )

                viewModelScope.launch {
                    dao.upsertItem(item)
                }

                _state.update {it.copy(
                    isAddingItem = false,
                    name = "",
                    quantity = "",
                    completed = false
                )}


            }

            is ItemEvent.SetName -> {
                _state.update { it.copy(
                    name = event.name
                ) }
            }
            is ItemEvent.SetQuantity -> {
                _state.update { it.copy(
                    quantity = event.quantity
                ) }
            }

            is ItemEvent.OnDoneChange -> {
                viewModelScope.launch {
                    dao.upsertItem(event.item.copy(isDone = event.isCompleted))
                }
            }

            ItemEvent.OnTodoClick -> TODO()
        }

    }
}