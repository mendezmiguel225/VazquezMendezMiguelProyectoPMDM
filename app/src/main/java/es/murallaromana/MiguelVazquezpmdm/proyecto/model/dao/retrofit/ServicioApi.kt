package es.murallaromana.MiguelVazquezpmdm.proyecto.model.dao.retrofit


import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.Pelicula
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.Token
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.User
import retrofit2.Call
import retrofit2.http.*

interface ServicioApi {

    @GET("movies")
    fun getAll(@Header("Authorization") token: String): Call<List<Pelicula>>

    @POST("users/login")
    fun login(@Body user: User): Call<Token>

    @POST("users/signup")
    fun signUp(@Body user: User): Call<Unit>

    @POST("movies")
    fun create(@Body pelicula: Pelicula, @Header("Authorization") token: String): Call<Unit>

    @GET("movies/{id}")
    fun getById(@Path("id") id: String, @Header("Authorization") token: String): Call<Pelicula>

    @DELETE("movies/{id}")
    fun delete(@Path("id") id: String, @Header("Authorization") token: String): Call<Unit>

    @PUT("movies/")
    fun update(
        @Header("Authorization") token: String,
        @Body pelicula: Pelicula
    ): Call<Unit>
}