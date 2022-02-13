package fr.isen.raillard.androiderestaurant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import fr.isen.raillard.androiderestaurant.basket.BasketActivity
import fr.isen.raillard.androiderestaurant.databinding.FragmentLoginBinding
import fr.isen.raillard.androiderestaurant.model.RegisterModel
import org.json.JSONObject

/*class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding


    //var interactor = UserActivityFragmentInteraction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentLoginBinding.inflate(layoutInflater)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
       // interactor = context as? UserActivityFragmentInteraction
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvRegister.setOnClickListener{
            //finish()
           // startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }
    }
}*/

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val buttonValidate = binding.btnLogin
        buttonValidate.setOnClickListener(){
            login()
        }

    }
    private fun login() {
        val params = HashMap<String, String>()
        params["id_shop"]="1"
        params["email"] = binding.email.text.toString()
        params["password"] = binding.motdepasse.text.toString()
        var errorBool: Boolean
        errorBool = true
        if (TextUtils.isEmpty(binding.email.text)){
            binding.email.error = "Erreur lors de la saisie de l'adresse mail"
            errorBool = false
        }
        else binding.email.error = null
        if (TextUtils.isEmpty(binding.motdepasse.text)){
            binding.password.error = "Erreur lors de la saisie du mot de passe"
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
                    Log.d("",register.data.userId)
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
    }

}

