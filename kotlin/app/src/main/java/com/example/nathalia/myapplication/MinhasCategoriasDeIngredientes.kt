package com.example.nathalia.myapplication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class MinhasCategoriasDeIngredientes{
    @SerializedName("imagemCategoria")
    @Expose
    var imagemCategoria:String = ""
    @SerializedName("categoria")
    @Expose
    var categoria:String = ""
    @SerializedName("ingredientes")
    @Expose
    var ingredientes: List<String>? = null
}