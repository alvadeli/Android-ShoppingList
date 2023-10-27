package com.example.shoppinglist


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.*
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.data.Item
import com.example.shoppinglist.data.ItemRepositoryImpl


@Composable
fun ShoppingListScreen(
    state: ItemState,
    viewModel: ShoppingListVM
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Shopping List")
                },
                modifier = Modifier.fillMaxWidth()
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(ItemEvent.ShowDialog)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Item"
                )
            }
        },
    ){ padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            if (state.isAddingItem){
                AddItemDialog(state = state, onEvent = viewModel::onEvent)
            }

            LazyColumn(
                contentPadding = padding,
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(state.items)
                {item ->
                    ItemView(
                        item = item,
                        onEvent = viewModel::onEvent,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.onEvent(ItemEvent.OnItemClick(item))
                            }
                    )
                }
            }
        }
    }
}

