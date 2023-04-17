package com.example.nashkodimtrainee.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nashkodimtrainee.R
import com.example.nashkodimtrainee.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val fragment = CryptoLabelFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.navigation_view, fragment)
                .commit()
        }
    }
}