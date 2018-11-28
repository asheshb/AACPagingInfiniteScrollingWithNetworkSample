package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.ui.movielist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.R
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.ResourceUrl
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.entity.MoviePoster
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.ui.getReadable
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*


class MovieListViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(movie: MoviePoster?, listener: (Long) -> Unit) {
        if (movie != null) {
            with(movie) {
                moviePoster.contentDescription = title
                Picasso.get().load(ResourceUrl.getPosterUrl(posterPath))
                    .placeholder(R.drawable.poster_placeholder).into(moviePoster)
                val rating = voteAverage / 10 * 5
                ratingBar.rating = rating
                movieReleaseDate.text = releaseDate.getReadable()
                itemView.setOnClickListener { listener.invoke(this.id) }
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): MovieListViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
            return MovieListViewHolder(view)
        }
    }
}