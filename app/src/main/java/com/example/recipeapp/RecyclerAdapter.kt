package com.example.recipeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.rv_item.view.*

class RecyclerAdapter (var Recipes:ArrayList<List<String>>): RecyclerView.Adapter<RecyclerAdapter.RecipeItem>(){

    class RecipeItem (itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.RecipeItem {
        return RecipeItem(LayoutInflater.from(parent.context).inflate(R.layout.rv_item,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.RecipeItem, position: Int) {
        val title= Recipes[position][0]
        val author= Recipes[position][1]
        val ingredents= Recipes[position][2]
        val instructions= Recipes[position][3]

        holder.itemView.apply {
            tvTitle.text= title
            tvAuthor.text=author
            tvIngredents.text= ingredents
            tvInstructions.text=instructions
        }
    }

    override fun getItemCount(): Int = Recipes.size
}