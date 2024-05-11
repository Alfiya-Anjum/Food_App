package com.example.food_app

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.content.Context

class RecipeAdapter(
    private val recipeList: List<Recipe>,
    private val context: Context  // Pass context to use in Intent
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageRecipe)
        val textViewName: TextView = itemView.findViewById(R.id.textRecipeName)
        val textViewDescription: TextView = itemView.findViewById(R.id.textRecipeDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentItem = recipeList[position]
        holder.textViewName.text = currentItem.name
        holder.textViewDescription.text = currentItem.description
        holder.imageView.setImageResource(currentItem.imageDrawableId)

        // Set the click listener to open a browser with the recipe URL
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(currentItem.recipeUrl))
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = recipeList.size
}
