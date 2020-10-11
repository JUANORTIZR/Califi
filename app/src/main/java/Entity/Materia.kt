package Entity

import java.io.Serializable

class Materia: Serializable {
    var nombre = ""
    var codigo = ""
    var primerCorte = Corte("PrimerCorte", 0.3)
    var segundoCorte = Corte("SegundoCorte",0.3)
    var tercerCorte = Corte("TercerCorte", 0.4)

    fun getDefinitiva(): Double {
        var definitiva = 0.0

        definitiva = (primerCorte.getDefitivaCorte() * primerCorte.porcentaje) + (segundoCorte.getDefitivaCorte() * segundoCorte.porcentaje) +
                (tercerCorte.getDefitivaCorte() * tercerCorte.porcentaje)

        return definitiva
    }
}