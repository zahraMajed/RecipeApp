package com.example.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recipes_display.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipesDisplay : AppCompatActivity() {
    var recipeList= arrayListOf<List<String>>()
    //var recipeList= arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes_display)

        rv_main.adapter=RecyclerAdapter(recipeList)
        rv_main.layoutManager= LinearLayoutManager(this)

        getRecipes()

    }//end onCreate()

    fun getRecipes(){
        val apiInterface=APIclint().getClient()?.create(APIinterface::class.java)
        apiInterface?.getDate()?.enqueue(object : Callback<List<myData.RecipeDetails>?> {
            override fun onResponse(call: Call<List<myData.RecipeDetails>?>, response: Response<List<myData.RecipeDetails>?>) {
                val response=response.body()
                for (dataItem in response!!){
                    recipeList.add(listOf("Recipe Title: ${dataItem.title}","Recipe Author: ${dataItem.author}",
                        "Recipe Ingredents: ${dataItem.ingredients}","Recipe Instructions: ${dataItem.instructions}"))
                }
                rv_main.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<myData.RecipeDetails>?>, t: Throwable) {
                Toast.makeText(applicationContext,"Something went wrong!", Toast.LENGTH_LONG).show()
            }
        })

    }

}//end class