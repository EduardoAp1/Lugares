package com.lugares.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lugares.databinding.LugarFilaBinding
import com.lugares.model.Lugar
import com.lugares.ui.lugar.LugarFragmentDirections

class LugarAdapter : RecyclerView.Adapter<LugarAdapter.LugarViewHolder>(){
    //LOS ADAPTER TIENEN LA MISMA ESTRUCTURA SIEMPRE

    //LISTA PARA GESTIONAR LA INFORMACION DE LOS LUGARES
    private var lista = emptyList<Lugar>()

    inner class LugarViewHolder(private val itemBinding: LugarFilaBinding)
        : RecyclerView.ViewHolder (itemBinding.root){

            fun dibuja(lugar: Lugar){
                itemBinding.tvNombre.text = lugar.nombre
                itemBinding.tvCorreo.text = lugar.correo
                itemBinding.tvTelefono.text = lugar.telefono
                itemBinding.tvWeb.text = lugar.web

                itemBinding.vistaFila.setOnClickListener{
                    val accion =  LugarFragmentDirections
                        .actionNavLugarToUpdateLugarFragment(lugar)
                    itemView.findNavController().navigate(accion)
                }
            }
    }

    //ACA SE VA CREAR UNA "CAJITA" DEL RECICLADOR... UNA FILA... SOLO LA ESTRUCTURA
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LugarViewHolder {
        val itemBinding =
            LugarFilaBinding.inflate(LayoutInflater.from(parent.context),
                parent,false)
        return  LugarViewHolder(itemBinding)
    }

    //ACA SE VA A SOLICITAR "DIBUJAR" UNA CAJITA, SEGUN EL ELEMENTO DE LA LISTA
    override fun onBindViewHolder(holder: LugarViewHolder, position: Int) {
        val lugar = lista[position]
        holder.dibuja(lugar)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun setData(lugares : List<Lugar>){
        lista = lugares
        notifyDataSetChanged()
    }

}