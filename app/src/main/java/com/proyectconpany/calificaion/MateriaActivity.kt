package com.proyectconpany.calificaion

import Entity.Materia
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_materia.*

class MateriaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materia)
        var intent: Intent = getIntent()
        val materia = intent.getSerializableExtra("materia") as Materia


       // textNombre.text = materia.nombre
        //textCodigo.text = materia.codigo

        botonAtras.setOnClickListener{finish()}
    }
}