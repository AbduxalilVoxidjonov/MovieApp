package com.example.movieapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.databinding.ActivityAddMovieBinding
import com.example.movieapp.databinding.ActivityEditMovieBinding
import com.example.movieapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private  val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var array = mutableListOf<Malumot>()

        val customAdapter = MyCustomAdapter(array)
        binding.recycleView.adapter = customAdapter
        binding.recycleView.setHasFixedSize(true)

        customAdapter.setListener(object : RecycleViewEditRemove {
            override fun editItem(position: Int) {
                val dialogCustom = ActivityEditMovieBinding.inflate(layoutInflater,null,false)
                val custom = ActivityAddMovieBinding.inflate(layoutInflater,null,false)
                dialogCustom.nameMovie.setText(array[position].name)
                dialogCustom.aboutMovei.setText(array[position].about)
                dialogCustom.timeMovie.setText(array[position].time)

                val dialog = AlertDialog.Builder(this@MainActivity,0)
                    .setTitle("Edit Movie")
                    .setView(dialogCustom.root)
                    .create()
                dialogCustom.saveMovie.setOnClickListener{
                    val name = dialogCustom.nameMovie.text.toString()
                    val about = dialogCustom.aboutMovei.text.toString()
                    val time = dialogCustom.timeMovie.text.toString()
                    if (name.isEmpty() || about.isEmpty() || time.isEmpty()){
                        Toast.makeText(this@MainActivity,"Ma'lumotlar kiritilmadi",Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    val malumot = Malumot(name,about,time)
                    array[position]=malumot
                    customAdapter.notifyItemChanged(position)
                    dialog.dismiss()
                }
                dialog.show()
            }
            override fun removeItem(position: Int) {
                array.removeAt(position)
                customAdapter.notifyItemRemoved(position)
            }
        })

        binding.addMovie.setOnClickListener {
            val dialogCustom = ActivityAddMovieBinding.inflate(layoutInflater,null,false)

            val dialog = AlertDialog.Builder(this,0)
                .setTitle("Add Movie")
                .setView(dialogCustom.root)
                .create()
                    dialogCustom.saveMovie.setOnClickListener{
                        val name = dialogCustom.nameMovie.text.toString()
                        val about = dialogCustom.aboutMovei.text.toString()
                        val time = dialogCustom.timeMovie.text.toString()
                        if (name.isEmpty() || about.isEmpty() || time.isEmpty()){
                            Toast.makeText(this,"Ma'lumotlar kiritilmadi",Toast.LENGTH_SHORT).show()
                            return@setOnClickListener
                        }
                        val malumot = Malumot(name,about,time)
                        array.add(malumot)
                        customAdapter.notifyItemInserted(array.size-1)
                        dialog.dismiss()
                    }

            dialog.show()
        }
    }


}
