package com.exemple.nathalia.tcc

import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.content.res.AppCompatResources
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.cardview_parent.view.*

 class ExpandableCardViewAdapter(var items: MutableList<Item>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val PARENT = 0
        const val CHILD = 1
         const val OPEN = 0.0F
        const val CLOSE = 180.0F
    }

    data class Item(val type: Int = 0,
                    var text: String = "Default",
                    var children: List<Item>? = null)

    inner class ItemHolder(v: View) : RecyclerView.ViewHolder(v) {
        var textView = v.item_text
        var image = v.imageView4
        val toggleImageView = v.item_toggle_button
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var view: View? = null

        when (viewType) {
            PARENT -> view = inflater.inflate(R.layout.cardview_parent, parent, false)
            CHILD -> view = inflater.inflate(R.layout.cardview_child, parent, false)
        }

        return ItemHolder(view!!)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemHolder = holder as? ItemHolder
        val item = items[position]

        itemHolder?.let {
            it.toggleImageView?.let {
                it.setImageResource(R.drawable.toggle)
                it.rotation = if (item.children == null) OPEN else CLOSE

                it.setOnClickListener { view ->
                    val start: Int = items.indexOf(item) + 1
                    if (item.children == null) {
                        var count = 0
                        var nextHeader = items.indexOf(items.find {
                            (count++ >= start) && (it.type == item.type)
                        })

                        if (nextHeader == -1) nextHeader = items.size
                        item.children = items.slice(start..nextHeader - 1)

                        val end = item.children!!.size
                        if (end > 0) items.removeAll(item.children!!)

                        view.animate().rotation(CLOSE).start()
                        notifyItemRangeRemoved(start, end)
                    } else {
                        item.children?.let {
                            items.addAll(start, it)
                            view.animate().rotation(OPEN).start()
                            notifyItemRangeInserted(start, it.size)
                            item.children = null
                        }
                    }
                }
            }

            it.textView.text = item.text
            it.image

        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = items[position].type


}