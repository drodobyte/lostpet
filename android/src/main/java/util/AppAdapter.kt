package util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class AppAdapter<T>(
    private val layoutId: Int,
    open val listener: (T) -> Unit
) : RecyclerView.Adapter<AppAdapter.Holder<T>>() {

    val items = mutableListOf<T>()

    abstract fun newHolder(view: View): Holder<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder<T> {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return newHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: Holder<T>, position: Int) {
        holder.bind(items[position], position)
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    operator fun get(i: Int) = items[i]

    operator fun plus(item: T) = items.apply {
        add(item)
        notifyItemInserted(size - 1)
    }

    abstract class Holder<T>(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        abstract fun bind(item: T, position: Int)
    }
}