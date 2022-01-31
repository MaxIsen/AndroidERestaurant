package fr.isen.raillard.androiderestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.raillard.androiderestaurant.databinding.CategoryCellBinding
import fr.isen.raillard.androiderestaurant.model.FoodModel

class FoodAdapter(val foods: List<FoodModel>, val onFoodClicked: (FoodModel) -> Unit): RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

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
        holder.foodName.text = foods[position].name_fr

        Picasso.get()
            .load(foods[position].getFirstPictures())
            .error(R.drawable.risotto)
            .placeholder(R.drawable.risotto)
            .into(holder.foodPicture)
        //holder.foodPicture.setImageResource(foods[position].getFirstPictures())
        holder.foodPrice.text = foods[position].getFormattedPrice()

        holder.itemView.setOnClickListener {
            onFoodClicked(foods[position])
        }
    }

    override fun getItemCount(): Int = foods.size
}