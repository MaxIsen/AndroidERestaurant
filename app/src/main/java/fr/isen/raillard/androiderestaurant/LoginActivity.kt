package fr.isen.raillard.androiderestaurant

import android.os.Bundle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import fr.isen.raillard.androiderestaurant.databinding.ActivityLoginBinding
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.tvRegister.setOnClickListener {
            finish()
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }

        binding.retour.setOnClickListener {
            startActivity(Intent(applicationContext, LandingPageActivity::class.java))
        }

    }
}