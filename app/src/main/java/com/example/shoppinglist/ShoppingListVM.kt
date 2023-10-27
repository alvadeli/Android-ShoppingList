package com.example.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.Item
import com.example.shoppinglist.data.ItemRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ShoppingListVM(private val itemRepository: ItemRepository) :
    ViewModel() {
    private val _items = itemRepository.getItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(ItemState())

    val state = combine(_state, _items) { state, items ->
        state.copy(items = items)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ItemState())

    fun onEvent(event: ItemEvent) {
        when (event) {

            ItemEvent.HideDialog -> {
                _state.update{ it.copy(
                    name = "",
                    quantity = "",
                    isAddingItem = false,
                    id = 0
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
                val id = state.value.id

                if(name.isBlank() || quantity.isBlank()){
                    return
                }

                val item = Item(
                    quantity = quantity,
                    name = name,
                    isDone = false,
                    id= id
                )

                viewModelScope.launch {
                    itemRepository.upsertItem(item)
                }

                _state.update {it.copy(
                    isAddingItem = false,
                    name = "",
                    quantity = "",
                    completed = false,
                    id = 0
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
                    itemRepository.upsertItem(event.item.copy(isDone = event.isCompleted))
                }
            }

            is ItemEvent.OnItemClick -> {
                viewModelScope.launch{
                    val item = itemRepository.getItemById(event.item.id)
                    _state.update {it.copy(
                        isAddingItem = true,
                        name = item?.name ?: "",
                        quantity = item?.quantity ?: "",
                        id = item?.id ?:0
                    )}

                }

            }
        }

    }
}

