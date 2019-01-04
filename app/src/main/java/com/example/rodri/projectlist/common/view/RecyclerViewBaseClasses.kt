package com.example.rodri.projectlist.common.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

data class ViewWrapper<V : View>(val view: V) : RecyclerView.ViewHolder(view)

interface RecyclerViewBaseBinder<T, V : View> {
    fun bind(data: T, itemView: V)
}

abstract class RecyclerViewBaseAdapter<T, V : View> : RecyclerView.Adapter<ViewWrapper<V>>(),
    RecyclerViewBaseBinder<T, V> {

    private val updateQueue: Queue<List<T>> = ArrayDeque()

    var onClickListener: ((T) -> Unit)? = null

    protected var items: MutableList<T> = ArrayList()
        private set

    protected abstract fun onCreateItemView(parent: ViewGroup, viewType: Int): V

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewWrapper<V> =
        ViewWrapper(onCreateItemView(parent, viewType))

    override fun onBindViewHolder(holder: ViewWrapper<V>, position: Int) {
        bind(items[position], holder.view)
    }

    fun updateItems(newItems: List<T>) {
        updateQueue.add(newItems)
        if (updateQueue.size > 1) {
            return
        }
        updateItemsAsync(newItems)
    }

    private fun updateItemsAsync(new: List<T>) {
        GlobalScope.launch {
            val diffResult = DiffUtil.calculateDiff(BaseAdapterDiffCallback(ArrayList(items), new))
            withContext(Dispatchers.Main) {
                applyUpdates(diffResult, new)
            }
        }
    }

    private fun applyUpdates(result: DiffUtil.DiffResult, new: List<T>) {
        updateQueue.remove(new)
        dispatchUpdates(result, new)
        if (updateQueue.isNotEmpty()) {
            updateItemsAsync(updateQueue.peek())
        }
    }

    private fun dispatchUpdates(result: DiffUtil.DiffResult, new: List<T>) {
        result.dispatchUpdatesTo(this)
        items.clear()
        items.addAll(new)
    }
}

class BaseAdapterDiffCallback<T>(private val oldList: List<T>, private val newList: List<T>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition]?.let {
            it!!.equals(newList[newItemPosition])
        } ?: false

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        areItemsTheSame(oldItemPosition, newItemPosition)
}
