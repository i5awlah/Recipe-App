package com.example.recipeapp

data class RecipeItem(
    val pk: Int,
    val title: String,
    val author: String,
    val ingredients: String,
    val instructions: String
)