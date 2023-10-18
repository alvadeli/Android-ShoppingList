package com.example.shoppinglist


import android.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.*
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


//@Composable
//@Preview
//fun ShoppingListScreenPreview() {
//
//    var item1 = Item(
//        Quantity = "2",
//        Name = "Paprika",
//        Completed = true
//    )
//
//    var item2 = Item(
//        Quantity = "3",
//        Name = "Onions",
//        Completed = false
//    )
//    var items = listOf<Item>(item1,item2)
//
//    val state = ItemState(
//        items = items
//    )
//
//    ShoppingListScreen(
//        state = state,
//        onEvent = {}
//    )
//}

@Composable
fun ShoppingListScreen(
    state: ItemState,
    onEvent: (ItemEvent) -> Unit
){

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(ItemEvent.ShowDialog)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Item"
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
                Row(
                    modifier = Modifier.fillMaxWidth()
                ){
                    Column(modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "${item.Quantity} ${item.Name}",
                            fontSize = 20.sp,

                        )
                    }
                    //TODO Checkbox does not get updated
                    Checkbox(
                        checked =item.Completed ,
                        onCheckedChange = {
                            item.Completed = it
                            onEvent(ItemEvent.UpdateItem(item))
                        }
                    )


                }

            }
        }

    }
}

fun createItemString(item:Item): AnnotatedString
{
    val baseString = buildAnnotatedString { "${item.Quantity} ${item.Name}"}
    if (!item.Completed) return baseString

    return  buildAnnotatedString {
        withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) {
            append(baseString)
        }
    }
}
