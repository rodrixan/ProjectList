package com.example.rodri.projectlist.common.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

data class ViewWrapper<V : View>(val view: V) : RecyclerView.ViewHolder(view)

interface RecyclerViewBaseBinder<T, V : View> {
    fun bind(data: T, view: V)
}

abstract class RecyclerViewBaseAdapter<T, V : View> : RecyclerView.Adapter<ViewWrapper<V>>(),
    RecyclerViewBaseBinder<T, V> {

    protected var items: MutableList<T> = ArrayList()
        set(value) {
            if (items.isEmpty()) {
                items = ArrayList(value)
                notifyItemRangeInserted(0, value.size)
            } else {
                //DiffUtil
            }
        }

    protected abstract fun onCreateItemView(parent: ViewGroup, viewType: Int): V

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewWrapper<V> =
        ViewWrapper(onCreateItemView(parent, viewType))

    override fun onBindViewHolder(holder: ViewWrapper<V>, position: Int) {
        bind(items[position], holder.view)
    }

}