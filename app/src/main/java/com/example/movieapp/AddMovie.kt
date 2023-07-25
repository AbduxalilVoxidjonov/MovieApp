package com.example.movieapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.databinding.ActivityAddMovieBinding
import com.example.movieapp.databinding.ActivityMainBinding


class AddMovie : AppCompatActivity() {
    private  val binding by lazy{ ActivityAddMovieBinding.inflate(layoutInflater) }
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()
        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE)
        binding.saveMovie.setOnClickListener{
            val intentss = Intent(this,MainActivity::class.java)
            val name= binding.nameMovie.text.toString()
            val about =binding.aboutMovei.text.toString()
            val time =binding.timeMovie.text.toString()
            sharedPreferences.edit().putString("name",name).apply()
            sharedPreferences.edit().putString("about",about).apply()
            sharedPreferences.edit().putString("time",time).apply()
            binding.aboutMovei.text.clear()
            binding.nameMovie.text.clear()
            binding.timeMovie.text.clear()
            startActivity(intentss)
            finish()
        }
    }
}