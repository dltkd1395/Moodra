package com.example.moviesimpleapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesimpleapp.R
import com.example.moviesimpleapp.databinding.ItemMovieBinding
import com.example.moviesimpleapp.databinding.ItemTvshowBinding
import com.example.moviesimpleapp.retrofit.Movie
import com.example.moviesimpleapp.retrofit.TVTrailer
import com.example.moviesimpleapp.retrofit.TVshow

class TVshowAdapter(val tvshowItemClick: (TVshow) -> Unit, val tvshowItemLongClick: (TVshow) -> Unit)

    : PagingDataAdapter<TVshow, TVshowAdapter.ViewHolder>(TVshowDiffUtil) {


    inner class ViewHolder(private val binding: ItemTvshowBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow: TVshow){
            binding.tvshow = tvshow
            binding.executePendingBindings() // 데이터가 수정되면 즉각 바인딩

            binding.root.setOnClickListener {
                tvshowItemClick(tvshow)
            }
            binding.root.setOnLongClickListener {
                tvshowItemLongClick(tvshow)
                true
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemTvshowBinding>(layoutInflater, viewType,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvshow = getItem(position)

        holder.bind(tvshow!!)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_tvshow
    }

    companion object TVshowDiffUtil: DiffUtil.ItemCallback<TVshow>() {
        override fun areItemsTheSame(oldItem: TVshow, newItem: TVshow): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: TVshow, newItem: TVshow): Boolean {
            return oldItem==newItem
        }

    }
}