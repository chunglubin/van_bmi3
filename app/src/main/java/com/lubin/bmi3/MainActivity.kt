package com.lubin.bmi3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.lubin.bmi3.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    companion object{
        private val TAG=MainActivity::class.java.simpleName
    }
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*binding.bHelp.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                TODO("Not yet implemented")
            }

        })*/
        /*binding.bHelp.setOnClickListener(){
            Log.d("MainActivity","I need your help!")
        }*/
        binding.bHelp.setOnClickListener{
            //Log.d("MainActivity","Help clicked")
            Log.d("MainActivity", "onCreate: help clicked")//logd
            Log.d(TAG, "Testing: ")
        }
    }

    fun bmi(view: View){
        println("hahaha!")
        var weight = binding.edWeight.text.toString().toFloat()
        var height = binding.edHeight.text.toString().toFloat()
        var bmi=weight/(height*height)//bmi=體重/身高平方cm^2
        Log.d("BMI", bmi.toString())
        Toast.makeText(this,"Your BMI is $bmi",Toast.LENGTH_LONG).show()
        //Log.d("MainActivity",bmi.toString())
        /*val builder = AlertDialog.Builder(this)
        builder.setTitle("Hello")
        builder.setMessage("Your BMI is $bmi")
        builder.setPositiveButton("OK",null)
        val dialog = builder.create()
        dialog.show()*/
        AlertDialog.Builder(this)
            .setTitle("Hello")
            .setMessage("Your BMI is $bmi")
            .setPositiveButton("OK") { dialog, which ->//dialog=d which=w
                binding.edWeight.setText("")
                binding.edHeight.setText("")
            }
        //.show()//show()展示
        binding.tvBmi.text="Your BMI is $bmi"
        //binding.edNumber7.
        val intent= Intent(this,ResultActivity::class.java)
        intent.putExtra("BMI",bmi)//傳遞資料
        startActivity(intent)//產生另一個畫面
    }
}