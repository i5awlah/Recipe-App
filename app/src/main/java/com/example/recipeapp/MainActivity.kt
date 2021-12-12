package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var recipeRecyclerView: RecyclerView
    lateinit var recipeAdapter: RecipeAdapter

    var apiInterface: APIInterface? = null
    var recipes = arrayListOf<RecipeItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        fetchData()
        binding.btnAddRecipe.setOnClickListener {
            openNewRecipeActivity()

        }
    }

    private fun setupRecyclerView() {
        recipeRecyclerView = binding.rvRecipe
        recipeAdapter = RecipeAdapter(recipes)
        recipeRecyclerView.adapter = recipeAdapter
        recipeRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchData() {
        apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface?.getRecipe()?.enqueue(object: Callback<Recipe>{
            override fun onResponse(call: Call<Recipe>, response: Response<Recipe>) {
                val recipeData = response.body()!!
                Log.d("Main","Data fetched correctly!")

                for (i in 0 until recipeData.size) {
                    recipes.add(recipeData[i])
                }
                recipeAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Recipe>, t: Throwable) {
                Log.d("Main","An error occurred while fetching data")
            }

        })
    }

    private fun openNewRecipeActivity() {
        val intent = Intent(this, NewRecipeActivity::class.java)
        startActivity(intent)
    }
}