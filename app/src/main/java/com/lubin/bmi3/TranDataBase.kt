package com.lubin.bmi3

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Transactions::class), version = 1)
abstract class TranDataBase:RoomDatabase() {
    abstract fun transactionDao():TransactionDao
}