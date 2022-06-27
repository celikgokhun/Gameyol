package com.trendyol.celik.gokhun.base.recyclerview

import androidx.recyclerview.widget.DiffUtil

class DataClassDiffCallback<T>(private val uniqueProperty: (T) -> Any?) : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T) = uniqueProperty(oldItem) == uniqueProperty(newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}