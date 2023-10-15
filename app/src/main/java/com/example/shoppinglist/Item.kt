package com.example.shoppinglist

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    val Quantity: String,
    val Name: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int =0
)
