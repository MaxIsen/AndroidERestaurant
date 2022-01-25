package fr.isen.raillard.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import fr.isen.raillard.androiderestaurant.databinding.ActivityHomeBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var bouton_Entrees = findViewById(R.id.Entrees) as Button
        var bouton_Plats = findViewById(R.id.Plats) as Button
        var bouton_Desserts = findViewById(R.id.Desserts) as Button

        /*
        Tous les toasters

        bouton_Desserts.setOnClickListener {
            // make a toast on button click event
            Toast.makeText(this, "You clicked on the desserts.", Toast.LENGTH_LONG).show()
        }
        bouton_Plats.setOnClickListener {
            // make a toast on button click event
            Toast.makeText(this, "You clicked on the main.", Toast.LENGTH_LONG).show()
        }
        bouton_Entrees.setOnClickListener {
            // make a toast on button click event
            Toast.makeText(this, "You clicked on the starters.", Toast.LENGTH_LONG).show()
        }*/


        //Bouton vers Desserts
        bouton_Desserts = findViewById(R.id.Desserts)                        //initialisation
        bouton_Desserts.setOnClickListener {                                 //clic sur le bouton
            ActivitySwapper(getString(R.string.home_desserts))
        }

        //Bouton vers Plats
        bouton_Plats = findViewById(R.id.Plats)                              //initialisation
        bouton_Plats.setOnClickListener {                                    //clic sur le bouton
            ActivitySwapper(getString(R.string.home_plats))
        }

        //Bouton vers Entrees
        bouton_Entrees = findViewById(R.id.Entrees)                          //initialisation
        bouton_Entrees.setOnClickListener {                                  //clic sur le bouton
            ActivitySwapper(getString(R.string.home_entrees))
        }

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.Entrees
    }

    private fun ActivitySwapper(category: String) {
        val changePage = Intent(this, plats::class.java)
        changePage.putExtra("category_type", category)
        startActivity(changePage)
    }
}