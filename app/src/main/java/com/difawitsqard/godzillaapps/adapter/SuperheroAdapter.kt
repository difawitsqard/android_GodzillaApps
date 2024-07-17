package com.difawitsqard.godzillaapps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.difawitsqard.godzillaapps.databinding.ItemSuperheroBinding
import com.difawitsqard.godzillaapps.model.Superhero

class SuperheroAdapter(private val superheroes: List<Superhero>) :
    RecyclerView.Adapter<SuperheroAdapter.SuperheroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        val binding = ItemSuperheroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuperheroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        val superhero = superheroes[position]
        holder.bind(superhero)
    }

    override fun getItemCount(): Int = superheroes.size

    class SuperheroViewHolder(private val binding: ItemSuperheroBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(superhero: Superhero) {
            binding.superheroName.text = superhero.name
            binding.FullName.text = superhero.biography.fullName
            binding.FirstAppearance.text = superhero.biography.firstAppearance
            binding.Alignment.text = superhero.biography.alignment

            val superheroAliases = buildString {
                superhero.biography.aliases.forEach { append("$it\n")
                }
            }
            binding.superheroAlias.text = superheroAliases.trimEnd();

            // Create a string key:val
            val powerStatsString = buildString {
                superhero.powerstats::class.java.declaredFields.forEach { field ->
                    field.isAccessible = true
                    append("${field.name}: ${field.get(superhero.powerstats)}%\n")
                }
            }
            binding.superheroPowerStats.text = powerStatsString.trimEnd()

            Glide.with(binding.root.context)
                .load(superhero.image.url)
                .circleCrop()
                .into(binding.superheroImage)
        }
    }
}