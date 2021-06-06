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
            Toast.makeText(this, "Button clicked", Toast.LENGTH_SHORT).show()
            Log.i("Widget", "Button in App was clicked")
            Log.i("Widget", "increase() called, value = $value")
        }

        binding.resetButton.setOnClickListener {
            reset()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("Widget", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Widget", "onResume called")

        if (intent != null && intent.hasCategory(ADD_CUP)) {
            increaseAmount()
            Log.i("Widget", "intent != 0")
            //val cupAmount = intent.getIntExtra(ONE_CUP, 0)
            //val sum = value + cupAmount
            //binding.cupsAmount.text = sum.toString()
        }
    }

    override fun onPause() {
        super.onPause()
        Log.i("Widget", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Widget", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Widget", "onDestroy called")
    }

    /* FUNCTIONS FOR INCREASE & RESET */
    private fun increaseAmount() {
        value += 1
        binding.cupsAmount.text = value.toString()
        intent.action = null // BIG FIX --> removes the intent which is summoning the activity.
        intent.removeCategory(ADD_CUP) // BIG FIX --> removes the intent which is summoning the activity.
    }

    private fun reset() {
        value = 0
        binding.cupsAmount.text = value.toString()
    }

    /* DECREASE COUNTER METHOD */
    fun decreaseCounter(inputValue: Int): Int {
        value = inputValue -1
        return value
    }

}

