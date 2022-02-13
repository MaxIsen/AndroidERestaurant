package fr.isen.raillard.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.raillard.androiderestaurant.databinding.ActivityLandingPageBinding

class LandingPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLandingPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLandingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener{
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }

        binding.register.setOnClickListener{
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }

        binding.menu.setOnClickListener{
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
    }
}