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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        boton1.setOnClickListener{
            val intent = Intent(this@MainActivity, RegistroMateriaActivity:: class.java)
            startActivity(intent)
        }
        var database = FirebaseDatabase.getInstance().reference
        var listaMaterias = mutableListOf<Materia>()



        var getData = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {

                listaMaterias.clear()
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
                    listaMaterias.add(materia)
                }

                Toast.makeText(
                    this@MainActivity,
                    "😍 que ser tan hermoso, sonrie", Toast.LENGTH_SHORT
                ).show()

                val adapter = MateriaAdapter(this@MainActivity, listaMaterias)

                listaActivid.adapter = adapter
            }
        }
        database.addValueEventListener(getData)
        database.addListenerForSingleValueEvent(getData)

        listaActivid.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this@MainActivity, MateriaActivity:: class.java)
            intent.putExtra("materia", listaMaterias[position])
            var actividad = Actividad()
            intent.putExtra("actividad", actividad)
            startActivity(intent)
        }
    }


}