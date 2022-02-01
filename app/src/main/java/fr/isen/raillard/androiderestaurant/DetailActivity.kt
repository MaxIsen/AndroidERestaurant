package fr.isen.raillard.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import fr.isen.raillard.androiderestaurant.databinding.DetailsBinding
import fr.isen.raillard.androiderestaurant.model.DishModel


class DetailActivity : AppCompatActivity() {

    private lateinit var binding:DetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        binding = DetailsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val dish = intent.getSerializableExtra("dish") as DishModel
        initDetail(dish)

        /*setContentView(R.layout.activity_detail)
        findViewById<TextView>(R.id.TitreDetail).text = (intent.getSerializableExtra("food") as DishModel).name_fr

        val itemDish = intent.getSerializableExtra("food") as DishModel

        val detailtitle = findViewById<TextView>(R.id.TitreDetail)
        detailtitle.setText(itemDish.name_fr)
        val detailimage = findViewById<ImageView>(R.id.detailImage)
        if(itemDish.images[0]!="") {
            Picasso.get()
                .load(itemDish.images[0])
                .error(R.drawable.pizza)
                .into(detailimage)
        }
        else{
            detailimage.setImageResource(R.drawable.pizza)
        }

        val detailprice = findViewById<TextView>(R.id.detailprice)
        detailprice.setText(itemDish.prices[0].price + "€")

        val detailtext = findViewById<TextView>(R.id.detailtext)

        for (i in itemDish.ingredients)
            detailtext.append(i.name_fr + " \n")*/

    }

    private fun initDetail(dish: DishModel) {
        var nbInBasket =  1
        binding.detailTitle.text = dish.name_fr

        binding.dishPhotoPager.adapter = DishPictureAdapter(this, dish.images)

        binding.dishIngredient.text = dish.ingredients.joinToString("\n") { it.name_fr }

        // TODO : quand je clique sur le bouton plus, j'incrémente nbInBasket
        // TODO : quand je clique sur le bouton moins, je décrémente nbInBasket tout en vérifiant qu'il ne devienne pas négatif
    }
}