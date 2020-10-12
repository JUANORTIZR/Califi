package com.proyectconpany.calificaion

import Entity.Actividad
import Entity.Materia
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_crear_actividad.*
import kotlinx.android.synthetic.main.activity_crear_actividad.textNombre
import kotlinx.android.synthetic.main.activity_registro_materia.*
import kotlinx.android.synthetic.main.activity_registro_materia.botonAtras
import java.util.*

class CrearActividadActivity : AppCompatActivity() {
    var materiaActual: Materia = Materia()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_actividad)

        var intent: Intent = getIntent()
        //Cambiar esto de abajo por que funciona solo si lo llamas desde crear actividad no pero si lo llamas desde editar si por los intent que manda cada uno
        val materia = intent.getSerializableExtra("materia") as Materia
        val actividad = intent.getSerializableExtra("actividad") as Actividad

        if (actividad != null) {
            textNombre.setText(actividad.nombre)
            var porcentaje = (actividad.getPorcentaje() * 100).toString()
            textPorcentajeActividad.setText(porcentaje)
            textNotaActividad.setText(actividad.nota.toString())
        }
        val opcion = intent.getStringExtra("opcion")

        materiaActual = materia

        botonRegistroActividad.setOnClickListener {
            when {
                opcion.equals("primer") -> {
                    var actividad = capturarDatos()
                    materiaActual.primerCorte.actividades.add(actividad)
                }
                opcion.equals("segundo") -> {
                    var actividad = capturarDatos()
                    materiaActual.segundoCorte.actividades.add(actividad)
                }
                opcion.equals("tercero") -> {
                    var actividad = capturarDatos()
                    materiaActual.tercerCorte.actividades.add(actividad)
                }
            }
            savedInDataBase(materiaActual)
        }
        botonAtras.setOnClickListener { finish() }
    }

    fun savedInDataBase(materia: Materia) {
        var dataBase = FirebaseDatabase.getInstance().reference
        dataBase.child(materia.codigo).setValue(materia)
    }

    fun capturarDatos(): Actividad {
        var actividad = Actividad()
        val date = Date()
        val codigo = "${date.day}${date.hours}${date.minutes}"
        actividad.codigo = codigo
        actividad.nombre = textNombre.text.toString()
        actividad.nota = textNotaActividad.text.toString().toDouble()
        actividad.setPorcentaje(textPorcentajeActividad.text.toString().toInt())

        return actividad
    }
}