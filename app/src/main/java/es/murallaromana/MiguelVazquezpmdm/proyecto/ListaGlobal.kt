package es.murallaromana.MiguelVazquezpmdm.proyecto

import android.app.Application
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.Pelicula
import java.util.ArrayList


class ListaGlobal : Application() {
    lateinit var  listaPeliculasGlobal :ArrayList<Pelicula>
}