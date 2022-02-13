package fr.isen.raillard.androiderestaurant

import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import fr.isen.raillard.androiderestaurant.databinding.ActivityRegisterBinding
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {


    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginactivity.setOnClickListener {
            finish()
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }

        binding.buttonRegister.setOnClickListener{
            if(formeValide()){
                val firstname = binding.nameCreateAccountInput.text.toString()
                val name = binding.surnameCreateAccountInput.text.toString()
                val address = binding.addressCreateAccountInput.text.toString()
                val email = binding.emailCreateAccountInput.text.toString()
                val password = binding.passwordCreateAccountInput.text.toString()
                val phone = binding.phoneCreateAccountInput.text.toString()

                //request post


                val text = "Incription réussie,\nVous pouvez dorénavant vous connecter"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()


                // Run volley

                    val url = "http://test.api.catering.bluecodegames.com/user/register\n"

                    // Post parameters
                    // Form fields and values
                    val params = HashMap<String, String>()
                    params["id_shop"] = "1"
                    params["lastname"] = name
                    params["address"] = address
                    params["email"] = email
                    params["password"] = password
                    params["firstname"] = firstname
                    params["phone"] = phone


                    val jsonObject = JSONObject(params as Map<*, *>?)

                    // Volley post request with parameters
                    val request = JsonObjectRequest(
                        Request.Method.POST, url, jsonObject,
                        { response ->
                            // Process the json
                            try
                            {
                                Log.d("Response:", "$response")
                            }
                            catch (e: Exception)
                            {
                                Log.d("Exception :", "$e")
                            }

                            Log.d("", "$response")
                        },
                        {
                            // Error in request
                            Log.d("Volley error:", "$it")
                        }
                    )


                    // Volley request policy, only one time request to avoid duplicate transaction
                    request.retryPolicy = DefaultRetryPolicy(
                        DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                        // 0 means no retry
                        0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
                        1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                    )
                    VolleySingleton.getInstance(this).addToRequestQueue(request)


                val text2 = "Redirection en cours"
                val toast2 = Toast.makeText(applicationContext, text2, duration)
                toast2.show()

                finish()
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }}

    // Permet de valider ou de refuser les entrées
    private fun formeValide(): Boolean {

        // Liste Erreurs
        val champNonRempli = "Champ vide"
        val passwordNotLongEnough = "Nombre de caracteres insuffisants"
        val mailinvalide = "Adresse email invalide"
        val telephoneinvalide = "Numéro de téléphone invalide"
        var error = true

        //Surname
        if (binding.surnameCreateAccountInput.text.toString().trim().isEmpty()) {
            binding.surnameCreateAccount.error = champNonRempli
            error = false
        } else {
            binding.surnameCreateAccount.error = null
        }

        //Name
        if (binding.nameCreateAccountInput.text.toString().trim().isEmpty()) {
            binding.nameCreateAccount.error = champNonRempli
            error = false
        } else {
            binding.nameCreateAccount.error = null
        }

        //Address
        if (binding.addressCreateAccountInput.text.toString().trim().isEmpty()) {
            binding.addressCreateAccount.error = champNonRempli
            error = false
        } else {
            binding.addressCreateAccount.error = null
        }

        // Email
        if (binding.emailCreateAccountInput.text.toString().trim().isEmpty()) {
            binding.emailCreateAccount.error = champNonRempli
            error = false
        } else {
            binding.emailCreateAccount.error = null
        }

        // MDP

        if (binding.emailCreateAccountInput.text.toString().trim().isEmpty()) {
            binding.emailCreateAccount.error = champNonRempli
            error = false
        } else {
            binding.emailCreateAccount.error = null
        }

        //si adresse email invalide
        if (""".+\@.+\..+""".toRegex().matches(binding.emailCreateAccountInput.text.toString())) {
            binding.emailCreateAccount.error = null
        } else {
            // Set error text
            binding.emailCreateAccount.error = mailinvalide
            error = false
        }

        // Not enough character in your password
        if (binding.passwordCreateAccountInput.text.toString().length < 8) {
            binding.passwordCreateAccount.error = passwordNotLongEnough
            error = false
        } else {
            binding.passwordCreateAccount.error = null
        }

        //Numero de telephone invalide
        if (binding.phoneCreateAccountInput.text.toString().trim().isEmpty()) {
            binding.phoneCreateAccount.error = champNonRempli
            error = false
        }

        if (binding.phoneCreateAccountInput.text.toString().length == 10) {
            binding.phoneCreateAccount.error = null
            val phonenumber = binding.phoneCreateAccountInput.text.toString()
            for (i in 0..9)
            {
                if (phonenumber[i] < '0' || phonenumber[i] > '9') {
                    binding.phoneCreateAccount.error = telephoneinvalide
                    error = false
                }
                else {
                    binding.phoneCreateAccount.error = null
                }
            }
        }
        else {
            binding.phoneCreateAccount.error = telephoneinvalide
            error = false
        }

        return error
    }
}