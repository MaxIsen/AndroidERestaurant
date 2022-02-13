package fr.isen.raillard.androiderestaurant.basket

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import fr.isen.raillard.androiderestaurant.model.DishModel
import java.io.File
import java.io.Serializable

class Basket(val items: MutableList<BasketItem>): Serializable {
    val itemsCount: Int
        get () {
            val count = items.map {
                it.quantity
            }.reduce {
                acc, i -> acc + i
            }
            return count

            //var count = 0
            //items.forEach {
            //    count += count + it.quantity
            // }
            //return count
        }

    val prixtotal: Float
        get() {
            return items.map {
                it.quantity * it.dish.prices.first().price.toFloat()
            }.reduceOrNull {
                acc, i -> acc + i
            } ?: 0f
        }

    fun addItem(item: DishModel, quantity: Int) {
        val existingItem = items.firstOrNull {
            it.dish.name_fr == item.name_fr
        }
        existingItem?.let {
            existingItem.quantity += quantity
        } ?:
        run {
            val basketItem = BasketItem(item, quantity)
            items.add(basketItem)
        }
    }

    fun removeDish(basketItem: BasketItem) {
        items.remove(basketItem)
    }

    fun save(context: Context) {
        val jsonFile = File(context.cacheDir.absolutePath + BASKET_FILE)
        val json = GsonBuilder().create().toJson(this)
        jsonFile.writeText(json)
        updateCounter(context)
    }

    private fun updateCounter(context: Context) {
        val sharedPreferences = context.getSharedPreferences(USER_PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(ITEMS_COUNT, itemsCount)
        editor.apply()
        Log.d(ITEMS_COUNT,itemsCount.toString())
    }

    companion object {
        fun getBasket(context: Context): Basket {
            val jsonFile = File(context.cacheDir.absolutePath + BASKET_FILE)
            if(jsonFile.exists()){
                val json = jsonFile.readText()
               return GsonBuilder().create().fromJson(json, Basket::class.java)
            } else {
                return Basket(mutableListOf())
            }
        }

        const val BASKET_FILE = "basket.json"
        const val ITEMS_COUNT = "ITEMS_COUNT"
        const val USER_PREFERENCE_NAME = "USER_PREFERENCE_NAME"
    }
}

class BasketItem(val dish: DishModel, var quantity: Int): Serializable {}