package fr.isen.raillard.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import fr.isen.raillard.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.raillard.androiderestaurant.model.Food
import fr.isen.raillard.androiderestaurant.model.FoodModel

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        findViewById<TextView>(R.id.detailTitle).text = (intent.getSerializableExtra("food") as FoodModel).name_fr
    }
}