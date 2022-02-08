package es.murallaromana.MiguelVazquezpmdm.proyecto.activities

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.squareup.picasso.Picasso
import es.murallaromana.MiguelVazquezpmdm.proyecto.R
import es.murallaromana.MiguelVazquezpmdm.proyecto.databinding.ActivityPantallaDetalleBinding
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.dao.retrofit.ServicioApi
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.Pelicula
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PantallaDetalleActivity : AppCompatActivity() {
    lateinit var binding: ActivityPantallaDetalleBinding
    lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = intent.extras?.get("id") as String
        setContentView(R.layout.activity_pantalla_detalle)
        binding = ActivityPantallaDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
                    Picasso.get().load(p.imageUrl).into(binding.ivImagen)
                    binding.etDetalleGenero2.setText(p.genre)
                    binding.etDetalleDirector2.setText(p.directorFullname)
                    binding.etDetalleTitulo2.setText(p.title)
                    binding.etDetalleValoracion2.setText(p.rating.toString())
                    binding.etDetalleNumero2.setText(p.directorPhone)
                } else {
                    Toast.makeText(
                        this@PantallaDetalleActivity,
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

        binding.tvDetalleNumero.setOnClickListener() {
            if (binding.etDetalleNumero2.isEnabled == false) {
                var i = Intent(Intent.ACTION_CALL)
                i.setData(Uri.parse("tel:" + binding.etDetalleNumero2.text.toString().trim()))
                startActivity(i)
            }

        }
        binding.bttAtras.setOnClickListener() {
            binding.etDetalleGenero2.isEnabled = false
            binding.etDetalleDirector2.isEnabled = false
            binding.etDetalleTitulo2.isEnabled = false
            binding.etDetalleValoracion2.isEnabled = false
            binding.etDetalleNumero2.isEnabled = false
            binding.bttCorrecto.isVisible = false
            binding.bttAtras.isVisible = false
            binding.ivImagen.isVisible = true
        }
        binding.bttCorrecto.setOnClickListener() {
            if (binding.etDetalleGenero2.text.toString()
                    .trim() == "" || binding.etDetalleDirector2.text.toString()
                    .trim() == "" || binding.etDetalleTitulo2.text.toString().trim() == "" ||
                binding.etDetalleValoracion2.text.toString()
                    .trim() == "" || binding.etDetalleNumero2.text.toString()
                    .trim() == ""
            ) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                /**
                MiApp.peliculas.get(p.id).directorFirstName =
                binding.etDetalleDirector2.text.toString().trim()
                MiApp.peliculas.get(p.id).title=
                binding.etDetalleTitulo2.text.toString().trim()
                //MiApp.peliculas.get(p.id).valoracion =
                //  binding.etDetalleValoracion2.text.toString().trim()
                MiApp.peliculas.get(p.id).genre =
                binding.etDetalleGenero2.text.toString().trim()
                MiApp.peliculas.get(p.id).directorFirstName =
                binding.etDetalleNumero2.text.toString().trim()
                Toast.makeText(this, "Película editada correctamente.", Toast.LENGTH_SHORT)
                .show()
                finish()
                }
                 **/
            }


        }

    }
        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_botones, menu)

        return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
        R.id.action_edit -> {
        binding.etDetalleGenero2.isEnabled = true
        binding.etDetalleDirector2.isEnabled = true
        binding.etDetalleTitulo2.isEnabled = true
        binding.etDetalleValoracion2.isEnabled = true
        binding.etDetalleNumero2.isEnabled = true
        binding.bttCorrecto.isVisible = true
        binding.bttAtras.isVisible = true

        binding.ivImagen.isVisible = false

        true

        }

        R.id.action_delete -> {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Eliminar película")
        .setMessage("La película seleccionada va a ser eliminada, ¿está seguro?")
        .setPositiveButton("Aceptar") { _, _ ->
            //RETROFIT
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://damapi.herokuapp.com/api/v1/")
                .build()
            val servicio: ServicioApi = retrofit.create(ServicioApi::class.java)
            val shared: SharedPreferences = getSharedPreferences("datos", MODE_PRIVATE)
            val delete=servicio.delete(id, "Bearer "+shared.getString("token","").toString())
            delete.enqueue(object: Callback<Unit>{
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if(response.isSuccessful){
                        Toast.makeText(this@PantallaDetalleActivity, "Película eliminada.", Toast.LENGTH_SHORT).show()
                        finish()
                    }else{
                        Toast.makeText(this@PantallaDetalleActivity, "No se ha podido eliminar la película", Toast.LENGTH_SHORT).show()
                        finish()
                    }

                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.d("respuesta: onFailure", t.toString())
                }

            })

        }.setNegativeButton("Cancelar", null)
        .show()
        true
        }
        else -> super.onOptionsItemSelected(item)
        }

    }

}
