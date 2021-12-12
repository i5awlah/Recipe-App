package com.example.recipeapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {
    @GET ("recipes/")
    fun getRecipe(): Call<Recipe>
    @POST("recipes/")
    fun addRecipe(@Body recipeItem: RecipeItem): Call<RecipeItem>
}