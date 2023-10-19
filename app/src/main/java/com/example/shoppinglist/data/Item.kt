package com.example.shoppinglist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    val quantity: String,
    val name: String,
    val isDone: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int =0
)
