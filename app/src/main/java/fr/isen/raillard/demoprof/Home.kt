package fr.isen.raillard.demoprof

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var bouton_Entrees = findViewById(R.id.Entrees) as Button

        bouton_Entrees.setOnClickListener {
            // make a toast on button click event
            Toast.makeText(this, "You clicked on the starters.", Toast.LENGTH_LONG).show()
        }

        var bouton_Plats = findViewById(R.id.Plats) as Button

        bouton_Plats.setOnClickListener {
            // make a toast on button click event
            Toast.makeText(this, "You clicked on the main.", Toast.LENGTH_LONG).show()
        }

        var bouton_Desserts = findViewById(R.id.Desserts) as Button

        bouton_Desserts.setOnClickListener {
            // make a toast on button click event
            Toast.makeText(this, "You clicked on the desserts.", Toast.LENGTH_LONG).show()
        }


        //Bouton vers Desserts
//initialisation
        bouton_Desserts = findViewById(R.id.Desserts)

        // creation de notre intent
        val IntentDesserts : Intent = Intent(this,desserts::class.java)

        //clic sur le bouton
        bouton_Desserts.setOnClickListener {
            startActivity(IntentDesserts)
        }

        //Bouton vers Plats
//initialisation
        bouton_Plats = findViewById(R.id.Plats)

        // creation de notre intent
        val IntentPlats : Intent = Intent(this,plats::class.java)

        //clic sur le bouton
        bouton_Plats.setOnClickListener {
            startActivity(IntentPlats)
        }

        //Bouton vers Entrees
//initialisation
        bouton_Entrees = findViewById(R.id.Entrees)

        // creation de notre intent
        val IntentEntrees : Intent = Intent(this,Entrees::class.java)

        //clic sur le bouton
        bouton_Entrees.setOnClickListener {
            startActivity(IntentEntrees)
        }
    }
}