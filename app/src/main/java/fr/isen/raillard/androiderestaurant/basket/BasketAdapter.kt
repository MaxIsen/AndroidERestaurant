package fr.isen.raillard.androiderestaurant.basket

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.raillard.androiderestaurant.R
import fr.isen.raillard.androiderestaurant.databinding.CellBasketBinding

class BasketAdapter(private val items: List<BasketItem>): RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    class BasketViewHolder(binding: CellBasketBinding) : RecyclerView.ViewHolder(binding.root) {
        val dishName: TextView = binding.dishname
        val dishPrice: TextView = binding.unityprice
        val dishQuantity: TextView = binding.dishquantity
        val delete: ImageView = binding.deleteDish
        val dishPicture: ImageView = binding.dishPicture
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        return BasketViewHolder(CellBasketBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val basketItem = items[position]
        holder.dishName.text = basketItem.dish.name_fr
        holder.dishQuantity.text = "Quantity : " + basketItem.quantity.toString()
        holder.dishPrice.text = basketItem.dish.prices.first().price + " € "
        Picasso.get()
            .load(basketItem.dish.images[0])
            .placeholder(R.drawable.risotto)
            .into(holder.dishPicture)
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}