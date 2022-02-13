package fr.isen.raillard.androiderestaurant.model

import java.io.Serializable

data class Basket (
    val food : Food,
    var quantity : Int
    ) : Serializable