package fr.isen.raillard.androiderestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.raillard.androiderestaurant.databinding.CategoryCellBinding
import fr.isen.raillard.androiderestaurant.model.DishModel

class FoodAdapter(val foods: List<DishModel>, val onFoodClicked: (DishModel) -> Unit): RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder(binding: CategoryCellBinding): RecyclerView.ViewHolder(binding.root) {
        val foodPicture = binding.foodPicture
        val foodName = binding.foodName
        val foodPrice = binding.foodPrice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = CategoryCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {


        val dish = foods[position]

        if(dish.images[0]!="") {
            Picasso.get()
                .load(dish.images[0])
                .error(R.drawable.pizza)
                .into(holder.foodPicture)



        }
        else{
            holder.foodPicture.setImageResource(R.drawable.pizza)
        }

        holder.foodName.text = dish.name_fr
        holder.foodPrice.text = dish.prices[0].prices+"€"


        holder.itemView.setOnClickListener {
            onFoodClicked(foods[position])
        }

    }

    override fun getItemCount(): Int = foods.size
}