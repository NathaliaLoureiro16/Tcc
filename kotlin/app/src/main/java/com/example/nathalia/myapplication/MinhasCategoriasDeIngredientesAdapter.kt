package com.example.nathalia.myapplication

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CheckedTextView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.nathalia.myapplication.R.id.checkbox
import kotlinx.android.synthetic.main.list_layout_categorias.view.*
import kotlinx.android.synthetic.main.list_layout_ingredientes.view.*

class MinhasCategoriasDeIngredientesAdapter(var categoriasList: MutableList<Item>,
                                            private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    companion object {
        const val CATEGORIA = 0
        const val INGREDIENTE = 1
        const val OPEN = 0.0F
        const val CLOSE = 180.0F
    }

    data class Item(
            val type: Int,
            var text: String,
            var url: String,
            var checked: Boolean = false,
            var ingredientes: List<Item>? = null)

    inner class CategoriaHolder(v: View) : RecyclerView.ViewHolder(v) {
        var textView = v.categoria_text
        var imagemCategoria = v.imagemCategoria
        val toggleImageView = v.item_toggle_button
    }

    inner class IngredienteHolder(v: View) : RecyclerView.ViewHolder(v) {
        var textView = v.ingredient_text
        var checkBox = v.mycheckbox
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var view: View? = null

        when (viewType) {
            CATEGORIA -> {
                view = inflater.inflate(R.layout.list_layout_categorias, parent, false)
                return CategoriaHolder(view)
            }
            INGREDIENTE -> {
                view = inflater.inflate(R.layout.list_layout_ingredientes, parent, false)
                return IngredienteHolder(view)
            }
        }
        return CategoriaHolder(view!!)
    }

    private val listaingredientes: ArrayList<String> = ArrayList()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == CATEGORIA) {
            val itensDoHolder = holder as? CategoriaHolder
            val item = categoriasList[position]

            itensDoHolder?.let {
                holder.toggleImageView?.let { toggleButton: ImageView ->
                    toggleButton.setImageResource(R.drawable.toggle)
                    toggleButton.rotation = if (item.ingredientes == null) OPEN else CLOSE

                    toggleButton.setOnClickListener { view ->
                        val start: Int = categoriasList.indexOf(item) + 1
                        if (item.ingredientes == null) {
                            var count = 0
                            var proximoHeader = categoriasList.indexOf(categoriasList.find { it ->
                                (count++ >= start) && (item.type == it.type)
                            })
                            if (proximoHeader == -1) proximoHeader = categoriasList.size
                            categoriasList.slice(start..proximoHeader - 1)
                            item.ingredientes = categoriasList.slice(start..proximoHeader - 1)

                            val end = item.ingredientes!!.size
                            if (end > 0) categoriasList.removeAll(item.ingredientes!!)

                            view.animate().rotation(CLOSE).start()
                            notifyItemRangeRemoved(start, end)
                        } else {
                            item.ingredientes?.let { ingredientes ->
                                categoriasList.addAll(start, ingredientes)
                                view.animate().rotation(OPEN).start()
                                notifyItemRangeInserted(start, ingredientes.size)
                                item.ingredientes = null
                            }
                        }
                    }
                    holder.textView.text = item.text

                    Glide.with(context).load(item.url).into(holder.imagemCategoria)
                }

            }
        } else {
            val itensDoHolder = holder as? IngredienteHolder
            val item = categoriasList[position]

            if (itensDoHolder != null) {
                itensDoHolder.textView.text = item.text
                itensDoHolder.checkBox.let {
                    it.isChecked = item.checked
                    it.setOnClickListener { view ->
                        val checkbox = view as CheckBox
                        if (checkbox.isChecked) {
                            listaingredientes.add(item.text)
                            item.checked = true
                        } else {
                            listaingredientes.remove(item.text)
                            item.checked = false
                        }
                        val strArray = "[${listaingredientes.joinToString(", ")}]"
                        Log.i("O ARRAY", strArray)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = categoriasList.size
    override fun getItemViewType(position: Int): Int = categoriasList[position].type
    fun getlistaingredientes(): ArrayList<String> {
        return listaingredientes
    }


}


