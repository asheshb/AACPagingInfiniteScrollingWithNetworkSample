package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.ui.moviedetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.MovieSampleApp
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.R
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.ResourceUrl
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.domain.entity.Movie
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.ui.getReadable
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import javax.inject.Inject


class MovieDetailFragment : Fragment() {
    @Inject
    lateinit var movieDetailViewModelFactory: MovieDetailViewModelFactory

    override fun onAttach(context: Context?) {
        MovieSampleApp.instance.getApplicationComponent().plusFragmentComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie_detail, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {


        super.onActivityCreated(savedInstanceState)
        val movieDetailViewModel = ViewModelProviders.of(
            this,movieDetailViewModelFactory)
            .get(MovieDetailViewModel::class.java)

        movieDetailViewModel.movie.observe(viewLifecycleOwner, Observer { it ->
            it?.let { setMovieData(it) }
        })

        val params = MovieDetailFragmentArgs.fromBundle(arguments)
        movieDetailViewModel.setMovieId(params.movieId.toLong())

    }

    private fun setMovieData(movie: Movie) {
        title.text = movie.title
        overview.text = movie.overview

        movie.backdropPath?.let {
            backdrop.visibility = View.VISIBLE
            Picasso.get().load(ResourceUrl.getBackdropUrl(it))
                .placeholder(R.drawable.poster_placeholder).into(backdrop)
        }
                ?: run { backdrop.visibility = View.GONE }

        Picasso.get().load(ResourceUrl.getPosterUrl(movie.posterPath))
            .placeholder(R.drawable.poster_placeholder).into(poster)
        releaseDate.text = movie.releaseDate.getReadable()
    }
}
