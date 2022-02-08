package es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades


import java.io.Serializable


class Pelicula (
    var id:String?,
    var title :String,
    var rating: Number,
    var runtimeMinutes: Double,
    var genre: String,
    var directorFullname : String,
    var imageUrl : String,
    var directorPhone:String
):Serializable{}