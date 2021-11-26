package es.murallaromana.MiguelVazquezpmdm.proyecto.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
            val contra = sharedPref.getString("datos","datos")
            val email = sharedPref.getString("datos","datos")
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


    }
}