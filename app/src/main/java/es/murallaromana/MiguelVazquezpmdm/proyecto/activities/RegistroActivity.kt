package es.murallaromana.MiguelVazquezpmdm.proyecto.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.core.graphics.toColor
import com.google.android.material.textfield.TextInputEditText
import es.murallaromana.MiguelVazquezpmdm.proyecto.R
import es.murallaromana.MiguelVazquezpmdm.proyecto.databinding.ActivityPantallaDetalleBinding
import es.murallaromana.MiguelVazquezpmdm.proyecto.databinding.ActivityRegistroBinding


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
            if (binding.tiTelefono.text.toString().trim() == "" || binding.tiContrasenha.text.toString().trim() == "" ||
                binding.tiCorreo.text.toString().trim() == "" || binding.tiNombre.text.toString().trim() == ""
                || binding.tiNombreUs.toString().trim() == ""
            ) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else if (pattern.matcher(binding.tiCorreo.text.toString().trim()).matches() == false) {
                Toast.makeText(this, "Email no válido", Toast.LENGTH_SHORT).show()
            } else if (binding.tiContrasenha.text.toString().trim().length > 8 ||
                binding.tiContrasenha.text.toString().trim().length < 5
            ) {
                Toast.makeText(this, "La contraseña debe tener entre 5 y 8 caracteres", Toast.LENGTH_SHORT).show()
            }else{
                var sharedPref = getSharedPreferences("datos", Context.MODE_PRIVATE)
                var editor = sharedPref.edit()
                editor.putString("email", binding.tiCorreo.text.toString().trim())
                editor.putString("contraseña", binding.tiContrasenha.text.toString().trim()).commit()
                onBackPressed()
            }

        }
    }
}