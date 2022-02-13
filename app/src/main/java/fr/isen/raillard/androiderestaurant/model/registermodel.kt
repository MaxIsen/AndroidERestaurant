package fr.isen.raillard.androiderestaurant.model

import com.google.gson.annotations.SerializedName


data class RegisterModel(@SerializedName("data") val data: LoginModel, @SerializedName("code") val code: Int)
data class LoginModel(@SerializedName("id") val userId: String)