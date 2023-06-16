package com.droidbytes.custompagecurleffect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.droidbytes.custompagecurleffect.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.openBook.setOnClickListener {
            var intent= Intent(this@MainActivity,BookActivity::class.java)
            startActivity(intent)
        }

    }
}