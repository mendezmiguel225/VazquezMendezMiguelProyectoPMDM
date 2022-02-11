package es.murallaromana.MiguelVazquezpmdm.proyecto.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.toColor
import com.google.android.material.textfield.TextInputEditText
import es.murallaromana.MiguelVazquezpmdm.proyecto.R
import es.murallaromana.MiguelVazquezpmdm.proyecto.databinding.ActivityPantallaDetalleBinding
import es.murallaromana.MiguelVazquezpmdm.proyecto.databinding.ActivityRegistroBinding
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.dao.retrofit.ServicioApi
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.Token
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RegistroActivity : AppCompatActivity() {
    //var sharedPref = getPreferences(MODE_PRIVATE)
    //var editor = sharedPref.edit()
    private lateinit var binding: ActivityRegistroBinding

    //editor.putString("email", getEmail.text.toString())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.registro)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pattern = Patterns.EMAIL_ADDRESS
        binding.bttConfirmar.setOnClickListener {
            binding.bttConfirmar.isEnabled=false
            val user = User(binding.tiCorreo.text.toString(), binding.tiClave.text.toString())

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://damapi.herokuapp.com/api/v1/")
                .build()
            val servicio: ServicioApi = retrofit.create(ServicioApi::class.java)
            val signUpCall = servicio.signUp(user)
            signUpCall.enqueue(object : Callback<Unit> {
                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.d("respuesta: onFailure", t.toString())
                }

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (!response.isSuccessful) {
                        Toast.makeText(
                            this@RegistroActivity,
                            "No se pudo crear el usuario",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.bttConfirmar.isEnabled=true
                    } else {
                        Toast.makeText(this@RegistroActivity, "Usuario creado", Toast.LENGTH_SHORT)
                            .show()
                        onBackPressed()
                    }
                }
            })

        }
    }
}