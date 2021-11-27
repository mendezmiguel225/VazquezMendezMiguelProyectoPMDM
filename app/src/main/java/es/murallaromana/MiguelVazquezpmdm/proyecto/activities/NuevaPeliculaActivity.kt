package es.murallaromana.MiguelVazquezpmdm.proyecto.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import es.murallaromana.MiguelVazquezpmdm.proyecto.MiApp
import es.murallaromana.MiguelVazquezpmdm.proyecto.R
import es.murallaromana.MiguelVazquezpmdm.proyecto.databinding.ActivityNuevaPeliculaBinding
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.Pelicula


class NuevaPeliculaActivity : AppCompatActivity() {
    lateinit var binding:ActivityNuevaPeliculaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_pelicula)
        binding = ActivityNuevaPeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("Nueva película")

        binding.bttCancelar.setOnClickListener(){
            this.onBackPressed()
        }

        binding.bttCrear.setOnClickListener(){
            if(binding.tiTitulo.text.toString().trim()==""||binding.tiGenero.text.toString().trim()==""||binding.tiDirector.text.toString().trim()==""||
                binding.tiNumeroD.text.toString().trim()==""||binding.tiURL.text.toString().trim()==""||binding.tiValoracion.text.toString().trim()==""){
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }else{
                var p=Pelicula(MiApp.peliculas.size,binding.tiTitulo.text.toString().trim(),binding.tiDirector.text.toString().trim(),
                    binding.tiGenero.text.toString().trim(),binding.tiValoracion.text.toString().trim(),binding.tiURL.text.toString().trim(),
                    binding.tiNumeroD.text.toString().trim())
                MiApp.peliculas.add(p)
                finish()
            }
        }
    }
}