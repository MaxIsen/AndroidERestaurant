package fr.isen.raillard.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import fr.isen.raillard.androiderestaurant.databinding.ActivityHomeBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
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


// TEst
        val textView = findViewById<TextView>(R.id.textView)

        binding.btn.setOnClickListener {
            val url = "http://test.api.catering.bluecodegames.com/menu"
            textView.text = ""

            // Post parameters
            // Form fields and values
            val params = HashMap<String,String>()
            params["id_shop"] = "1"

            val jsonObject = JSONObject(params as Map<*, *>)

            // Volley post request with parameters
            val request = JsonObjectRequest(Request.Method.POST,url,jsonObject,
                { response ->
                    // Process the json
                    try {
                        textView.text = "Response: $response"
                    }catch (e:Exception){
                        textView.text = "Exception: $e"
                    }

                }, {
                    // Error in request
                    textView.text = "Volley error: $it"
                })


            // Volley request policy, only one time request to avoid duplicate transaction
            request.retryPolicy = DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                // 0 means no retry
                0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
                1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )

            // Add the volley post request to the request queue
            VolleySingleton.getInstance(this).addToRequestQueue(request)
        }


        /*
        Lorsque nous avions plusieurs pages, je garde le code et les pages juste au cas où

        var bouton_Entrees = findViewById(R.id.Entrees) as Button

        var bouton_Plats = findViewById(R.id.Plats) as Button
        var bouton_Desserts = findViewById(R.id.Desserts) as Button


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
        }


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
        }*/
    }

}
