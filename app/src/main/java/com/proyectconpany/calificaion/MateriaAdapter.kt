package com.proyectconpany.calificaion

import Entity.Materia
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_item_materia.view.*

class MateriaAdapter(private val mContext: Context, private val listaMaterias:List<Materia>) : ArrayAdapter<Materia>(mContext, 0, listaMaterias) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.activity_item_materia, parent, false)

        val materia = listaMaterias[position]

        layout.textCodigo.text = materia.codigo
        layout.textNombre.text = materia.nombre

        return layout
    }
}