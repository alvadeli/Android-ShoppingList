package com.example.shoppinglist


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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppinglist.data.Item


@Composable
@Preview
fun ShoppingListScreenPreview() {

    var item1 = Item(
        quantity = "2",
        name = "Paprika",
        isDone = true
    )

    var item2 = Item(
        quantity = "3",
        name = "Onions",
        isDone = false
    )
    var items = listOf<Item>(item1,item2)

    val state = ItemState(
        items = items
    )

    ShoppingListScreen(
        state = state,
        onEvent = {}
    )
}

@Composable
fun ShoppingListScreen(
    state: ItemState,
    onEvent: (ItemEvent) -> Unit
//TODO Depency injection
){

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(ItemEvent.ShowDialog)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Item"
                )
            }
        },
        modifier = Modifier.padding(20.dp)
    ){ padding ->
        if (state.isAddingItem){
            AddItemDialog(state = state, onEvent = onEvent)
        }

        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(state.items)
            {item ->
                //TODO
                //ItemView(item = item, onEvent = viewModel::onEvent )


            }
        }
    }
}

