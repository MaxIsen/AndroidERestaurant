package fr.isen.raillard.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.raillard.androiderestaurant.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonRegister = binding.buttonRegister
        val buttonLogin = binding.buttonLogin
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        supportFragmentManager.beginTransaction().replace(R.id.fragment, LoginFragment()).commit()

        buttonRegister.setOnClickListener {
            fragmentTransaction.replace(R.id.fragment, RegisterFragment()).commit()
        }

        buttonLogin.setOnClickListener {
            fragmentTransaction.replace(R.id.fragment, LoginFragment()).commit()
        }
    }

}