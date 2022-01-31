package fr.isen.raillard.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.Volley
import fr.isen.raillard.androiderestaurant.databinding.ActivityFoodBinding
import com.android.volley.Request
import fr.isen.raillard.androiderestaurant.model.FoodModel
import org.json.JSONObject
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.Gson
import fr.isen.raillard.androiderestaurant.model.FoodResult

class FoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryType = intent.getStringExtra("category_type") ?: ""
        binding.FoodTitle.text = categoryType

        loadDishesFromCategory(categoryType)
    }

    private fun loadDishesFromCategory(categoryType: String) {
        val url = "http://test.api.catering.bluecodegames.com/menu"

        val jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")
        val jsonRequest = JsonObjectRequest(
            Request.Method.POST, url, jsonObject, { response ->
                val foodResult = Gson().fromJson(response.toString(), FoodResult::class.java)
                displayFoods(foodResult.data.firstOrNull { category -> category.name_fr == categoryType}?.items ?: listOf() )

            }, {
                Log.e("FoodActivity", "erreur lors de la récupération de la liste des plats")
            })
        Volley.newRequestQueue(this).add(jsonRequest)
    }

    private fun displayFoods(foods: List<FoodModel>) {
        binding.foodList.layoutManager = LinearLayoutManager(this)

        binding.foodList.adapter = FoodAdapter(foods) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("food", it)
            startActivity(intent)
        }
    }
}