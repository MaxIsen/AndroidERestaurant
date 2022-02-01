package fr.isen.raillard.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import fr.isen.raillard.androiderestaurant.model.DishModel


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var item: String? = ""

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
            detailtext.append(i.name_fr + " \n")

    }
}