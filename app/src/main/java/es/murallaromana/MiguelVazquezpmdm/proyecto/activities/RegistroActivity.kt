package es.murallaromana.MiguelVazquezpmdm.proyecto.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.graphics.toColor
import com.google.android.material.textfield.TextInputEditText
import es.murallaromana.MiguelVazquezpmdm.proyecto.R
import es.murallaromana.MiguelVazquezpmdm.proyecto.databinding.ActivityPantallaDetalleBinding
import es.murallaromana.MiguelVazquezpmdm.proyecto.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {
    //var sharedPref = getPreferences(MODE_PRIVATE)
    //var editor = sharedPref.edit()
    private  lateinit var binding:ActivityRegistroBinding
    private lateinit var tiCorreo: TextInputEditText
    private lateinit var bttConfirmar: Button
    //editor.putString("email", getEmail.text.toString())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.registro)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
       binding.bttConfirmar.setOnClickListener {
            var sharedPref = this.getSharedPreferences(R.string.file_preferences.toString(),Context.MODE_PRIVATE)
            var editor = sharedPref.edit()
            editor.putString("email",binding.tiCorreo.text.toString()).commit()

            editor.putString("contraseña",binding.tiContrasenha.text.toString()).commit()
            onBackPressed()
        }
    }
}