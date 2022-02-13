package fr.isen.raillard.androiderestaurant

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import fr.isen.raillard.androiderestaurant.basket.BasketActivity
import fr.isen.raillard.androiderestaurant.databinding.ActivityLoginBinding
import fr.isen.raillard.androiderestaurant.model.RegisterModel
import org.json.JSONObject

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


    /*private fun Login() {
        val params = HashMap<String, String>()
        params["id_shop"]="1"
        params["email"] = binding.adressemail.text.toString()
        params["password"] = binding.motdepasse.text.toString()
        var errorBool: Boolean
        errorBool = true
        if (TextUtils.isEmpty(binding.adressemail.text)){
            binding.adressemail.error = "Champ vide"
            errorBool = false
        }
        else binding.adressemail.error = null
        if (TextUtils.isEmpty(binding.motdepasse.text)){
            binding.password.error = "Saisie incorrecte"
            errorBool = false
        }
        else binding.password.error = null
        if (errorBool) {
            val queue = Volley.newRequestQueue(requireContext())
            val url = "http://test.api.catering.bluecodegames.com/user/login"
            val jsonObject = JSONObject(params as HashMap<*, *>)
            val request = JsonObjectRequest(
                Request.Method.POST, url, jsonObject,
                { response ->
                    val register =
                        GsonBuilder().create()
                            .fromJson(response.toString(), RegisterModel::class.java)
                    val editor =
                        requireContext().getSharedPreferences(
                            DetailActivity.APP_PREFS,
                            Context.MODE_PRIVATE
                        ).edit()
                    editor.putString(USER_ID, register.data.userId)
                    editor.apply()
                    startActivity(Intent(requireContext(), BasketActivity::class.java))
                }, {
                    Log.d("Login", "error ${it}")
                })
            request.retryPolicy = DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,

                0,
                1f
            )
            queue.add(request)
        }
    }

    companion object {
        const val USER_ID = "USER_ID"
    }*/
}