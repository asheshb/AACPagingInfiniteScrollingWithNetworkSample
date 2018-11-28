package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.network

import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.BuildConfig
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.MovieData
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbService {

    @GET("discover/movie?certification_country=US&adult=false&with_original_language=en&sort_by=primary_release_date.desc")
    fun getMovies(
        @Query("primary_release_date.lte") releaseDate: String
    ): Call<List<MovieData>>

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"

        private val retrofitService: TmdbService by lazy{
            // Add api key to every request
            val interceptor = Interceptor { chain ->
                val request = chain.request()
                val url = request.url().newBuilder()
                    .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                    .build()
                val newRequest = request.newBuilder()
                    .url(url)
                    .build()
                chain.proceed(newRequest)

            }

            val httpClient = OkHttpClient().newBuilder().addInterceptor(interceptor).build()

            val gson = GsonBuilder()
                .registerTypeAdapterFactory(ItemTypeAdapterFactory())
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            retrofit.create(TmdbService::class.java)
        }

        fun getInstance(): TmdbService {
            return retrofitService
        }
    }

}
