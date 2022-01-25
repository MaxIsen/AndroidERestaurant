package fr.isen.raillard.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class plats : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plats)

        var type = intent.getStringExtra("category_type")
        findViewById<TextView>(R.id.Titreplats).text = type
    }
}