package es.murallaromana.MiguelVazquezpmdm.proyecto.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.squareup.picasso.Picasso
import es.murallaromana.MiguelVazquezpmdm.proyecto.R
import es.murallaromana.MiguelVazquezpmdm.proyecto.databinding.ActivityPantallaDetalleBinding
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.Pelicula

class PantallaDetalleActivity : AppCompatActivity() {
    lateinit var binding: ActivityPantallaDetalleBinding
    lateinit var p: Pelicula

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        p = intent.extras?.get("pelicula") as Pelicula
        setContentView(R.layout.activity_pantalla_detalle)
        binding = ActivityPantallaDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle(p.title)
        Picasso.get().load(p.imageUrl).into(binding.ivImagen)
        binding.etDetalleGenero2.setText(p.genre)
        binding.etDetalleDirector2.setText(p.directorFullname)
        binding.etDetalleTitulo2.setText(p.title)
        //binding.etDetalleValoracion2.setText(p.valoracion)
        binding.etDetalleNumero2.setText(p.directorPhone)

        binding.tvDetalleNumero.setOnClickListener() {
            if(binding.etDetalleNumero2.isEnabled==false){
                var i =  Intent(Intent.ACTION_CALL)
                i.setData(Uri.parse("tel:"+binding.etDetalleNumero2.text.toString().trim()))
                startActivity(i)
            }

        }
        binding.bttAtras.setOnClickListener() {
            binding.etDetalleGenero2.isEnabled = false
            binding.etDetalleDirector2.isEnabled = false
            binding.etDetalleTitulo2.isEnabled = false
            binding.etDetalleValoracion2.isEnabled = false
            binding.etDetalleNumero2.isEnabled = false
            binding.bttCorrecto.isVisible = false
            binding.bttAtras.isVisible = false
            binding.ivImagen.isVisible = true
        }
        binding.bttCorrecto.setOnClickListener() {
            if (binding.etDetalleGenero2.text.toString()
                    .trim() == "" || binding.etDetalleDirector2.text.toString()
                    .trim() == "" || binding.etDetalleTitulo2.text.toString().trim() == "" ||
                binding.etDetalleValoracion2.text.toString()
                    .trim() == "" || binding.etDetalleNumero2.text.toString()
                    .trim() == ""
            ) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
/**
                MiApp.peliculas.get(p.id).directorFirstName =
                    binding.etDetalleDirector2.text.toString().trim()
                MiApp.peliculas.get(p.id).title=
                    binding.etDetalleTitulo2.text.toString().trim()
                //MiApp.peliculas.get(p.id).valoracion =
                  //  binding.etDetalleValoracion2.text.toString().trim()
                MiApp.peliculas.get(p.id).genre =
                    binding.etDetalleGenero2.text.toString().trim()
                MiApp.peliculas.get(p.id).directorFirstName =
                    binding.etDetalleNumero2.text.toString().trim()
                Toast.makeText(this, "Película editada correctamente.", Toast.LENGTH_SHORT)
                    .show()
                finish()
            }
**/
        }


    }
        /**

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_botones, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                binding.etDetalleGenero2.isEnabled = true
                binding.etDetalleDirector2.isEnabled = true
                binding.etDetalleTitulo2.isEnabled = true
                binding.etDetalleValoracion2.isEnabled = true
                binding.etDetalleNumero2.isEnabled = true
                binding.bttCorrecto.isVisible = true
                binding.bttAtras.isVisible = true

                binding.ivImagen.isVisible = false

                true

            }

            R.id.action_delete -> {
                val builder = AlertDialog.Builder(this)

                builder.setTitle("Eliminar película")
                    .setMessage("La película seleccionada va a ser eliminada, ¿está seguro?")
                    .setPositiveButton("Aceptar") { _, _ ->
                        MiApp.peliculas.removeAt(p.id)
                        MiApp.actualizarIndice()
                        Toast.makeText(this, "Película eliminada.", Toast.LENGTH_SHORT).show()
                        finish()
                    }.setNegativeButton("Cancelar", null)
                    .show()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    **/
    }

}