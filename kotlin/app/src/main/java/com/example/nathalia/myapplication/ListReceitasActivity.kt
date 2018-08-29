package com.example.nathalia.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_list_receitas.*

class ListReceitasActivity : AppCompatActivity() {

    private var receitas: List<Receita> = emptyList()
    private val gson: Gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_receitas)

        val url = intent.getStringExtra("URL")
        lerReceitas(url)

        Log.i("nath", "url" + url)

        val receitasListName = receitas.map {
            it.titulo
        }
        val adapter = ArrayAdapter(baseContext, android.R.layout.simple_list_item_1, receitasListName)
        listview.adapter = adapter
        listview.setOnItemClickListener { parent, view, position, id ->
            val receita = receitas.get(position)
            val intent = Intent(baseContext, DetalhesActivity::class.java)
            intent.putExtra("receita", gson.toJson(receita))
            startActivity(intent)
        }
    }

    fun btn(view: View) {
        val intent = Intent(baseContext, DetalhesActivity::class.java)
        startActivity(intent)
    }

    fun lerReceitas(url: String) {


        val jsonObjectRequest = GsonRequest(url, Array<Receita>::class.java, null,
                Response.Listener { response ->
                    //Toast.makeText(baseContext,"Leu as receitas", Toast.LENGTH_LONG).show()
                    receitas = response.toList()
                    val receitasListName = receitas.map {
                        it.titulo
                    }
                    val adapter = ArrayAdapter(baseContext, android.R.layout.simple_list_item_1, receitasListName)
                    listview.adapter = adapter
                },
                Response.ErrorListener {
                }
        )

        Volley.newRequestQueue(baseContext).add(jsonObjectRequest)
    }


}
