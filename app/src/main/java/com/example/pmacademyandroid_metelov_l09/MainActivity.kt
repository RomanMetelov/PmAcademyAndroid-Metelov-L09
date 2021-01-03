package com.example.pmacademyandroid_metelov_l09

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.pmacademyandroid_metelov_l09.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tag, "onCreate")
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, MyFragment.newInstance())
                .commit()

            Log.d(tag, "convertButtonClicked")

            val stringValueUSD: String = binding.editTextDollars
                .text.toString()
            Log.d(tag, "stringValueUSD = $stringValueUSD")

            val intent = Intent(this, MyService::class.java)
            intent.putExtra("valueUSD", stringValueUSD)

            startService(intent)
        }
    }
}