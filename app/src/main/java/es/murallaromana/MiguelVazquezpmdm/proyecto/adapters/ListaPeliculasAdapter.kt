package es.murallaromana.MiguelVazquezpmdm.proyecto.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.murallaromana.MiguelVazquezpmdm.proyecto.R
import es.murallaromana.MiguelVazquezpmdm.proyecto.activities.PantallaDetalleActivity
import es.murallaromana.MiguelVazquezpmdm.proyecto.model.entidades.Pelicula

class ListaPeliculasAdapter (val activity:Activity, var peliculas:List<Pelicula>): RecyclerView.Adapter<ListaPeliculasAdapter.PeliculasViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculasViewHolder {
       val layoutInflater=LayoutInflater.from(parent.context).inflate(R.layout.item_lista_peliculas,parent,false)
       return PeliculasViewHolder(layoutInflater)
    }


    override fun getItemCount()=peliculas.size



    override fun onBindViewHolder(holder: PeliculasViewHolder, position: Int) {
        val pelicula= peliculas.get(position)
        Picasso.get().load(pelicula.imageUrl).into(holder.ivFoto)
        holder.clItemPelicula.setOnClickListener {
            val intent= Intent(activity, PantallaDetalleActivity::class.java)
            intent.putExtra("id",pelicula.id )
            activity.startActivity(intent)
        }


    }
    class PeliculasViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val ivFoto = itemView.findViewById<ImageView>(R.id.ivFoto)
        val clItemPelicula=itemView.findViewById<ConstraintLayout>(R.id.clItemPelicula)
    }
}