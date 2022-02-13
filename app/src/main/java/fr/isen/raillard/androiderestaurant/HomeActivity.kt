package fr.isen.raillard.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import fr.isen.raillard.androiderestaurant.databinding.ActivityHomeBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeBinding

    private fun activitySwapper(category: String) {
        val changePage = Intent(this, FoodActivity::class.java)
        changePage.putExtra("category_type", category)
        startActivity(changePage)
    }

    override fun onStop() {
        super.onStop()
        Log.d("HomeActivity", "Vous stoppez la page Home")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("HomeActivity", "Vous quittez la page Home")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.Entrees.setOnClickListener {
            activitySwapper(getString(R.string.home_entrees))
        }

        binding.Plats.setOnClickListener {
            activitySwapper(getString(R.string.home_plats))
        }

        binding.Desserts.setOnClickListener {
            activitySwapper(getString(R.string.home_desserts))
        }

        /*binding.connexion.setOnClickListener{
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }

        binding.inscription.setOnClickListener{
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }

        binding.profil.setOnClickListener{
            startActivity(Intent(applicationContext, Accueil::class.java))
        }*/
    }
}
