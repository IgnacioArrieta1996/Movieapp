package com.example.movieapp.core

import java.lang.Exception

//En kotlin existen los sealed class, que sirven para retornar estados

sealed class Resource<out T> {

    class Loading<out T> : Resource<T>()

    //Como tienen un dato para retornar, usamos data class
    data class Success<out T>(val data: T) : Resource<T>()

    //Como tienen un dato para retornar, usamos data class
    data class Failure(val exception: Exception) : Resource<Nothing>()

}