package es.murallaromana.MiguelVazquezpmdm.proyecto.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import es.murallaromana.MiguelVazquezpmdm.proyecto.R
import es.murallaromana.MiguelVazquezpmdm.proyecto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bttSign:Button
    private lateinit var bttSiguiente:Button
    private lateinit var tiEmail:TextInputEditText
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bttSign =binding.bttSign
        bttSiguiente=binding.bttSiguiente
        bttSign.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }
        bttSiguiente.setOnClickListener {
            val intent= Intent(this,ListaPeliculas::class.java )
            startActivity(intent)
        }


    }
}