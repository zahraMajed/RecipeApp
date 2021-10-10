package com.example.recipeapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIinterface {

    @POST("/recipes/")
    fun addRecipe (@Body info:myData.RecipeDetails): Call<myData.RecipeDetails>

    @GET("/recipes")
    fun getDate (): Call<List<myData.RecipeDetails>>?

}