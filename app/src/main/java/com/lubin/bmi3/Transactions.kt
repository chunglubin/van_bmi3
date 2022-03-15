package com.lubin.bmi3

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Transactions(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val account:String,
    @ColumnInfo(name="createAt")
    val date:String,
    @ColumnInfo(name="amount")
    val amount:Int,
    val type:Int
    ) {
}