package es.murallaromana.MiguelVazquezpmdm.proyecto.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.squareup.picasso.Picasso
import es.murallaromana.MiguelVazquezpmdm.proyecto.R
import es.murallaromana.MiguelVazquezpmdm.proyecto.databinding.ActivityPantallaDetalleBinding
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.Pelicula

class PantallaDetalleActivity : AppCompatActivity() {
    lateinit var binding: ActivityPantallaDetalleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val p = intent.extras?.get("pelicula")
        setContentView(R.layout.activity_pantalla_detalle)
        binding = ActivityPantallaDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        p as Pelicula
        setTitle(p.titulo)
        Picasso.get().load(p.url).into(binding.ivImagen)
        binding.tvDetalleGenero2.setText(p.genero)
        binding.tvDetalleDirector2.setText(p.director)
        binding.tvDetalleTitulo2.setText(p.titulo)
        binding.tvDetalleValoracion2.setText(p.valoracion)
        binding.tvDetalleNumero2.setText(p.numeroDirector)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_botones, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit-> {
                Toast.makeText(this, "Personaje guardado correctamente.", Toast.LENGTH_SHORT).show()
                finish()
                true
            }

            R.id.action_delete -> {
                val builder = AlertDialog.Builder(this)

                builder.setTitle("Eliminar personaje")
                    .setMessage("La película seleccionada va a ser eliminada, ¿está seguro?")
                    .setPositiveButton("Aceptar") { _, _ ->
                        Toast.makeText(this, "Personaje eliminado.", Toast.LENGTH_SHORT).show()
                        finish()
                    }.setNegativeButton("Cancelar", null)
                    .show()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}