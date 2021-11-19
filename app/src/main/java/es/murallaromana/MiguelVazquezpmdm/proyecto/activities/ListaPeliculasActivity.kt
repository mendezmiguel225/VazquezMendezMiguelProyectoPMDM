package es.murallaromana.MiguelVazquezpmdm.proyecto.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import es.murallaromana.MiguelVazquezpmdm.proyecto.R
import es.murallaromana.MiguelVazquezpmdm.proyecto.adapters.ListaPeliculasAdapter
import es.murallaromana.MiguelVazquezpmdm.proyecto.databinding.ActivityListaPeliculasBinding
import es.murallaromana.MiguelVazquezpmdm.proyecto.databinding.ActivityMainBinding
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.dao.PeliculasDaoMock

class ListaPeliculasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListaPeliculasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTitle("Lista de películas")
        super.onCreate(savedInstanceState)
       binding = ActivityListaPeliculasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val peliculasDao=PeliculasDaoMock()
        val listaPeliculas=peliculasDao.getTodos()

        val layoutManager = GridLayoutManager(this,2)
        val adapter = ListaPeliculasAdapter(listaPeliculas,this)
        binding.rvListaPeliculas.adapter=adapter
        binding.rvListaPeliculas.layoutManager=layoutManager
        val dividerItemDecoration = DividerItemDecoration(
            binding.rvListaPeliculas.context,
            layoutManager.orientation
        )

        binding.fabEngadir.setOnClickListener {

        }

    }
}