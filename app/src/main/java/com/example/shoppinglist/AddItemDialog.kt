package com.example.shoppinglist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
@Preview
fun AddContactItemPreview() {
    val state = ItemState(
        name = "Paprika",
        quantity = "2",
    )

    AddItemDialog(
        state = state,
        onEvent = {}
    )
}

@Composable
fun AddItemDialog(
    state: ItemState,
    onEvent: (ItemEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEvent(ItemEvent.HideDialog)
        },
        title = { Text(text = "Add Item") },
        text = {
            Column (
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.name,
                    onValueChange = {
                        onEvent(ItemEvent.SetName(it))
                    },
                    placeholder = {
                        Text(text = "Item")
                    }
                )

                TextField(
                    value = state.quantity,
                    onValueChange = {
                        onEvent(ItemEvent.SetQuantity(it))
                    },
                    placeholder = {
                        Text(text = "Quantity")
                    }
                )

            }
        },
        buttons = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ){
                Button(onClick = {
                    onEvent(ItemEvent.SaveItem)
                }){
                    Text(text = "Add")
                }
            }
        }
    )
}