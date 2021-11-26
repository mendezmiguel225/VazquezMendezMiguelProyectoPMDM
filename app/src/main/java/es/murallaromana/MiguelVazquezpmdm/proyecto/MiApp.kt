package es.murallaromana.MiguelVazquezpmdm.proyecto

import android.app.Application
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.dao.PeliculasDaoMock
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.Pelicula
import java.util.ArrayList



class MiApp : Application() {

    companion object {
        var peliculas= ArrayList<Pelicula>()
        fun borrarPelicula(indice:Int)= peliculas.removeAt(indice)
        fun crearPelicula(pelicula: Pelicula)=peliculas.add(pelicula)
        fun actualizarIndice(){
            var i=0
            while(i<peliculas.size){
                peliculas.get(i).indice=i
                i++
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        inicializarLista()

    }

    fun inicializarLista() {
        val dao=PeliculasDaoMock()
        peliculas= dao.getTodos()
    }


}