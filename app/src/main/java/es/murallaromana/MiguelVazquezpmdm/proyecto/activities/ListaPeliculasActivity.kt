package es.murallaromana.MiguelVazquezpmdm.proyecto.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import es.murallaromana.MiguelVazquezpmdm.proyecto.MiApp
import es.murallaromana.MiguelVazquezpmdm.proyecto.R
import es.murallaromana.MiguelVazquezpmdm.proyecto.adapters.ListaPeliculasAdapter
import es.murallaromana.MiguelVazquezpmdm.proyecto.databinding.ActivityListaPeliculasBinding

class ListaPeliculasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListaPeliculasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTitle("Lista de películas")
        super.onCreate(savedInstanceState)
        binding = ActivityListaPeliculasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val layoutManager = GridLayoutManager(this, 2)
        val adapter = ListaPeliculasAdapter(this)
        binding.rvListaPeliculas.adapter = adapter
        binding.rvListaPeliculas.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(
            binding.rvListaPeliculas.context,
            layoutManager.orientation
        )


        binding.fabEngadir.setOnClickListener {
            val intent = Intent(this, NuevaPeliculaActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        val layoutManager = GridLayoutManager(this, 2)
        val adapter = ListaPeliculasAdapter(this)
        binding.rvListaPeliculas.adapter = adapter
        binding.rvListaPeliculas.layoutManager = layoutManager
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_deslog, menu)
        return true
    }

}

