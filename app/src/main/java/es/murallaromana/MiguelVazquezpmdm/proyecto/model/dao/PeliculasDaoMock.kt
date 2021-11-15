package es.murallaromana.MiguelVazquezpmdm.proyecto.model.dao

import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.Pelicula


class PeliculasDaoMock : PeliculasDao{

    override fun getTodos()= mutableListOf(
        Pelicula("Eternals", "Chloe Zhao", "Acción", "7,5","http://www.espaciomarvelita.com/wp-content/uploads/2019/07/xEternals-Dolby-Cinema.jpg.pagespeed.ic.5LfKUaet8S.webp","22222" ),
        Pelicula("Viuda negra", "Cate Shortland", "Acción", "7,7","http://www.themoviedb.org/t/p/w600_and_h900_bestv2/oggEsESyLjVGD7jbIdBfa2hFUrx.jpg","2222"),
        Pelicula("Spider-Man: No Way Home","Jon Watts", "Acción", "8","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5pVJ9SuuO72IgN6i9kMwQwnhGHG.jpg","2222")
    )
}