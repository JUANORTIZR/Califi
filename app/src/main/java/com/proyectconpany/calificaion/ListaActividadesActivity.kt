package com.proyectconpany.calificaion

import Entity.Actividad
import Entity.Materia
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_lista_actividades.*
import kotlinx.android.synthetic.main.activity_main.*

class ListaActividadesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_actividades)

        var database = FirebaseDatabase.getInstance().reference
        var actividades = mutableListOf<Actividad>()
        var materiaActual = Materia()

        var getData = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {

                actividades.clear()
                for(i in p0.children){
                    var materia = Materia()
                    var codigo = i.child("codigo").getValue()
                    var nombre = i.child("nombre").getValue()
                    materia.codigo = codigo.toString()
                    materia.nombre = nombre.toString()
                    for(item in i.child("primerCorte").child("actividades").children){
                        var actividad = Actividad()
                        actividad.nombre = item.child("nombre").getValue().toString()
                        var porcentajeInt = (item.child("porcentaje").getValue().toString().toDouble() * 100).toInt()
                        actividad.setPorcentaje(porcentajeInt)
                        actividad.nota = item.child("nota").getValue().toString().toDouble()
                        materia.primerCorte.actividades.add(actividad)
                    }
                    for(item in i.child("segundoCorte").child("actividades").children){
                        var actividad = Actividad()
                        actividad.nombre = item.child("nombre").getValue().toString()
                        var porcentajeInt = (item.child("porcentaje").getValue().toString().toDouble() * 100).toInt()
                        actividad.setPorcentaje(porcentajeInt)
                        actividad.nota = item.child("nota").getValue().toString().toDouble()
                        materia.segundoCorte.actividades.add(actividad)
                    }

                    for(item in i.child("tercerCorte").child("actividades").children){
                        var actividad = Actividad()
                        actividad.nombre = item.child("nombre").getValue().toString()
                        var porcentajeInt = (item.child("porcentaje").getValue().toString().toDouble() * 100).toInt()
                        actividad.setPorcentaje(porcentajeInt)
                        actividad.nota = item.child("nota").getValue().toString().toDouble()
                        materia.tercerCorte.actividades.add(actividad)
                    }
                    var intent: Intent = getIntent()
                    var opcion = intent.getStringExtra("opcion")
                    when {
                        opcion.equals("primer") ->{
                            actividades = materia.primerCorte.actividades
                        }
                        opcion.equals("segundo")->{
                            actividades = materia.segundoCorte.actividades
                        }
                        opcion.equals("tercero") -> {
                            actividades = materia.tercerCorte.actividades
                        }
                    }
                    materiaActual = materia
                }

                val adapter = ActividadAdadpter(this@ListaActividadesActivity,actividades)

                lisActividades.adapter = adapter
            }
        }
        database.addValueEventListener(getData)
        database.addListenerForSingleValueEvent(getData)

        lisActividades.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this@ListaActividadesActivity, CrearActividadActivity:: class.java)
            intent.putExtra("materia", materiaActual)
            intent.putExtra("actividad", actividades[position])
            startActivity(intent)
        }

        botonAtrasActividades.setOnClickListener{finish()}
    }
}