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
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpOffset
import androidx.compose.material.IconButton as IconButton1


@Composable
fun ShoppingListScreen(
    state: ItemState,
    viewModel: ShoppingListVM
){
    var expanded by rememberSaveable {
        mutableStateOf(false) }

    var anchor by remember {
        mutableStateOf(DpOffset.Zero) }

    var itemWidth by remember {
        mutableStateOf(0.dp) }

    var doneItemsVisible by rememberSaveable {
        mutableStateOf(true) }

    val density = LocalDensity.current

    val items = listOf("Delete done")
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Shopping List")
                },
                actions = {
                    IconButton1(onClick = {
                        expanded = true
                        anchor = DpOffset(itemWidth, 0.dp)
                    }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "")
                    }
                }
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
        modifier = Modifier.onSizeChanged {
            itemWidth = with(density){ it.width.toDp()}
        }
    ){ padding ->
        Box{
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                },
                offset = anchor,


            ) {
                DropdownMenuItem(onClick = {
                    viewModel.onEvent(ItemEvent.DeleteDoneItems)
                    expanded = false
                }) {
                    Text(text = "Delete done items")
                }
            }
        }


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

