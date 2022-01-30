package fr.isen.raillard.androiderestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.isen.raillard.androiderestaurant.databinding.CategoryCellBinding
import fr.isen.raillard.androiderestaurant.model.Food

class FoodAdapter(val foods: List<Food>, val onFoodClicked: (Food) -> Unit): RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

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
        holder.foodName.text = foods[position].name
        holder.foodPicture.setImageResource(foods[position].picture)
        holder.foodPrice.text = foods[position].price

        holder.itemView.setOnClickListener {
            onFoodClicked(foods[position])
        }
    }

    override fun getItemCount(): Int = foods.size
}