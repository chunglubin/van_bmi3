package com.lubin.bmi3

import androidx.room.*

@Dao
interface TransactionDao {
    @Query("select * from `Transactions` where id=:id")
    fun getById(id:Int):Transactions
    @Query("select * from `Transactions`ORDER BY createAt DESC")
    fun getAll():List<Transactions>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tran:Transactions)
    @Delete
    fun delete(tran: Transactions)
    @Update
    fun update(tran: Transactions)
}