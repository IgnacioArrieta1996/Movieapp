package com.example.movieapp.repository

import com.example.movieapp.data.model.MovieList

/*

Dentro de la capa repository, se va encontrar solamente la firma de los metodos y la implementacion
de donde ir a buscar la informacion. Solamente se crean los metodos que luego van a ser implementados
para luego utilizar la logica de negocio en la capa de datos. Practicamente, vamos a llamar la logica
de negocio desde el repositorio.

 */

/*
[suspend] ...
Una corrutina es una funcion que tiene una caracteristica que la hace "suspendable", una funcion
suspendida. Es decir, que se suspende la funcion hasta que no realiza una accion (como buscar un valor
a un servidor). Es decir, no finaliza su ejecucion, una vez que se trae el valor del servidor se hace
un resum de esa funcion (se dan los datos y continua)
 */


interface MovieRepository {

    suspend fun getUpcomingMovies(): MovieList
    suspend fun getTopRatedMovies(): MovieList
    suspend fun getPopularMovies(): MovieList

}