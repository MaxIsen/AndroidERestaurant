package fr.isen.raillard.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.DefaultRetryPolicy
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.raillard.androiderestaurant.databinding.ActivityFoodBinding
import fr.isen.raillard.androiderestaurant.model.Food
import fr.isen.raillard.androiderestaurant.model.ItemsViewModel
import com.android.volley.Request
import com.android.volley.Response
import org.json.JSONObject

class FoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.FoodTitle.text = intent.getStringExtra("category_type")

        binding.foodList.layoutManager = LinearLayoutManager(this)

        val foods = listOf(
            Food("Risotto aux Champignons", R.drawable.risotto, price = "18€50"),
            Food("Pates Carbonara", R.drawable.pates_carbo, price = "15€50"),
            Food("Pizza du chef", R.drawable.pizza, price = "16€50"),
            Food("Pizza du chef", R.drawable.pizza, price = "16€50"),
            Food("Pizza du chef", R.drawable.pizza, price = "16€50"),
            Food("Pizza du chef", R.drawable.pizza, price = "16€50"),
            Food("Pizza du chef", R.drawable.pizza, price = "16€50"),
            Food("Pizza du chef", R.drawable.pizza, price = "16€50"),
            Food("Mozzarella frite", R.drawable.mozza, price = "7€50")
        )

        binding.foodList.adapter = FoodAdapter(foods) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("food", it)
            startActivity(intent)
        }




    }
}