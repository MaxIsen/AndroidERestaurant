package fr.isen.raillard.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.raillard.androiderestaurant.databinding.ActivityFoodBinding
import fr.isen.raillard.androiderestaurant.model.Food

class FoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.FoodTitle.text = intent.getStringExtra("category_type")

        binding.foodList.layoutManager = LinearLayoutManager(this)

        val foods = listOf(
            Food("Risotto aux Champignons", R.drawable.risotto, price = "16€50"),
            Food("Pates Carbonara", R.drawable.pates_carbo, price = "16€50"),
            Food("Pizza du chef", R.drawable.pizza, price = "16€50"),
            Food("Mozzarella frite", R.drawable.mozza, price = "16€50")
        )

        binding.foodList.adapter = FoodAdapter(foods) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("food", it)
            startActivity(intent)
        }
    }
}