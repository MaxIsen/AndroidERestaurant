package fr.isen.raillard.androiderestaurant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.isen.raillard.androiderestaurant.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

        }
    }
}