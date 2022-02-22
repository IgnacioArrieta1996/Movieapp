package com.example.movieapp.repository

import com.example.movieapp.application.AppConstants
import com.example.movieapp.data.model.MovieList
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/*
El webservice, va ser el encargado de utilizar retrofit para traer la informacion del servidor
*/

interface WebService {
    //Dicho webService busca la informacion en el servidor

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String): MovieList

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): MovieList

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieList

}

object RetrofitClient {

    /*
    El by lazy, funciona para que cuando llamemos al retrofitclient, va inicializar la variable
    lazy, solamente en el momento que inicialicemos webservice, no lo va inicializar antes.
    */

    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL) //Proporciona la base URL
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())) //Convierte el json del sv en objetos
            .build().create(WebService::class.java) //Instancia con el webservice
    }

}