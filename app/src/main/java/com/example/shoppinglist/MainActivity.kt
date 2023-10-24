package com.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.shoppinglist.data.ItemDatabase
import com.example.shoppinglist.presentation.viewModelFactory
import com.example.shoppinglist.ui.theme.ShoppingListTheme

class MainActivity : ComponentActivity() {

    private val db by lazy{
        Room.databaseBuilder(
            applicationContext,
            ItemDatabase::class.java,
            "items.db"
        ).build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListTheme {

                val viewModel = viewModel<ShoppingListViewModel>(
                    factory = viewModelFactory {
                        ShoppingListViewModel(ShoppingListApp.appModule.itemRepository)
                    }
                )

                val state by viewModel.state.collectAsState()
                ShoppingListScreen(state = state,viewModel = viewModel )
            }
        }
    }
}

