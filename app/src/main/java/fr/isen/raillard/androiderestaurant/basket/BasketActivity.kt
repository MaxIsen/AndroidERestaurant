package fr.isen.raillard.androiderestaurant.basket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.raillard.androiderestaurant.UserActivity
import fr.isen.raillard.androiderestaurant.databinding.ActivityBasketBinding

class BasketActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBasketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadList()

        binding.passercommande.setOnClickListener{
            startActivity(Intent(this, UserActivity::class.java))
        }
    }

    private fun loadList() {
        val basket = Basket.getBasket(this)
        val items = basket.items
        binding.recyclerViewBasket.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewBasket.adapter = BasketAdapter(items) {
            basket.removeDish(it)
            basket.save(this)
            loadList()
        }
    }
}