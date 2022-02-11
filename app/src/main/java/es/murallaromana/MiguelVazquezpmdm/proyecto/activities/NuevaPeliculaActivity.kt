package es.murallaromana.MiguelVazquezpmdm.proyecto.activities

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.squareup.picasso.Picasso
import es.murallaromana.MiguelVazquezpmdm.proyecto.R
import es.murallaromana.MiguelVazquezpmdm.proyecto.databinding.ActivityNuevaPeliculaBinding
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.dao.retrofit.ServicioApi
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.Pelicula
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class NuevaPeliculaActivity : AppCompatActivity() {
    lateinit var binding:ActivityNuevaPeliculaBinding
    lateinit var  id:String
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        id = intent.extras?.get("id").toString()
        setContentView(R.layout.activity_nueva_pelicula)
        binding = ActivityNuevaPeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (id != "") {
            //Si el id no  es "" significa que estamos editando
            binding.bttCrear.setText("Actualizar")
            var p: Pelicula
            //RETROFIT
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://damapi.herokuapp.com/api/v1/")
                .build()
            val servicio: ServicioApi = retrofit.create(ServicioApi::class.java)

            val shared: SharedPreferences = getSharedPreferences("datos", MODE_PRIVATE)
            val getById = servicio.getById(id, "Bearer " + shared.getString("token", "").toString())
            getById.enqueue(object : Callback<Pelicula> {
                override fun onResponse(call: Call<Pelicula>, response: Response<Pelicula>) {
                    if (response.isSuccessful) {
                        p = response.body()!!
                        setTitle(p.title)
                        binding.tiTitulo.setText(p.title)
                        binding.tiValoracion.setText(p.rating.toString())
                        binding.tiDuracion.setText(p.runtimeMinutes.toString())
                        binding.tiGenero.setText(p.genre)
                        binding.tiDirector.setText(p.directorFullname)
                        binding.tiURL.setText(p.imageUrl)
                        binding.tiNumeroD.setText(p.directorPhone)

                        binding.bttCrear.setOnClickListener() {
                            if (binding.tiTitulo.text.toString()
                                    .trim() == "" || binding.tiGenero.text.toString()
                                    .trim() == "" || binding.tiDirector.text.toString().trim() == "" ||
                                binding.tiNumeroD.text.toString().trim() == "" || binding.tiURL.text.toString()
                                    .trim() == "" || binding.tiValoracion.text.toString()
                                    .trim() == "" || binding.tiDirector.text.toString().trim() == ""
                            ) {
                                Toast.makeText(this@NuevaPeliculaActivity, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                            } else {
                                p.title=binding.tiTitulo.text.toString()
                                p.rating=binding.tiValoracion.text.toString().toDouble()
                                p.runtimeMinutes=binding.tiDuracion.text.toString().toDouble()
                                p.genre=binding.tiGenero.text.toString()
                                p.directorFullname=binding.tiDirector.text.toString()
                                p.imageUrl=binding.tiURL.text.toString()
                                p.directorPhone=binding.tiNumeroD.text.toString()
                               val update = servicio.update("Bearer "+shared.getString("token", "").toString(),p )
                                update.enqueue(object : Callback<Unit>{
                                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                                        if(response.isSuccessful){
                                            Toast.makeText(this@NuevaPeliculaActivity, "Película actualizada", Toast.LENGTH_SHORT).show()
                                            finish()
                                        }else{
                                            Toast.makeText(this@NuevaPeliculaActivity, "No se pudo actualizar", Toast.LENGTH_SHORT).show()
                                            finish()
                                        }
                                    }

                                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                                        Log.d("respuesta: onFailure", t.toString())
                                    }

                                })
                            }
                        }

                    } else {

                        Toast.makeText(
                            this@NuevaPeliculaActivity,
                            "No se ha podido acceder a la película",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                }

                override fun onFailure(call: Call<Pelicula>, t: Throwable) {
                    Log.d("respuesta: onFailure", t.toString())
                }

            })

        } else {
            //Si el id es igual "" estamos creando una nueva película
            setTitle("Nueva película")


            binding.bttCrear.setOnClickListener() {
                if (binding.tiTitulo.text.toString()
                        .trim() == "" || binding.tiGenero.text.toString()
                        .trim() == "" || binding.tiDirector.text.toString().trim() == "" ||
                    binding.tiNumeroD.text.toString().trim() == "" || binding.tiURL.text.toString()
                        .trim() == "" || binding.tiValoracion.text.toString()
                        .trim() == "" || binding.tiDirector.text.toString().trim() == ""
                ) {
                    Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                } else {
                    //Retrofit
                    val retrofit = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("https://damapi.herokuapp.com/api/v1/")
                        .build()

                    val servicio: ServicioApi = retrofit.create(ServicioApi::class.java)

                    //Crear película
                    val shared: SharedPreferences = getSharedPreferences("datos", MODE_PRIVATE)
                    val pelicula: Pelicula = Pelicula(
                        null,
                        binding.tiTitulo.text.toString(),
                        binding.tiValoracion.text.toString().toInt(),
                        binding.tiDuracion.text.toString().toDouble(),
                        binding.tiGenero.text.toString(),
                        binding.tiDirector.text.toString(),
                        binding.tiURL.text.toString(),
                        binding.tiNumeroD.text.toString()
                    )
                    val create = servicio.create(
                        pelicula,
                        "Bearer " + shared.getString("token", "").toString()
                    )
                    create.enqueue(object : Callback<Unit> {
                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                            if (response.isSuccessful) {
                                Toast.makeText(
                                    this@NuevaPeliculaActivity,
                                    "Película creada",
                                    Toast.LENGTH_SHORT
                                ).show()
                                finish()
                            } else {
                                Toast.makeText(
                                    this@NuevaPeliculaActivity,
                                    "No se ha podido crear la nueva película",
                                    Toast.LENGTH_SHORT
                                ).show()
                                finish()
                            }
                        }

                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            Log.d("respuesta: onFailure", t.toString())
                        }

                    })
                    finish()
                }
            }
        }
        binding.bttCancelar.setOnClickListener() {
            this.onBackPressed()
        }
    }
}