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
                    listaMaterias.add(materia)
                }

                Toast.makeText(
                    this@MainActivity,
                    listaMaterias[1].nombre, Toast.LENGTH_SHORT
                ).show()

                val adapter = MateriaAdapter(this@MainActivity, listaMaterias)

                lista.adapter = adapter
            }
        }
        database.addValueEventListener(getData)

        lista.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this@MainActivity, MateriaActivity:: class.java)
            intent.putExtra("materia", listaMaterias[position])
            startActivity(intent)
        }
    }


}