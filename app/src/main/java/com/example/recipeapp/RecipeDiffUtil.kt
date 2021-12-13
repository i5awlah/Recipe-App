package com.example.recipeapp
import androidx.recyclerview.widget.DiffUtil

class RecipeDiffUtil(private val oldList: ArrayList<RecipeItem>, private val newList: ArrayList<RecipeItem>) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].pk == newList[newItemPosition].pk
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].pk != newList[newItemPosition].pk -> false
            oldList[oldItemPosition].title != newList[newItemPosition].title -> false
            oldList[oldItemPosition].author != newList[newItemPosition].author -> false
            oldList[oldItemPosition].ingredients != newList[newItemPosition].ingredients -> false
            oldList[oldItemPosition].instructions != newList[newItemPosition].instructions -> false
            else -> true
        }
    }

}