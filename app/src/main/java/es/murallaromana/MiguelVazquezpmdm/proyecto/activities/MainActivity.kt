package es.murallaromana.MiguelVazquezpmdm.proyecto.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import es.murallaromana.MiguelVazquezpmdm.proyecto.R
import es.murallaromana.MiguelVazquezpmdm.proyecto.databinding.ActivityMainBinding
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.dao.retrofit.ServicioApi
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
        setTitle("Login")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val u = User("miguel@gmail.com", "1234")

        binding.bttRegistro.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
        binding.bttSiguiente.setOnClickListener {
            val sharedPref: SharedPreferences = getSharedPreferences("datos", MODE_PRIVATE)
            val contra = sharedPref.getString("contraseña", "datos")
            val email = sharedPref.getString("email", "datos")
            if (email != binding.tiEmail.text.toString().trim()) {
                Toast.makeText(this, "Email incorrecto", Toast.LENGTH_SHORT).show()
            } else if (contra != binding.tiContrasenha.text.toString().trim()) {
                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, ListaPeliculasActivity::class.java)
                startActivity(intent)
                finish()
            }

        }

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://damapi.herokuapp.com/api/v1/")
            .build()
        val servicio: ServicioApi = retrofit.create(ServicioApi::class.java)
        val loginCall = servicio.login(u)

        loginCall.enqueue(object : Callback<Token> {


            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                Log.d("MainActivity",response.body().toString())
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }
}