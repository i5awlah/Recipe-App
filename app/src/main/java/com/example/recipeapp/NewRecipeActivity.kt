package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.recipeapp.databinding.ActivityNewRecipeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewRecipeActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            addNewRecipe()
        }
    }

    private fun addNewRecipe() {
        val title = binding.etTitle.text.toString()
        val author = binding.etAuthor.text.toString()
        val ingredients = binding.etIngredients.text.toString()
        val instructions = binding.etInstructions.text.toString()
        val newRecipe = RecipeItem(0, title, author, ingredients, instructions)

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface?.addRecipe(newRecipe)?.enqueue(object: Callback<RecipeItem> {
            override fun onResponse(call: Call<RecipeItem>, response: Response<RecipeItem>) {
                Log.d("Main","Added successfully")
            }

            override fun onFailure(call: Call<RecipeItem>, t: Throwable) {
                Log.d("Main","Error: $t")
            }

        })

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}