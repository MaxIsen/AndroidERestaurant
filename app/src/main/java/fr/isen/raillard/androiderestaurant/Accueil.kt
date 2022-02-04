package fr.isen.raillard.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.raillard.androiderestaurant.databinding.ActivityMainBinding

class Accueil : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setContentView(binding.root)

        binding.logout.setOnClickListener {
            finish()
            startActivity(Intent(applicationContext, LandingPageActivity::class.java))
        }
    }
}