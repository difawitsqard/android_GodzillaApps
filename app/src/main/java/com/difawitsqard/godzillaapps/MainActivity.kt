package com.difawitsqard.godzillaapps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.difawitsqard.godzillaapps.adapter.SuperheroAdapter
import com.difawitsqard.godzillaapps.databinding.ActivityMainBinding
import com.difawitsqard.godzillaapps.model.Superhero
import com.difawitsqard.godzillaapps.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        getSuperheroData()
    }

    private fun getSuperheroData() {
        RetrofitInstance.api.getSuperhero().enqueue(object : Callback<Superhero> {
            override fun onResponse(call: Call<Superhero>, response: Response<Superhero>) {
                if (response.isSuccessful) {
                    val superhero = response.body()
                    superhero?.let {
                        binding.recyclerView.adapter = SuperheroAdapter(listOf(it))
                    }
                }
            }

            override fun onFailure(call: Call<Superhero>, t: Throwable) {
                // Handle failure
            }
        })
    }
}