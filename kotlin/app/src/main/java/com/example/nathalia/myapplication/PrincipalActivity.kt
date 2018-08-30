package com.example.nathalia.myapplication

import android.content.Intent
import android.Manifest
import android.app.Activity
import android.graphics.Bitmap
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import android.support.design.widget.Snackbar
import android.view.View
import android.view.Window
import com.google.gson.Gson
import com.github.florent37.runtimepermission.kotlin.askPermission
import kotlinx.android.synthetic.main.activity_principal.*
import kotlinx.android.synthetic.main.content_principal.*
import java.io.IOException
import com.example.nathalia.myapplication.R.string.action_settings




class PrincipalActivity : AppCompatActivity() {

    private var categorias: Array<MinhasCategoriasDeIngredientes>? = null

    val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        setSupportActionBar(toolbar)

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.action_camera -> {
                tirarFoto()
                true
            }
            R.id.action_receitas -> {
                val ingredientes = (recycler_cardview.adapter as MinhasCategoriasDeIngredientesAdapter).getlistaingredientes()
                if (ingredientes.isEmpty()) {
                    Snackbar.make(currentFocus!!, "SELECIONE OS INGREDIENTES PRIMEIRO :)", Snackbar.LENGTH_LONG).show()
                } else {
                    buscarReceitas()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
        val json: String
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

    fun tirarFoto() {


        askPermission(Manifest.permission.CAMERA) {
            //all permissions already granted or just granted
            takePictureIntent()
            // your action
        }.onDeclined { e ->
            //the list of denied permissions
            //e.denied.forEach{

            //}
            /*
            AlertDialog.Builder(this@MainActivityKotlinCoroutine)
                    .setMessage("Please accept our permissions")
                    .setPositiveButton("yes", (dialog, which) -> { result.askAgain(); })
                    .setNegativeButton("no", (dialog, which) -> { dialog.dismiss(); })
                    .show();
            */

            //the list of forever denied permissions, user has check 'never ask again'
            //e.foreverDenied.forEach{

            //}
            // you need to open setting manually if you really need it
            //e.goToSettings();
        }

    }


    private fun takePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val extras = data.extras
            val imageBitmap = extras!!.get("data") as Bitmap

        }
    }


}