package com.example.recipeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.databinding.RecipeRowBinding

class RecipeAdapter(private var recipes: ArrayList<RecipeItem>): RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {
    class RecipeViewHolder(val binding: RecipeRowBinding ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(RecipeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.binding.apply {
            // Each Card View should display the Title of the recipe and its Author
            tvTitle.text = recipe.title
            tvAuthor.text = recipe.author

            tvIngredients.text = recipe.ingredients
            tvInstructions.text = recipe.instructions

            // When users click on the Card View, they should be presented with the full Recipe (including Ingredients and Instructions)
            cvRecipe.setOnClickListener {
                llIngredientsAndInstructions.visibility = when (llIngredientsAndInstructions.visibility) {
                    View.VISIBLE -> View.GONE
                    else -> View.VISIBLE
                }
            }
        }
    }

    override fun getItemCount() = recipes.size

    fun setData(newRecipes: ArrayList<RecipeItem>) {
        val diffUtil = RecipeDiffUtil(recipes, newRecipes)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        recipes = newRecipes
        diffResult.dispatchUpdatesTo(this)

    }
}