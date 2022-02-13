package fr.isen.raillard.androiderestaurant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import com.google.gson.GsonBuilder
import fr.isen.raillard.androiderestaurant.basket.BasketActivity
import fr.isen.raillard.androiderestaurant.databinding.FragmentRegisterBinding
import fr.isen.raillard.androiderestaurant.model.RegisterModel
import org.json.JSONObject

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonValidate = binding.buttonRegister

        buttonValidate.setOnClickListener() {
            register()
        }
    }

    private fun register() {
        val firstname = binding.nameCreateAccountInput.text.toString()
        val name = binding.surnameCreateAccountInput.text.toString()
        val address = binding.addressCreateAccountInput.text.toString()
        val email = binding.emailCreateAccountInput.text.toString()
        val password = binding.passwordCreateAccountInput.text.toString()
        val phone = binding.phoneCreateAccountInput.text.toString()

        val params = HashMap<String, String>()
        params["id_shop"] = "1"
        params["lastname"] = name
        params["address"] = address
        params["email"] = email
        params["password"] = password
        params["firstname"] = firstname
        params["phone"] = phone
        val errorBool: Boolean

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

        errorBool = error

        if (errorBool) {
            val queue = Volley.newRequestQueue(requireContext())
            val url = "http://test.api.catering.bluecodegames.com/user/register"
            val jsonObject = JSONObject(params as HashMap<*, *>)
            val request = JsonObjectRequest(
                Request.Method.POST, url, jsonObject,
                { response ->
                    Log.e("response", response.toString())
                    val register =
                        GsonBuilder().create()
                            .fromJson(response.toString(), RegisterModel::class.java)
                    val editor =
                        requireContext().getSharedPreferences(
                            DetailActivity.APP_PREFS,
                            Context.MODE_PRIVATE
                        ).edit()
                    Log.d("",register.data.userId)
                    editor.putString(USER_ID, register.data.userId)
                    editor.apply()
                }, {
                    Log.e("API", it.toString())
                })
            request.retryPolicy = DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,

                0,
                1f
            )
            queue.add(request)

            startActivity(Intent(requireContext(), BasketActivity::class.java))
        }
        else Snackbar.make(binding.root, "Les informations sont incorrectes !", Snackbar.LENGTH_LONG).show()
    }
    companion object {
        const val USER_ID = "USER_ID"
    }
}