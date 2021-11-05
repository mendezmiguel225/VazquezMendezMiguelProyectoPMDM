package es.murallaromana.MiguelVazquezpmdm.proyecto.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.murallaromana.MiguelVazquezpmdm.proyecto.R
import es.murallaromana.MiguelVazquezpmdm.proyecto.activities.ListaPeliculas
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.Pelicula

class ListaPeliculasAdapter (val peliculas:List<Pelicula>,val activity:Activity): RecyclerView.Adapter<ListaPeliculasAdapter.PeliculasViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculasViewHolder {
       val layoutInflater=LayoutInflater.from(parent.context).inflate(R.layout.item_lista_peliculas,parent,false)
       return PeliculasViewHolder(layoutInflater)
    }


    override fun getItemCount()=peliculas.size



    override fun onBindViewHolder(holder: PeliculasViewHolder, position: Int) {
        val pelicula= peliculas.get(position)
        holder.tvTitulo.setText(pelicula.titulo)
        holder.tvGenero.setText(pelicula.genero)
        holder.tvDirector.setText(pelicula.director)
        holder.tvValoracion.setText(pelicula.valoracion)
        Picasso.get().load(pelicula.url).into(holder.ivFoto)
        holder.clItemPelicula.setOnClickListener{

        }


    }
    class PeliculasViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val tvTitulo= itemView.findViewById<TextView>(R.id.tvTitulo)
        val tvGenero= itemView.findViewById<TextView>(R.id.tvGenero)
        val tvDirector= itemView.findViewById<TextView>(R.id.tvDirector)
        val tvValoracion= itemView.findViewById<TextView>(R.id.tvValoracion)
        val ivFoto = itemView.findViewById<ImageView>(R.id.ivFoto)
        val clItemPelicula=itemView.findViewById<ConstraintLayout>(R.id.clItemPelicula)
    }
}