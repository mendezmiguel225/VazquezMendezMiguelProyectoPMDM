package es.murallaromana.MiguelVazquezpmdm.proyecto.model.dao

import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.Pelicula

interface PeliculasDao {
    fun getTodos():List<Pelicula>
}