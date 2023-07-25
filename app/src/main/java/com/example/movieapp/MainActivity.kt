package com.example.movieapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.databinding.ActivityAddMovieBinding
import com.example.movieapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private  val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var array = mutableListOf<Malumot>()
        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "")
        val about = sharedPreferences.getString("about", "")
        val time = sharedPreferences.getString("time", "")
        sharedPreferences.edit().clear().apply()
        array.add(Malumot(name.toString(),about.toString(), time.toString()))

        val customAdapter = MyCustomAdapter(array)
        binding.recycleView.adapter = customAdapter
        binding.recycleView.setHasFixedSize(true)

        customAdapter.setListener(object : RecycleViewEditRemove {
            override fun editItem(position: Int) {

            }
            override fun removeItem(position: Int) {
                array.removeAt(position)
                customAdapter.notifyItemRemoved(position)
            }
        })

        binding.addMovie.setOnClickListener {
            val intents = Intent(this, AddMovie::class.java)
            startActivity(intents)
        }
    }


}