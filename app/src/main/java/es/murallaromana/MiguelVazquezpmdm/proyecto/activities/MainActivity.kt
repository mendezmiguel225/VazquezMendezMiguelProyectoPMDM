package es.murallaromana.MiguelVazquezpmdm.proyecto.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.edit
import es.murallaromana.MiguelVazquezpmdm.proyecto.R
import es.murallaromana.MiguelVazquezpmdm.proyecto.databinding.ActivityMainBinding
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.dao.retrofit.ServicioApi
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.Pelicula
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.Token
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //RETROFIT
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://damapi.herokuapp.com/api/v1/")
            .build()
        val servicio: ServicioApi = retrofit.create(ServicioApi::class.java)

        //AUTENTICACIÓN DE TOKEN AL INICIAR LA APLICACIÓN
        val shared: SharedPreferences = getSharedPreferences("datos", MODE_PRIVATE)
        var autenticacion = shared.getString("token","").toString()

        if (autenticacion!="") {
            val getAllCall = servicio.getAll("Bearer "+autenticacion)
            getAllCall.enqueue(object : Callback<List<Pelicula>> {
                override fun onResponse(
                    call: Call<List<Pelicula>>,
                    response: Response<List<Pelicula>>
                ) {
                    if (response.isSuccessful) {
                        val intent =
                            Intent(this@MainActivity,ListaPeliculasActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "No se ha podido acceder a la lista de películas",
                            Toast.LENGTH_SHORT
                        ).show()
                        shared.edit().clear().commit()

                        startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) )
                    }
                }

                override fun onFailure(call: Call<List<Pelicula>>, t: Throwable) {
                    Log.d("respuesta: onFailure", t.toString())
                }
            })

        } else {

            //LOGIN
            setTitle("Login")
            setContentView(R.layout.activity_main)

            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.bttRegistro.setOnClickListener {
                val intent = Intent(this, RegistroActivity::class.java)
                startActivity(intent)
            }

            binding.bttSiguiente.setOnClickListener {

                val u = User(binding.tiEmail.text.toString(), binding.tiContrasenha.text.toString())
                val loginCall = servicio.login(u)

                loginCall.enqueue(object : Callback<Token> {
                    override fun onResponse(call: Call<Token>, response: Response<Token>) {
                        if (!response.isSuccessful) {
                            Toast.makeText(
                                this@MainActivity,
                                "No se ha podido crear el usuario",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {
                            val token = response.body()?.token
                            Log.d("respuesta: token:", token.orEmpty())

                            Toast.makeText(
                                this@MainActivity,
                                "se ha creado el usuario",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            var sharedPref = getSharedPreferences("datos", Context.MODE_PRIVATE)
                            var editor = sharedPref.edit()
                            editor.putString("token", token).commit()
                            val intent =
                                Intent(this@MainActivity,ListaPeliculasActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<Token>, t: Throwable) {
                        Log.d("respuesta: onFailure", t.toString())

                    }
                })

            }

        }

    }

}