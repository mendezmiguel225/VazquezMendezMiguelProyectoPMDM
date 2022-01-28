package es.murallaromana.MiguelVazquezpmdm.proyecto.model.dao.retrofit

import android.media.session.MediaSession
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.dao.PeliculasDao
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.Pelicula
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.Token
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServicioApi {

    @GET("movies")
    fun getAll(): Call<List<Pelicula>>

    @POST("users/login")
    fun login(@Body user: User): Call<Token>

    @POST("users/signup")
    fun signUp(@Body user: User):Call<Unit>
}