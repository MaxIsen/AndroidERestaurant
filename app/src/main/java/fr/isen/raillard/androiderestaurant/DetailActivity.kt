package fr.isen.raillard.androiderestaurant

import android.annotation.SuppressLint
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import fr.isen.raillard.androiderestaurant.basket.Basket
import fr.isen.raillard.androiderestaurant.databinding.DetailsBinding
import fr.isen.raillard.androiderestaurant.model.DishModel


class DetailActivity : BaseActivity() {

    private lateinit var binding:DetailsBinding

    private var itemCount = 1
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        binding = DetailsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val dish = intent.getSerializableExtra("dish") as DishModel
        initDetail(dish)
        clickObserver()
        refreshShop()
    }

    private fun initDetail(dish: DishModel) {
        binding.detailTitle.text = dish.name_fr

        binding.Price.text = dish.prices.joinToString("\n") { it.price + "€" }

        binding.dishPhotoPager.adapter = DishPictureAdapter(this, dish.images)

        binding.dishIngredient.text = dish.ingredients.joinToString("\n") { it.name_fr }
    }

    @SuppressLint("SetTextI18n")
    private fun refreshShop() {
        val dish = intent.getSerializableExtra("dish") as DishModel
        val price = dish.prices.first().price.toFloat()
        binding.validation.text = "${getString(R.string.total)} ${ price * itemCount } € \n Ajouter au panier"
        binding.quantity.text = "$itemCount"
    }

    private fun clickObserver() {
        val dish = intent.getSerializableExtra("dish") as DishModel
        binding.boutonMoins.setOnClickListener{
            if ( itemCount == 1)
            {
                itemCount = 1
            }
            else {
                itemCount -= 1
            }
            refreshShop()
        }

        binding.boutonPlus.setOnClickListener {
            itemCount += 1
            refreshShop()
        }

        binding.validation.setOnClickListener {
            // Add to basket
            this.cacheDir.absolutePath
            val basket = Basket.getBasket(this)
            basket.addItem(dish, itemCount)
            basket.save(this)
            Snackbar.make(binding.root, R.string.itemAdded, Snackbar.LENGTH_LONG).show()
            invalidateOptionsMenu()
        }
    }
}