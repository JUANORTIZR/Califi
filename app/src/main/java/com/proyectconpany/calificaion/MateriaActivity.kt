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


        botonCrearActividadPrimCorte.setOnClickListener{
            var intent = Intent(this@MateriaActivity, CrearActividadActivity::class.java)
            intent.putExtra("materia", materia)
            intent.putExtra("opcion", "primer")
            startActivity(intent)
        }

        botonVerActividadPrimCorte.setOnClickListener{
            var intent = Intent(this@MateriaActivity, ListaActividadesActivity::class.java)
            intent.putExtra("opcion", "primer")
            startActivity(intent)
        }

        botonCrearActividadSegCorte.setOnClickListener{
            var intent = Intent(this@MateriaActivity, CrearActividadActivity::class.java)
            intent.putExtra("materia", materia)
            intent.putExtra("opcion", "segundo")
            startActivity(intent)
        }

        botonVerActividadSegCorte.setOnClickListener{
            var intent = Intent(this@MateriaActivity, ListaActividadesActivity::class.java)
            intent.putExtra("opcion", "segundo")
            startActivity(intent)
        }

        botonCrearActividadTercerCorte.setOnClickListener{
            var intent = Intent(this@MateriaActivity, CrearActividadActivity::class.java)
            intent.putExtra("materia", materia)
            intent.putExtra("opcion", "tercer")
            startActivity(intent)
        }

        botonVerActividadSegCorte.setOnClickListener{
            var intent = Intent(this@MateriaActivity, ListaActividadesActivity::class.java)
            intent.putExtra("opcion", "tercero")
            startActivity(intent)
        }

        botonAtras.setOnClickListener{finish()}
    }
}