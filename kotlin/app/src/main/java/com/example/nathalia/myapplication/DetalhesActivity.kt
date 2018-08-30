package com.example.nathalia.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detalhes.*
import kotlinx.android.synthetic.main.content_detalhes.*


class DetalhesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)
        setSupportActionBar(toolbar)

        val receita = getReceita()
        Glide.with(baseContext).load(receita.imagemReceita).into(imagemReceita)

        titulo.text = receita.titulo
        descricaoIngredientes.text = receita.descricaoIngredientes!!.joinToString("\n")
        modoPreparo.text = receita.modoPreparo!!.joinToString("\n")

    }

    private fun getReceita(): Receita {
        val receita = intent.getStringExtra("receita")
        return Gson().fromJson(receita, Receita::class.java)
    }
}


