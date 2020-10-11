package Entity

import java.io.Serializable

class Corte: Serializable {
    private var nombre = ""
    var porcentaje = 0.0
    var actividades = mutableListOf<Actividad>()

    constructor(_nombre:String,_porcentaje:Double){
        nombre = _nombre
        porcentaje = _porcentaje
    }

    fun getNombre(): String {
        return nombre
    }

    fun getDefitivaCorte():Double{
        var definitiva = 0.0
        for(actividad in actividades){
            definitiva += actividad.nota * actividad.getPorcentaje()
        }
        return definitiva
    }

}