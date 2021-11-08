package es.murallaromana.MiguelVazquezpmdm.proyecto.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.murallaromana.MiguelVazquezpmdm.proyecto.R

class PantallaDetalle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_detalle)
        val p=intent.extras?.get("pelicula")
    }
}