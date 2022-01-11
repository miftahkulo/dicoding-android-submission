package com.indramahkota.app.ui.widget

import androidx.recyclerview.widget.RecyclerView
import com.indramahkota.app.databinding.VhItemEmptyBinding
import com.indramahkota.app.databinding.VhItemLoadingBinding

class LoadingViewHolder(val binding: VhItemLoadingBinding) :
    RecyclerView.ViewHolder(binding.root)

class EmptyViewHolder(val binding: VhItemEmptyBinding) :
    RecyclerView.ViewHolder(binding.root)