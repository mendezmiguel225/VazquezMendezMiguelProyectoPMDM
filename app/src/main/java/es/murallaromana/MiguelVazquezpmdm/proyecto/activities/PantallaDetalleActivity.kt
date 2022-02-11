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
                    binding.tvDetalleGenero2.setText(p.genre)
                    binding.tvDetalleDirector2.setText(p.directorFullname)
                    binding.tvDetalleTitulo2.setText(p.title)
                    if(p.rating==null){
                        p.rating=0
                    }
                    binding.tvDetalleValoracion2.setText(p.rating.toString())
                    binding.tvDetalleNumero2.setText(p.directorPhone)
                    binding.tvDetalleDuracion2.setText(p.runtimeMinutes.toString())
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
            if (binding.tvDetalleNumero2.isEnabled == false) {
                var i = Intent(Intent.ACTION_CALL)
                i.setData(Uri.parse("tel:" + binding.tvDetalleNumero2.text.toString().trim()))
                startActivity(i)
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
                val intent =
                    Intent(this,NuevaPeliculaActivity::class.java)
                intent.putExtra("id",id)
                startActivity(intent)
                finish()
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
