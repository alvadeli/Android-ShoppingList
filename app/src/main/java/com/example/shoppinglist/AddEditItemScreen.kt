package com.example.shoppinglist

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
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
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(25.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    onEvent(ItemEvent.HideDialog)
                }) {
                    Text(text = "Cancel")
                }
                Button(
                    onClick = {
                    onEvent(ItemEvent.SaveItem)
                }) {
                    val text = if (state.id == 0) "Add" else "Update"
                    Text(text = text)
                }
            }

        }
    )
}