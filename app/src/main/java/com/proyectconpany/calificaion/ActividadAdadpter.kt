package com.proyectconpany.calificaion

import Entity.Actividad
import Entity.Materia
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_crear_actividad.view.*
import kotlinx.android.synthetic.main.activity_item_actividad.view.*
import kotlinx.android.synthetic.main.activity_item_materia.view.*

class ActividadAdadpter(private val mContext: Context, private val listaActividades:List<Actividad>) : ArrayAdapter<Actividad>(mContext, 0, listaActividades) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.activity_item_actividad, parent, false)

        val actividad = listaActividades[position]

        layout.textNombreActividad.text = actividad.nombre
        var porcentaje = (actividad.getPorcentaje() * 100).toString() + "%"
        layout.textPorcentajeActivity.text = porcentaje
        layout.textDefinitiva.text = actividad.nota.toString()

        return layout
    }
}