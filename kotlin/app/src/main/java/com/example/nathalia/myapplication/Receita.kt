package com.example.nathalia.myapplication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Receita{
    @SerializedName("imagemReceita")
    @Expose
    var imagemReceita:String = ""
    @SerializedName("titulo")
    @Expose
    var titulo:String = ""
    @SerializedName("descricaoIngredientes")
    @Expose
    var descricaoIngredientes: List<String>? = null
    @SerializedName("modoPreparo")
    @Expose
    var modoPreparo: List<String>? = null
}