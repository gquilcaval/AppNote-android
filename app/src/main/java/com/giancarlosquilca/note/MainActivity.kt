package com.giancarlosquilca.note



import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.giancarlosquilca.note.R
import com.giancarlosquilca.note.databinding.ActivityMainBinding
import com.giancarlosquilca.note.adapters.NotasAdapter
import com.giancarlosquilca.note.viewModels.MainActivityVM


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // Specify layout for recycler view
        val gridLayoutManager = GridLayoutManager(
            applicationContext,2, RecyclerView.VERTICAL,false)
        binding.recyclerView.layoutManager = gridLayoutManager

         // Get the view model
        val model: MainActivityVM by viewModels()
        model.getNotas().observe(this, Observer {

            binding.recyclerView.adapter = NotasAdapter(it)
        })


        //FLOATING BUTTON
        binding.fabRegister.setOnClickListener{


            startActivity(Intent(this, RegisterNoteActivity::class.java))

        }
    }
}