package com.lubin.bmi3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lubin.bmi3.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private val TAG=ResultActivity::class.java.simpleName
    lateinit var binding:ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showBMI()
        binding.idDone.setOnClickListener {
            val name = binding.idMyname.text.toString()
            val data = Intent()
            data.putExtra("NAME",name)
            setResult(RESULT_OK,data)//
            finish()
        }///in resulitActivity
    }

    private fun showBMI() {
        val bmi = intent.getFloatExtra("BMI_EXTRA", 0f)
        Log.d(TAG, "BMI: $bmi")
        binding.idBmiDisplay.text = bmi.toString()
    }
}