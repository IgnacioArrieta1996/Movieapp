package com.example.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.movieapp.core.Resource
import com.example.movieapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception


//De esta manera, solo usamos la implementacion de la interfaz de "MovieRepository"
class MovieViewModel(private val repo: MovieRepository) : ViewModel() {

    fun fetchMainScreenMovies() = liveData(Dispatchers.IO) {
        //El dispatchers.IO es el hilo de ejecucion para correr la corrutina


        //Avisa al usuario que se esta cargando la peticion antes de irla a buscar
        emit(Resource.Loading())

        /*Hacemos las llamadas al sv. Para eso hacemos un try/catch, dentro de este ejecutamos la corrutina
        que nos va traer la informacion (las 3 consultas) del sv.
        */

        try {
            emit(
                Resource.Success(
                    Triple(
                        repo.getUpcomingMovies(),
                        repo.getTopRatedMovies(),
                        repo.getPopularMovies()
                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }


    }


}

/*
    El repo de la instancia del "class MovieViewModel" tiene que venir de algun lado... Por defecto
    las instancias del viewModel tienen que ser vacias, pero viewModel nos provee del factory. Basicamente
    es aclararle al viewModel como se crea esa instancia...
 */

class MovieViewModelFactory(private val repo: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}