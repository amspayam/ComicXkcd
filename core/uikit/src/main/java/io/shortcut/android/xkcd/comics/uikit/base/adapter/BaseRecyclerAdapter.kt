package io.shortcut.android.xkcd.comics.uikit.base.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter : RecyclerView.Adapter<BaseViewHolder<Any>>() {

    private var diffCallback: ((oldItems: List<Any>, newItems: List<Any>) -> DiffUtil.Callback)? =
        null
    protected var mItems: MutableList<Any> = mutableListOf()


    protected fun setDiffUtilsCallback(diffCallback: (oldItems: List<Any>, newItems: List<Any>) -> DiffUtil.Callback) {
        this.diffCallback = diffCallback
    }

    //remove all items and add single item
    fun setItem(item: Any) {
        clearAll()
        this.mItems.add(item)
        notifyDataSetChanged()
    }

    //remove all items and add list items
    open fun setItems(items: List<Any>) {
        if (diffCallback != null) {
            setItemsByDiffUtils(items)
            return
        }

        clearAll()
        mItems.addAll(items)
        notifyDataSetChanged()
    }

    private fun setItemsByDiffUtils(items: List<Any>) {
        val result = DiffUtil.calculateDiff(
            diffCallback!!.invoke(ArrayList(mItems), ArrayList(items))
        )
        mItems = items.toMutableList()
        result.dispatchUpdatesTo(this)
    }

    fun replaceItem(position: Int, item: Any) {
        if (mItems.isNullOrEmpty() && position <= mItems.lastIndex) {
            mItems[position] = item
            notifyItemRangeChanged(position, 1)
        }
    }

    fun addItem(item: Any) {
        mItems.add(item)
        val positionStart = mItems.size - 1
        notifyItemRangeChanged(positionStart, 1)
    }

    fun addItemFirst(item: Any) {
        mItems.add(0, item)
        val positionStart = 0
        notifyItemInserted(positionStart)
    }

    fun addItems(items: List<Any>) {
        mItems.addAll(items)
        val positionStart = mItems.size - items.size
        notifyItemRangeChanged(positionStart, items.size)
    }

    open fun addItemsFirst(items: List<Any>) {
        mItems.addAll(0, items)
        val positionStart = 0
        notifyItemRangeChanged(positionStart, 1)
    }

    fun clear(position: Int) {
        if (mItems.size > 0) {
            mItems.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clearAll() {
        mItems.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Any>, position: Int) {
        holder.bind(mItems[position])
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

}