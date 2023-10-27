package com.example.shoppinglist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.shoppinglist.data.Item


@Composable
@Preview
fun AddItemViewPreview() {
    val item = Item(
        name = "Paprika",
        quantity = "2",
        isDone = false
    )

    ItemView(
        item = item,
        onEvent = {}
    )
}


@Composable
fun ItemView(
    item:Item,
    onEvent: (ItemEvent) -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){

        Text(
                text = createItemString(item),
                fontSize = 20.sp,
                modifier = Modifier.clickable{
                                        onEvent(ItemEvent.OnItemClick(item))}
                    .weight(1f)
                )
        Checkbox(
            checked =item.isDone ,
            onCheckedChange = {isChecked->
                onEvent(ItemEvent.OnDoneChange(item,isChecked))
            }
        )
    }
}

fun createItemString(item: Item): AnnotatedString
{
    val baseString = AnnotatedString ("${item.quantity} ${item.name}")
    if (!item.isDone) return baseString

    return  buildAnnotatedString {
        withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) {
            append(baseString)
        }
    }
}
