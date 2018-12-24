package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.ui.movielist

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup

import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.entity.MoviePoster

class MovieListAdapter(private val listener: (Long) -> Unit) :
    PagedListAdapter<MoviePoster, androidx.recyclerview.widget.RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        return MovieListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        val movieItem = getItem(position)
        if (movieItem != null) {
            (holder as MovieListViewHolder).bind(movieItem, listener)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<MoviePoster>() {
            override fun areItemsTheSame(oldItem: MoviePoster, newItem: MoviePoster): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MoviePoster, newItem: MoviePoster): Boolean =
                oldItem == newItem
        }
    }
}