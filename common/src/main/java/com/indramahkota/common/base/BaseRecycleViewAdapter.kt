package com.indramahkota.common.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.indramahkota.common.utils.Constant
import timber.log.Timber

interface BaseModel {
    fun getType(): Int
}

class LoadingModel : BaseModel {
    override fun getType(): Int = Constant.TYPE_LOADING
}

class EmptyModel(val message: String) : BaseModel {
    override fun getType(): Int = Constant.TYPE_EMPTY
}

abstract class BaseRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    protected var data: MutableList<BaseModel> = ArrayList()

    private var lastVisibleItem: Int? = null
    private var totalItemCount: Int? = null
    private var totalItemBeforeLoadMore: Int? = null

    private var isLoadingMore = false
    private var isDataEnd = false

    var page = 1

    private var swipeRefresh = false

    fun addSupportLoadMore(
        recyclerView: RecyclerView,
        visibleThreshold: Int,
        onLoadMore: (page: Int) -> Unit
    ) {
        val layoutManager = recyclerView.layoutManager
        if (layoutManager != null) {
            if (layoutManager is LinearLayoutManager) {
                totalItemBeforeLoadMore = layoutManager.itemCount
                recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        totalItemCount = layoutManager.itemCount
                        lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                        if (!swipeRefresh && !isDataEnd && !isLoadingMore && totalItemCount!! <= (lastVisibleItem!! + visibleThreshold)) {
                            page++
                            onLoadMore(page)
                            isLoadingMore = true
                        }
                    }
                })
            } else {
                Timber.d("Only support LinearLayoutManager")
            }
        } else {
            Timber.d("No LayoutManager found")
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun get(position: Int): BaseModel {
        return data[position]
    }

    fun getPosition(item: BaseModel): Int {
        return data.indexOf(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    open fun setDatas(items: List<BaseModel>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    fun addData(items: List<BaseModel>) {
        data.addAll(items)
        notifyItemRangeInserted(itemCount - 1, items.size)
    }

    open fun addItem(item: BaseModel) {
        data.add(item)
        notifyItemInserted(itemCount - 1)
    }

    fun addItem(item: BaseModel, position: Int) {
        data.add(position, item)
        notifyItemInserted(position)
    }

    fun changeItem(item: BaseModel, position: Int) {
        data.removeAt(position)
        data.add(position, item)
        notifyItemChanged(position)
    }

    fun deleteItem(position: Int) {
        if (position < itemCount) {
            data.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun deleteItem(item: BaseModel) {
        data.remove(item)
    }

    private fun deleteEmpty() {
        val dataCopy = data.toList()
        for ((index, value) in dataCopy.withIndex()) {
            if (value is EmptyModel) {
                deleteItem(index)
            }
        }
    }

    private fun deleteLoading() {
        val dataCopy = data.toList()
        for ((index, value) in dataCopy.withIndex()) {
            if (value is LoadingModel) {
                deleteItem(index)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    fun setEmpty(message: String) {
        deleteEmpty()
        deleteLoading()
        addItem(EmptyModel(message))
    }

    fun setLoading() {
        deleteEmpty()
        deleteLoading()
        isLoadingMore = true
        addItem(LoadingModel())
    }

    fun setLoaded() {
        if (isLoadingMore) {
            isLoadingMore = false
            deleteLoading()
        }
    }

    fun setDataEnd(isDataEnd: Boolean) {
        if (!isDataEnd) {
            page = 1
        }
        this.isDataEnd = isDataEnd
    }

    fun isDataEnd(): Boolean {
        return isDataEnd
    }

    fun swipeRefresh(swipe: Boolean) {
        swipeRefresh = swipe
    }

    fun loadMore(loadMore: Boolean) {
        isLoadingMore = loadMore
    }

    fun getLoadMore(): Boolean {
        return isLoadingMore
    }

    fun getListData(): MutableList<BaseModel> {
        return data
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].getType()
    }
}