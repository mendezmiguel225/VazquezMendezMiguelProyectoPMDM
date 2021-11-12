package es.murallaromana.MiguelVazquezpmdm.proyecto.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.squareup.picasso.Picasso
import es.murallaromana.MiguelVazquezpmdm.proyecto.R
import es.murallaromana.MiguelVazquezpmdm.proyecto.databinding.ActivityPantallaDetalleBinding
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.Pelicula

class PantallaDetalle : AppCompatActivity() {
    lateinit var binding: ActivityPantallaDetalleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val p = intent.extras?.get("pelicula")
        setContentView(R.layout.activity_pantalla_detalle)
        binding = ActivityPantallaDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        p as Pelicula
        Picasso.get().load(p.url).into(binding.ivImagen)
        binding.tvDetalleGenero2.setText(p.genero)
        binding.tvDetalleDirector2.setText(p.director)
        binding.tvDetalleTitulo2.setText(p.titulo)
        binding.tvDetalleValoracion2.setText(p.valoracion)
        binding.tvDetalleNumero2.setText(p.numeroDirector)
        binding.tvDetalleNumero
    }
}