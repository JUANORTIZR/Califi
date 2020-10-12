package Entity

import java.io.Serializable

class Actividad:Serializable {
    var codigo = ""
    var nombre = ""
    var nota = 0.0
    private var porcentaje = 0.0

    fun setPorcentaje(_porcentaje:Int){
        porcentaje = _porcentaje / 100.0
    }

    fun getPorcentaje(): Double {
        return porcentaje
    }
}