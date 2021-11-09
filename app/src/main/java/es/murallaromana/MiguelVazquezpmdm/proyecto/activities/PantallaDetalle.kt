package es.murallaromana.MiguelVazquezpmdm.proyecto.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import es.murallaromana.MiguelVazquezpmdm.proyecto.R
import es.murallaromana.MiguelVazquezpmdm.proyecto.databinding.ActivityPantallaDetalleBinding
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.Pelicula

class PantallaDetalle : AppCompatActivity() {
    lateinit var  binding: ActivityPantallaDetalleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val p=intent.extras?.get("pelicula")
        if(p!=null){
            setContentView(R.layout.activity_pantalla_detalle)
            binding = ActivityPantallaDetalleBinding.inflate(layoutInflater)
            p as Pelicula


        }else{

        }


    }
}