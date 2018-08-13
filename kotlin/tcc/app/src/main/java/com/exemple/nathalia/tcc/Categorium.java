package com.exemple.nathalia.tcc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Categorium {

@SerializedName("imagemCategoria")
@Expose
public String imagemCategoria;
@SerializedName("categoria")
@Expose
public String categoria;
@SerializedName("ingredientes")
@Expose
public List<String> ingredientes = null;

}