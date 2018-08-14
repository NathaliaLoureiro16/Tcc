package com.exemple.nathalia.tcc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var categorias: List<Categoria>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        var json: String? = null
        try
        {
            val inputStream = getAssets().open("ingredientes.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)

            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset("UTF-8"))
            categorias = Gson().fromJson(json, Array<Categoria>::class.java).asList()
        }
        catch (e: IOException) {
            e.printStackTrace()
        }
        val itemList = arrayListOf<ExpandableCardViewAdapter.Item>()
        if (categorias!= null)
        for(item in categorias!!){
            val header = ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.PARENT, item.categoria)
            val itemListChild = arrayListOf<ExpandableCardViewAdapter.Item>()
            for (ingrediente in item.ingredientes!!){

                itemListChild.add(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, ingrediente))
            }
            header.children = itemListChild
            itemList.add(header)
        }
        val cardView = recycler_cardview




//        laticinios.children = listOf(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Manteiga"),
//                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Queijo parmesão"),
//                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Queijo mussarela"),
//                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Iogurte natural"),
//                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Creme de leite"),
//                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Leite condensado"),
//                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Leite"),
//                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Queijo provolone"),
//                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Requeijão"),
//                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Margarina"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Chocolate amargo"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Chocolate granulado"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Queijo ralado"))





        cardView.layoutManager = LinearLayoutManager(this)
        cardView.adapter = ExpandableCardViewAdapter(itemList)
    }

}