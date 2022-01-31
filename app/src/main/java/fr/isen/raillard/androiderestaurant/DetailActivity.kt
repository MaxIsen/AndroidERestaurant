package fr.isen.raillard.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import fr.isen.raillard.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.raillard.androiderestaurant.model.Food

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        findViewById<TextView>(R.id.detailTitle).text = (intent.getSerializableExtra("food") as Food).name
        var binding = ActivityDetailBinding.inflate(layoutInflater)
        val url = "http://test.api.catering.bluecodegames.com/menu"
    }
}