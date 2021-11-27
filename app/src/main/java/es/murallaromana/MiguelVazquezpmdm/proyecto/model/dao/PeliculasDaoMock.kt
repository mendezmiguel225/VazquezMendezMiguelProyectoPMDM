package es.murallaromana.MiguelVazquezpmdm.proyecto.model.dao

import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.Pelicula
import java.util.ArrayList

class PeliculasDaoMock : PeliculasDao {


    override fun getTodos() = arrayListOf(
        Pelicula(
           0,
            "Eternals",
            "Chloe Zhao",
            "Acción",
            "7,5",
            "https://www.espaciomarvelita.com/wp-content/uploads/2019/07/xEternals-Dolby-Cinema.jpg.pagespeed.ic.5LfKUaet8S.webp",
            "22222"
        ),
        Pelicula(
            1,
            "Viuda negra",
            "Cate Shortland",
            "Acción",
            "7,7",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oggEsESyLjVGD7jbIdBfa2hFUrx.jpg",
            "2222"
        ),
        Pelicula(
            2,
            "Spider-Man: No Way Home",
            "Jon Watts",
            "Acción",
            "8",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5pVJ9SuuO72IgN6i9kMwQwnhGHG.jpg",
            "2222"
        )
    )
}