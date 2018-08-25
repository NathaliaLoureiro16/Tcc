package com.example.nathalia.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ArrayAdapter
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_list_receitas.*

class ListReceitasActivity : AppCompatActivity() {

    private var receitas: List<Receita> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_receitas)

        val url = intent.getStringExtra("URL")
        lerReceitas(url)

        Log.i("nath","url" + url )

        val receitasListName = receitas.map { it.titulo }
        val adapter = ArrayAdapter(baseContext, android.R.layout.simple_list_item_1,receitasListName)
        listview.adapter = adapter
    }

    fun lerReceitas(url:String) {


        val jsonObjectRequest = GsonRequest(url, Array<Receita>::class.java, null,
                Response.Listener { response ->
                    //Toast.makeText(baseContext,"Leu as categorias", Toast.LENGTH_LONG).show()
                    receitas = response.toList()
                    Log.i("Receitas", receitas.toString())
                },
                Response.ErrorListener {
                }
        )

        Volley.newRequestQueue(baseContext).add(jsonObjectRequest)
    }
//    fun fromfile(){
//        var json: String
//        try
//        {
//            val inputStream = getAssets().open("receitas.json")
//            val size = inputStream.available()
//            val buffer = ByteArray(size)
//
//            inputStream.read(buffer)
//            inputStream.close()
//            json = String(buffer, charset("UTF-8"))
//           receitas = Gson().fromJson(json, Receita::class.java)
//        }
//        catch (e: Exception) {
//            e.printStackTrace()
//        }

    }



//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
//
//        setContentView(R.layout.activity_main)
//
//        val receitas = ArrayList<Receita_model>()
//        receitas.add(Receita_model("Kaju katli", "Kaju katli, also known as kaju Katari or kaju barfi, is an Indian dessert similar to a barfi.",R.drawable.kaju))
//
//
//        val obj_adapter = CustomAdapter(receitas)
//
//        recyclerView.adapter = obj_adapter
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

