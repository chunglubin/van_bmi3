package com.lubin.bmi3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.lubin.bmi3.databinding.ActivityMainBinding
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    val TAG=MainActivity::class.java.simpleName
    val REQUEST_DISPLAY_BMI=16
    lateinit var binding:ActivityMainBinding
    val fragments= mutableListOf<Fragment>()
    //lateinit var launcher: ActivityResultLauncher<Unit>
    var launcher=registerForActivityResult(NameContract()){name->
        Log.d(TAG,":$name")
        //Toast.makeText(this,name,Toast.LENGTH_LONG).show()
    }//註冊一個合約，以app compat為直接的父類別
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFragments()
        binding.bottomNaviBar.setOnItemReselectedListener {item->
            when(item.itemId){
                R.id.action_camera->{
                    supportFragmentManager.beginTransaction().run {
                        replace(R.id.id_container,fragments[0])
                    }
                    true
                }
                R.id.action_video_online->{
                    true
                }
                R.id.action_star->{
                    true
                }
                else -> true
            }
        }
        //insert database
        val t1=Transactions(1,"Lubin,","987654321",5000,1)
        val database=Room.databaseBuilder(this,
                            TranDataBase::class.java,"trans.db")
            .build()
        thread {
            database.transactionDao().insert(t1)
        }
        //Log.d(TAG, "onCreate: ")//1-->oncreate
        /*binding.bHelp.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                TODO("Not yet implemented")
            }

        })*/
        /*binding.bHelp.setOnClickListener(){
            Log.d("MainActivity","I need your help!")
        }*/
        /*binding..setOnClickListener{
            //Log.d("MainActivity","Help clicked")
            Log.d("MainActivity", "help clicked")//logd
            //Log.d(TAG, "Testing: ")
        }*/
        /*val launcher=registerForActivityResult(NameContract()){name->
            Toast.makeText(this,name,Toast.LENGTH_LONG).show()
        }//註冊一個合約，以app compat為直接的父類別*/
    }

    /*fun bmi(view: View){
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
        //val intent= Intent(this,ResultActivity::class.java)
        intent.putExtra("BMI",bmi)//傳遞資料
        //startActivity(intent)//產生另一個畫面
        //startActivityForResult(intent,REQUEST_DISPLAY_BMI)
        //registerForActivityResult(NameContract())-->activity註冊將要執行甚麼樣子的功能
        /*registerForActivityResult(NameContract()){name->
            Toast.makeText(this,name,Toast.LENGTH_LONG).show()
        }//註冊一個合約，以app compat為直接的父類別*/
        launcher.launch(bmi)
    }*/

    class NameContract:ActivityResultContract<Float,String>(){//合約
        override fun parseResult(resultCode: Int, intent: Intent?): String {
            if (resultCode == RESULT_OK) {
                val name = intent?.getStringExtra(Extras.NAME)
                return name!!
            } else {
                return "No name"
            }
        }
        override fun createIntent(context: Context, input: Float?): Intent {
            val intent = Intent(context, ResultActivity::class.java)
                .putExtra(Extras.BMI, input)
            return intent
        }
        /*override fun parseResult(resultCode: Int, intent: Intent?): String {
            if(resultCode== RESULT_OK){
                val name=intent?.getStringExtra("NAME")
                return name!!
            }else {
                return "No Name"
            }
        }*/
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, ": onActivityResult")
        if(requestCode == REQUEST_DISPLAY_BMI && resultCode == RESULT_OK){
            Log.d(TAG, "back from ResultActivity")
        }
    }

    private fun initFragments(){
        fragments.add(0,BlankFragment())
        val bmiFragment=BlankFragment()
        val transaction=supportFragmentManager.beginTransaction()
        transaction.add(R.id.id_container,bmiFragment)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
}