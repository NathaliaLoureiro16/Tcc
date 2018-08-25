package com.example.nathalia.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private var categorias: Array<MinhasCategoriasDeIngredientes>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.actionreceitas -> {
                buscarReceitas()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun buscarReceitas() {
        val intent = Intent(baseContext, ListReceitasActivity::class.java)
        var url = "http://api-receitas-com.umbler.net/api/receitas?"
        val ingredientes = (recycler_cardview.adapter as MinhasCategoriasDeIngredientesAdapter).getlistaingredientes()
        ingredientes.forEach {
            url += "ingredientesBase=${it.toLowerCase()}&"
        }
        intent.putExtra("URL", url.take(url.length - 1))
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        lerCategorias()
    }


    fun lerCategorias() {
        val url = "http://api-receitas-com.umbler.net/api/categorias"

        val jsonObjectRequest = GsonRequest(url, Array<MinhasCategoriasDeIngredientes>::class.java, null,
                Response.Listener { response ->
                    //Toast.makeText(baseContext,"Leu as categorias", Toast.LENGTH_LONG).show()
                    categorias = response
                    listViewfromCategorias()
                },
                Response.ErrorListener {
                }
        )

        Volley.newRequestQueue(baseContext).add(jsonObjectRequest)
    }

    fun fromfile() {
        var json: String
        try {
            val inputStream = getAssets().open("ingredientes.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)

            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset("UTF-8"))
            categorias = Gson().fromJson(json, Array<MinhasCategoriasDeIngredientes>::class.java)
            listViewfromCategorias()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun listViewfromCategorias() {
        val itemList = arrayListOf<MinhasCategoriasDeIngredientesAdapter.Item>()
        if (categorias != null)
            for (item in categorias!!) {
                val header = MinhasCategoriasDeIngredientesAdapter.Item(MinhasCategoriasDeIngredientesAdapter.CATEGORIA, item.categoria, item.imagemCategoria)
                val itemListIngredientes = arrayListOf<MinhasCategoriasDeIngredientesAdapter.Item>()
                for (ingrediente in item.ingredientes!!) {

                    itemListIngredientes.add(MinhasCategoriasDeIngredientesAdapter.Item(MinhasCategoriasDeIngredientesAdapter.INGREDIENTE, ingrediente.toString(), item.imagemCategoria))
                }
                header.ingredientes = itemListIngredientes
                itemList.add(header)
            }
        val cardView = recycler_cardview
        cardView.layoutManager = LinearLayoutManager(baseContext)
        cardView.adapter = MinhasCategoriasDeIngredientesAdapter(itemList, baseContext)
    }

}