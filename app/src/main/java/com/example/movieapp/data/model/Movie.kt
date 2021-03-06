package com.example.movieapp.data.model

import androidx.constraintlayout.utils.widget.MockView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Movie(

    val id: Int = -1,
    /*El serialzedname, nos permite ponerle un nombre diferente a la variable al que se encuentra
    en la base de datos o servidor, es decir, en este caso, por mas que la variable en nuestra clase
    se llame adulto, va funcionar igual porque dentro del serializedname se encuentra el nombre original
    que posee la db.
    */
    @SerializedName("adult")
    val adulto: Boolean = false,
    val backdrop_path: String = "",
    val original_title: String = "",
    val original_language: String = "",
    val overview: String = "",
    val popularity: Double = -1.0,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = -1.0,
    val vote_count: Int = -1,
    val movie_type: String = ""
)

data class MovieList(val results: List<Movie> = listOf())

//Room

@Entity
data class MovieEntity(

    @PrimaryKey
    val id: Int = -1,
    @ColumnInfo(name = "adult")
    val adult: Boolean = false,
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String = "",
    @ColumnInfo(name = "original_title")
    val original_title: String = "",
    @ColumnInfo(name = "original_language")
    val original_language: String = "",
    @ColumnInfo(name = "overview")
    val overview: String = "",
    @ColumnInfo(name = "popularity")
    val popularity: Double = -1.0,
    @ColumnInfo(name = "poster_path")
    val poster_path: String = "",
    @ColumnInfo(name = "release_date")
    val release_date: String = "",
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "video")
    val video: Boolean = false,
    @ColumnInfo(name = "vote_average")
    val vote_average: Double = -1.0,
    @ColumnInfo(name = "vote_count")
    val vote_count: Int = -1,

    //Me va indicar que tipo de pelicula es cuando lo inserte en la DB
    @ColumnInfo(name = "movie_type")
    val movie_type: String = ""
)


/*
Extension Functions que para cualquier lista de tipo "MovieEntity" se pueda transformar en
"MovieList"
*/

fun List<MovieEntity>.toMovieList(): MovieList {

    val resultList = mutableListOf<Movie>()
    this.forEach { movieEntity ->
        resultList.add(movieEntity.toMovie())
    }
    return MovieList(resultList)
}

/*
Basicamente, transformamos una pelicula de MovieEntity a Movie para luego almacenarlas en la
"MovieList" o dicho de otra forma, creamos una Movie en base a los atributos de MovieEntity
*/

fun MovieEntity.toMovie(): Movie = Movie(
    this.id,
    this.adult,
    this.backdrop_path,
    this.original_title,
    this.original_language,
    this.overview,
    this.popularity,
    this.poster_path,
    this.release_date,
    this.title,
    this.video,
    this.vote_average,
    this.vote_count,
    this.movie_type
)


// Extension function que me transforma una pelicula en un MovieEntity
fun Movie.toMovieEntity(movie_type: String): MovieEntity = MovieEntity(
    this.id,
    this.adulto,
    this.backdrop_path,
    this.original_title,
    this.original_language,
    this.overview,
    this.popularity,
    this.poster_path,
    this.release_date,
    this.title,
    this.video,
    this.vote_average,
    this.vote_count,
    movie_type = movie_type
)