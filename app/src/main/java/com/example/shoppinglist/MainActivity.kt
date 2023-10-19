package com.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.shoppinglist.data.ItemDatabase
import com.example.shoppinglist.ui.theme.ShoppingListTheme

class MainActivity : ComponentActivity() {

    private val db by lazy{
        Room.databaseBuilder(
            applicationContext,
            ItemDatabase::class.java,
            "items.db"
        ).build()
    }

    private val viewModel by viewModels<ShoppingListViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun  <T: ViewModel?> create(modelClass: Class<T>): T {
                    return  ShoppingListViewModel(db.dao) as T
                }
            }
        }
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListTheme {
                val state by viewModel.state.collectAsState()
                ShoppingListScreen(state = state, onEvent = viewModel::onEvent)
            }
        }
    }
}

