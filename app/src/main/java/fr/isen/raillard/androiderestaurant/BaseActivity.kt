package fr.isen.raillard.androiderestaurant

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import fr.isen.raillard.androiderestaurant.basket.Basket
import fr.isen.raillard.androiderestaurant.basket.BasketActivity

open class BaseActivity: AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val menuView = menu?.findItem(R.id.basket)?.actionView
        val counterText = menuView?.findViewById(R.id.basketCount) as? TextView
        val count = getItemsCount()
        counterText?.isVisible = count > 0
        counterText?.text = count.toString()

        menuView?.setOnClickListener{
            if(count > 0){
                startActivity(Intent(this, BasketActivity::class.java))
            }
        }

        return true
    }

    override fun onResume() {
        super.onResume()
        invalidateOptionsMenu()
    }

    private fun getItemsCount():Int {
        val sharedPreferences = getSharedPreferences(Basket.USER_PREFERENCE_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getInt(Basket.ITEMS_COUNT, 0)
    }

}