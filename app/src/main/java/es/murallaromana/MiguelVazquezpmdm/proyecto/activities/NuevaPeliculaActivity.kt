package es.murallaromana.MiguelVazquezpmdm.proyecto.activities

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import es.murallaromana.MiguelVazquezpmdm.proyecto.R
import es.murallaromana.MiguelVazquezpmdm.proyecto.databinding.ActivityNuevaPeliculaBinding
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.dao.retrofit.ServicioApi
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.Pelicula
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class NuevaPeliculaActivity : AppCompatActivity() {
    lateinit var binding:ActivityNuevaPeliculaBinding

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_pelicula)
        binding = ActivityNuevaPeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("Nueva película")



        binding.bttCancelar.setOnClickListener(){
            this.onBackPressed()
        }

        binding.bttCrear.setOnClickListener(){

            if(binding.tiTitulo.text.toString().trim()==""||binding.tiGenero.text.toString().trim()==""||binding.tiDirector.text.toString().trim()==""||
                binding.tiNumeroD.text.toString().trim()==""||binding.tiURL.text.toString().trim()==""||binding.tiValoracion.text.toString().trim()==""||binding.tiDirector.text.toString().trim()==""){
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }else{
                //Retrofit
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://damapi.herokuapp.com/api/v1/")
                    .build()

                val servicio: ServicioApi = retrofit.create(ServicioApi::class.java)

                //Crear película
                val shared: SharedPreferences = getSharedPreferences("datos", MODE_PRIVATE)
                val pelicula:Pelicula= Pelicula(null,
                    binding.tiTitulo.text.toString(),
                    binding.tiValoracion.text.toString().toInt(),
                    binding.tiDuracion.text.toString().toInt(),
                    binding.tiGenero.text.toString(),
                    binding.tiDirector.text.toString(),
                    binding.tiURL.text.toString(),
                    binding.tiNumeroD.text.toString()
                )
                val create=servicio.create(pelicula,"Bearer "+shared.getString("token","").toString())
                create.enqueue(object :Callback<Unit>{
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                       if(response.isSuccessful){
                           Toast.makeText(
                               this@NuevaPeliculaActivity,
                               "Película creada",
                               Toast.LENGTH_SHORT
                           ).show()
                           finish()
                       }else{
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
}