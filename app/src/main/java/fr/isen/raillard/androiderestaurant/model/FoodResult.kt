package fr.isen.raillard.androiderestaurant.model;

data class FoodResult ( val data: List<ItemsViewModel>)

data class CategoryModel (val name_fr: String, val items: List<FoodModel>)

data class FoodModel(val name_fr: String)
