package com.example.shoppinglist

import android.app.Application
import com.example.shoppinglist.di.AppModule
import com.example.shoppinglist.di.AppModuleImpl

class ShoppingListApp: Application()
{
    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }
}