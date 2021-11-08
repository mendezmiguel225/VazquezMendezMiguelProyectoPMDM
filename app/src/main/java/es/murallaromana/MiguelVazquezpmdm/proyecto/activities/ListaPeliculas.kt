package es.murallaromana.MiguelVazquezpmdm.proyecto.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import es.murallaromana.MiguelVazquezpmdm.proyecto.R
import es.murallaromana.MiguelVazquezpmdm.proyecto.adapters.ListaPeliculasAdapter
import es.murallaromana.MiguelVazquezpmdm.proyecto.databinding.ActivityListaPeliculasBinding
import es.murallaromana.MiguelVazquezpmdm.proyecto.databinding.ActivityMainBinding
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.dao.PeliculasDaoMock

class ListaPeliculas : AppCompatActivity() {
    private lateinit var binding: ActivityListaPeliculasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityListaPeliculasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val peliculasDao=PeliculasDaoMock()
        val listaPeliculas=peliculasDao.getTodos()

        val layoutManager = LinearLayoutManager(this)
        val adapter = ListaPeliculasAdapter(listaPeliculas,this)
        binding.rvListaPeliculas.adapter=adapter
        binding.rvListaPeliculas.layoutManager=layoutManager
        val dividerItemDecoration = DividerItemDecoration(
            binding.rvListaPeliculas.context,
            layoutManager.orientation
        )
        binding.rvListaPeliculas.addItemDecoration(dividerItemDecoration)

        binding.rvListaPeliculas.setHasFixedSize(true)

    }
}