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
        Log.i("Widget", "onCreate called")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        /* storing values in bundle - key is always a String*/
        savedInstanceState?.putInt("amount", value)
        /* check if app is reloaded or started for the first time */
        if (savedInstanceState != null) {
            value = savedInstanceState.getInt("amount", 0)
            binding.cupsAmount.text = value.toString()
            Log.i("Widget", "value from savedInstanceState = $value")
        }

        binding.adddingButton.setOnClickListener {
            increaseAmount()
            Log.i("Widget", "Button in App was clicked")
            Log.i("Widget", "increase() called, value = $value")
        }

        binding.resetButton.setOnClickListener {
            reset()
        }

        if (intent != null && intent.action == ADD_CUP) {
            increaseAmount()
            Log.i("Widget", "intent != 0")
            //val cupAmount = intent.getIntExtra(ONE_CUP, 0)
            //val sum = value + cupAmount
            //binding.cupsAmount.text = sum.toString()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("Widget", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Widget", "onResume called")
    }

    override fun onStop() {
        super.onStop()
    }

    /* FUNCTIONS FOR INCREASE & RESET */
    private fun increaseAmount() {
        value += 1
        binding.cupsAmount.text = value.toString()
        Toast.makeText(this, "Button clicked", Toast.LENGTH_SHORT).show()
        intent.action = null // BIG FIX --> removes the intent which is summoning the activity - works only when newly installed
    }

    private fun reset() {
        value = 0
        binding.cupsAmount.text = value.toString()
    }

}

