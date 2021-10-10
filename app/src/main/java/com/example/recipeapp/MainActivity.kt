package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var btnSave:Button
    lateinit var btnView:Button
    lateinit var edTitle:EditText
    lateinit var edAuthor:EditText
    lateinit var edIngredents:EditText
    lateinit var edInstructions:EditText

    var title=""; var AuthorName=""; var Ingredents=""; var Instructions=""
    lateinit var recipeObject:myData.RecipeDetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSave=findViewById(R.id.btnSave)
        btnView=findViewById(R.id.btnView)
        edTitle=findViewById(R.id.eddTitle)
        edAuthor=findViewById(R.id.edAuthorName)
        edIngredents=findViewById(R.id.edIngredents)
        edInstructions=findViewById(R.id.edInstruction)

        btnSave.setOnClickListener(){
            title=edTitle.text.toString()
            AuthorName=edAuthor.text.toString()
            Ingredents=edIngredents.text.toString()
            Instructions=edInstructions.text.toString()

            recipeObject=myData.RecipeDetails(title,AuthorName,Ingredents,Instructions)
            postRecipe()
        }


        btnView.setOnClickListener(){
            intent= Intent(this,RecipesDisplay::class.java)
            startActivity(intent)
        }

    }//end onCreate()

    fun postRecipe(){
        val apiInterface=APIclint().getClient()?.create(APIinterface::class.java)
        apiInterface?.addRecipe(recipeObject)?.enqueue(object : Callback<myData.RecipeDetails?> {
            override fun onResponse(
                call: Call<myData.RecipeDetails?>,
                response: Response<myData.RecipeDetails?>
            ) {
                Toast.makeText(applicationContext,"Recipe added successfully ", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<myData.RecipeDetails?>, t: Throwable) {
                Toast.makeText(applicationContext,"Something went wrong!",Toast.LENGTH_LONG).show()
            }
        })
    }//end postRecipe()


}//end class