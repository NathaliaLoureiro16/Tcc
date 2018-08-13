package com.exemple.nathalia.tcc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        var json: String? = null
        try
        {
            val inputStream = getAssets().open("ingredientes.json,receitas.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)

            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset("UTF-8"))
            val categorias = Gson().fromJson(json, Categorium::class.java)
        }
        catch (e: IOException) {
            e.printStackTrace()
        }

        val cardView = recycler_cardview

        val itemList = arrayListOf<ExpandableCardViewAdapter.Item>()

        val laticinios = ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.PARENT, "Laticinios")
        laticinios.children = listOf(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Manteiga"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Queijo parmesão"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Queijo mussarela"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Iogurte natural"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Creme de leite"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Leite condensado"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Leite"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Queijo provolone"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Requeijão"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Margarina"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Chocolate amargo"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Chocolate granulado"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Queijo ralado"))

        itemList.add(laticinios)

        val vegetais = ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.PARENT, "Vegetais")
        vegetais.children = listOf(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "seila"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"))
        itemList.add(vegetais)


        val frutas = ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.PARENT, "Frutas")
        frutas.children = listOf(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "seila"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"))
        itemList.add(frutas)

        val graos = ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.PARENT, "Grãos")
        graos.children = listOf(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "seila"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Oreo"))
        itemList.add(graos)

        val grao = ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.PARENT, "Adoçantes")
        grao.children = listOf(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "seila"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"))
        itemList.add(grao)

        val tempero = ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.PARENT, "Temperos")
        tempero.children = listOf(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "seila"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"))
        itemList.add(tempero)

        val pao = ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.PARENT, "Pães")
        pao.children = listOf(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "seila"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"))
        itemList.add(pao)

        val carne = ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.PARENT, "Carnes")
        carne.children = listOf(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "seila"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"))
        itemList.add(carne)

        val mar = ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.PARENT, "Frutos do mar")
        mar.children = listOf(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "seila"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"))
        itemList.add(mar)

        val condi = ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.PARENT, "Condimentos")
        condi.children = listOf(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "seila"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"))
        itemList.add(condi)

        val oleo = ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.PARENT, "Óleos")
        oleo.children = listOf(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "seila"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"))
        itemList.add(oleo)

        val molho = ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.PARENT, "Molhos")
        molho.children = listOf(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "seila"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"))
        itemList.add(molho)

        val bebida = ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.PARENT, "Bebidas")
        bebida.children = listOf(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "seila"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"))
        itemList.add(bebida)

        val massa = ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.PARENT, "Massas")
        massa.children = listOf(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "seila"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"), ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "a"))
        itemList.add(massa)




        cardView.layoutManager = LinearLayoutManager(this)
        cardView.adapter = ExpandableCardViewAdapter(itemList)
    }

}