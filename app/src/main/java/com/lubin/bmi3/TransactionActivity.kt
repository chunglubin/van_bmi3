package com.lubin.bmi3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.lubin.bmi3.databinding.ActivityMainBinding
import com.lubin.bmi3.databinding.ActivityTransactionBinding
import com.lubin.bmi3.databinding.RowTransactionBinding
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import kotlin.concurrent.thread

class TransactionActivity : AppCompatActivity() {
    lateinit var binding: ActivityTransactionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_transaction)
        thread{
            val json=URL("https://atm201605.appspot.com/h").readText()
            val gson= Gson()
            val transactions = gson.fromJson(json,Array<Transaction>::class.java).toList()
            transactions.forEach {
                println(it)
            }
            binding.idRecycler.adapter= object : RecyclerView.Adapter<TranViewHolder>() {
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranViewHolder {
                    val binding=RowTransactionBinding.inflate(layoutInflater,parent,false)
                    return TranViewHolder(binding)
                }

                override fun onBindViewHolder(holder: TranViewHolder, position: Int) {
                    val tran=transactions.get(position)
                    holder.amount.setText(tran.amount.toString())
                    holder.date.setText(tran.date)
                    holder.type.setText(tran.type.toString())
                }

                override fun getItemCount(): Int {
                    return transactions.size
                }//先做
            }
            /*runOnUiThread {
                Toast.makeText(this,"Testing",Toast.LENGTH_LONG).show()
            }*/
        /*val transaction=mutableListOf<Transaction>()
        val array=JSONArray(json)
        for(i in 0 until array.length()){
            val obj:JSONObject=array.getJSONObject(i)
            val amount=obj.getInt("amount")
            val account=obj.getString("account")
            val date=obj.getString("data")
            val type=obj.getInt("type")
            val tran=Transaction(account,date,amount,type)
            transaction.add(tran)
        }*/
        }
    }
    inner class TranViewHolder(val binding:RowTransactionBinding):
        RecyclerView.ViewHolder(binding.root) {
        val amount = binding.idTranAmount
        val date = binding.idTranDate
        val type = binding.idTranType
    }
}
class Transaction (val account:String,val date:String,val amount:Int,val type:Int){

}