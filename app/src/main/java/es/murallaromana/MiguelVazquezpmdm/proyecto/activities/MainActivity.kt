package es.murallaromana.MiguelVazquezpmdm.proyecto.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import es.murallaromana.MiguelVazquezpmdm.proyecto.R
import es.murallaromana.MiguelVazquezpmdm.proyecto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTitle("Login")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bttRegistro.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
        binding.bttSiguiente.setOnClickListener {
            val sharedPref: SharedPreferences = getSharedPreferences("datos", MODE_PRIVATE)
            val contra = sharedPref.getString("contraseña","datos")
            val email = sharedPref.getString("email","datos")
            if (email != binding.tiEmail.text.toString()) {
                Toast.makeText(this, "Email incorrecto", Toast.LENGTH_SHORT).show()
            } else if (contra != binding.tiContrasenha.text.toString()) {
                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, ListaPeliculasActivity::class.java)
                startActivity(intent)
            }

        }


    }
}