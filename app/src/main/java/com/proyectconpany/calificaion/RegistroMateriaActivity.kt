package com.proyectconpany.calificaion

import Entity.Materia
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_item_materia.view.*
import kotlinx.android.synthetic.main.activity_registro_materia.*
import java.lang.StringBuilder

class RegistroMateriaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_materia)

        botonAtras.setOnClickListener{finish()}
        var database = FirebaseDatabase.getInstance().reference
        botonRegistro.setOnClickListener{
            var nombre = textNombre.text.toString()
            var codigo = textCodigo1.text.toString()

            var materia = Materia()
            materia.codigo = codigo
            materia.nombre = nombre


            database.child(materia.codigo).setValue(materia)

            Toast.makeText(
                this@RegistroMateriaActivity,
                "Registro exitoso", Toast.LENGTH_SHORT
            ).show()

        }

    }

    var listaMateria = mutableListOf<Materia>()
}