package com.android.example.enoughwater

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.example.enoughwater.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
private var value = 0
const val ADD_CUP = "ADD_CUP"
const val ONE_CUP = "ONE_CUP"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* storing values in bundle - key is always a String*/
        savedInstanceState?.putInt("amount", value)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.adddingButton.setOnClickListener {
            increaseAmount()
        }

        binding.resetButton.setOnClickListener{
            reset()
        }

        if (intent != null && intent.action == ADD_CUP) {
            val cupAmount = intent.getIntExtra(ONE_CUP, 0)
            val sum = value + cupAmount
            binding.cupsAmount.text = sum.toString()
        }

        if(savedInstanceState != null) {
            value = savedInstanceState.getInt("amount", 0)
        }

    }

    override fun onStart() {
        super.onStart()
        Log.i("Widget", "onStart started, value = $value")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Widget", "onResume started")
    }

    /* FUNCTIONS FOR INCREASE & RESET */
    fun increaseAmount () {
        Log.i("Widget", "Button in App was clicked")
        value += 1
        binding.cupsAmount.text = value.toString()
        Toast.makeText(this, "Button clicked", Toast.LENGTH_SHORT).show()
    }

    fun reset () {
        value = 0
        binding.cupsAmount.text = value.toString()
    }

}

