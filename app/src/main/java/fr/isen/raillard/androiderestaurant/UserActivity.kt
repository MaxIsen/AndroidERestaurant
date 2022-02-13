package fr.isen.raillard.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import fr.isen.raillard.androiderestaurant.databinding.ActivityUserBinding

/*interface UserActivityFragmentInteraction {
    fun showLogin()
    fun showRegister()
}

class UserActivity : AppCompatActivity(), UserActivityFragmentInteraction {
    lateinit var binding : ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val buttonRegister = binding.buttonRegister
        val buttonLogin = binding.buttonLogin

        val fragment = RegisterFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragment, fragment).commit()
    }

    override fun showLogin()
    {
        val loginFragment = LoginFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment, loginFragment).commit()
    }

    override fun showRegister() {
        val registerFragment = RegisterFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment, registerFragment).commit()
    }
}*/

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
            //fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment, RegisterFragment()).commit()
        }

        buttonLogin.setOnClickListener {
            //fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment, LoginFragment()).commit()
        }
    }

}