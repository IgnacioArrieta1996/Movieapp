package com.example.movieapp.core

import kotlinx.coroutines.coroutineScope
import java.io.IOException
import java.lang.Exception
import java.net.InetSocketAddress
import java.net.Socket

object InternetCheck {

    /*
    * La coroutineScope lo proporciona corrutinas y es un scope en la que vamos a ejecutar corrutinas
    * u operaciones asincronas y luego nos van a retornar un valor
    */
    suspend fun isNetworkAvailable() = coroutineScope {
        return@coroutineScope try {
            val sock = Socket()
            val socketAddress = InetSocketAddress("8.8.8.8", 53)
            sock.connect(socketAddress, 2000)
            sock.close()
            true
        } catch (e: IOException) {
            false
        }
    }

}