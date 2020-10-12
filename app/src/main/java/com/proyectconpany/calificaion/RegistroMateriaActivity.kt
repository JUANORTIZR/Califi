package com.proyectconpany.calificaion

import Entity.Actividad
import Entity.Materia
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_registro_materia.*
import java.util.*

class RegistroMateriaActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_materia)
        val date = Date()
        val codigo = "${date.day}${date.hours}${date.minutes}"
        textCodigo1.setText(codigo)
        botonAtras.setOnClickListener{finish()}
        var database = FirebaseDatabase.getInstance().reference
        botonRegistro.setOnClickListener{

            var nombre = textNombre.text.toString()


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