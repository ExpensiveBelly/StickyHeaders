package com.example.stickyheaders

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.stickyheaders.stickyheaders.StickyHeaderAdapter

class MyStickyHeaderAdapter :
    ListAdapter<StickyItem, MyStickyHeaderAdapter.MyViewHolder>(EqualsDiffItemCallback()),
    StickyHeaderAdapter<MyStickyHeaderAdapter.MyViewHolder> {

    override fun getHeaderId(position: Int) =
        if (position > currentList.size || position < 0) -1L else getItem(position).headerId

    override fun onCreateHeaderViewHolder(parent: ViewGroup) =
        MyViewHolder.Header(
            LayoutInflater.from(parent.context).inflate(R.layout.row_header, parent, false)
        )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder.Item(
            LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    sealed class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: StickyItem)

        class Header(itemView: View) : MyViewHolder(itemView) {
            private val header_label = itemView.findViewById<TextView>(R.id.header_label)
            override fun bind(item: StickyItem) {
                header_label.text = item.content
            }

        }

        class Item(itemView: View) : MyViewHolder(itemView) {
            private val item_label = itemView.findViewById<TextView>(R.id.item_label)
            override fun bind(item: StickyItem) {
                item_label.text = item.content
            }
        }
    }

    override fun onBindHeaderViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private class EqualsDiffItemCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(item1: T, item2: T) = areContentsTheSame(item1, item2)

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(item1: T, item2: T) = item1 == item2
}


