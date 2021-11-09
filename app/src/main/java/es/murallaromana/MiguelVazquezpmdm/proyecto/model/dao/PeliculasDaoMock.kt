package es.murallaromana.MiguelVazquezpmdm.proyecto.model.dao

import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.Pelicula


class PeliculasDaoMock : PeliculasDao{
    override fun getTodos()= listOf(
        Pelicula("Eternals", "Chloe Zhao", "Acción", "7,5","http://elcomercio.pe/resizer/ytzf2hipJdv9AtodRKqP4ncCKR0=/620x0/smart/filters:format(jpeg):quality(75)/cloudfront-us-east-1.images.arcpublishing.com/elcomercio/MCZGODYJWRF4RJRZ2LOMW4RPWM.jpg","22222" ),
        Pelicula("Viuda negra", "Cate Shortland", "Acción", "7,7","http://www.themoviedb.org/t/p/w600_and_h900_bestv2/oggEsESyLjVGD7jbIdBfa2hFUrx.jpg","2222")
    )
}