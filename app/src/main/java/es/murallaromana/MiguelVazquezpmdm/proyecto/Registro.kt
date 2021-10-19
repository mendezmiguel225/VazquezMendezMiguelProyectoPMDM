package es.murallaromana.MiguelVazquezpmdm.proyecto

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class Registro : AppCompatActivity() {
    //var sharedPref = getPreferences(MODE_PRIVATE)
    //var editor = sharedPref.edit()
    private lateinit var tiCorreo: TextInputEditText
    private lateinit var bttConfirmar: Button
    //editor.putString("email", getEmail.text.toString())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        bttConfirmar = findViewById(R.id.bttConfirmar)
        tiCorreo = findViewById(R.id.tiCorreo)
        bttConfirmar.setOnClickListener {
            var sharedPref = getPreferences(MODE_PRIVATE)
            var editor = sharedPref.edit()
            editor.putString("email",tiCorreo.toString())
            onBackPressed()
        }
    }


}