package es.murallaromana.MiguelVazquezpmdm.proyecto.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import es.murallaromana.MiguelVazquezpmdm.proyecto.R
import es.murallaromana.MiguelVazquezpmdm.proyecto.adapters.ListaPeliculasAdapter
import es.murallaromana.MiguelVazquezpmdm.proyecto.databinding.ActivityListaPeliculasBinding
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.dao.retrofit.ServicioApi
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.Pelicula
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListaPeliculasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListaPeliculasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTitle("Lista de películas")
        super.onCreate(savedInstanceState)

        binding = ActivityListaPeliculasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //RETROFIT

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://damapi.herokuapp.com/api/v1/")
            .build()

        val servicio: ServicioApi = retrofit.create(ServicioApi::class.java)
        val shared: SharedPreferences = getSharedPreferences("datos", MODE_PRIVATE)
        val getAllCall = servicio.getAll("Bearer " + shared.getString("token", "").toString())
        getAllCall.enqueue(object : Callback<List<Pelicula>> {
            override fun onResponse(
                call: Call<List<Pelicula>>,
                response: Response<List<Pelicula>>
            ) {
                if (response.isSuccessful) {
                    var peliculas = response.body()!!
                    val layoutManager = GridLayoutManager(this@ListaPeliculasActivity, 2)

                    val adapter = ListaPeliculasAdapter(this@ListaPeliculasActivity, peliculas)
                    binding.rvListaPeliculas.adapter = adapter
                    binding.rvListaPeliculas.layoutManager = layoutManager
                    val dividerItemDecoration = DividerItemDecoration(
                        binding.rvListaPeliculas.context,
                        layoutManager.orientation
                    )
                } else {
                    Toast.makeText(
                        this@ListaPeliculasActivity,
                        "No se ha podido acceder a la lista de películas",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Pelicula>>, t: Throwable) {
                Log.d("respuesta: onFailure", t.toString())
            }
        })



        binding.fabEngadir.setOnClickListener {
            val intent = Intent(this, NuevaPeliculaActivity::class.java)
            intent.putExtra("id","")
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://damapi.herokuapp.com/api/v1/")
            .build()

        val servicio: ServicioApi = retrofit.create(ServicioApi::class.java)
        val shared: SharedPreferences = getSharedPreferences("datos", MODE_PRIVATE)
        val getAllCall = servicio.getAll("Bearer " + shared.getString("token", "").toString())
        getAllCall.enqueue(object : Callback<List<Pelicula>> {
            override fun onResponse(
                call: Call<List<Pelicula>>,
                response: Response<List<Pelicula>>
            ) {
                if (response.isSuccessful) {
                    var peliculas = response.body()!!
                    val layoutManager = GridLayoutManager(this@ListaPeliculasActivity, 2)
                    val adapter = ListaPeliculasAdapter(this@ListaPeliculasActivity, peliculas)
                    binding.rvListaPeliculas.adapter = adapter
                    binding.rvListaPeliculas.layoutManager = layoutManager

                } else {
                    Toast.makeText(
                        this@ListaPeliculasActivity,
                        "No se ha podido acceder a la lista de películas",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Pelicula>>, t: Throwable) {
                Log.d("respuesta: onFailure", t.toString())
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_deslog, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_deslog -> {
                val builder = AlertDialog.Builder(this)

                builder.setTitle("Cerrar sesión")
                    .setMessage("Se cerrara la sesión actual, ¿está seguro?")
                    .setPositiveButton("Aceptar") { _, _ ->
                        val shared: SharedPreferences= getSharedPreferences("datos", MODE_PRIVATE)
                        shared.edit().clear().commit()
                        val intent = Intent(this,MainActivity::class.java )
                        startActivity(intent)
                        finish()

            }.setNegativeButton("Cancelar", null)
                    .show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        }
    }

