package com.example.basesdatos

class Usuario {
    var id: Int = 0
    var nombre: String = ""
    var apellido: String = ""
    var semestre: Int = 0
    var promedio: Double = 0.0

    constructor(id: Int, nombre: String, apellido: String, semestre: Int, promedio: Double) { // Ajuste al constructor
        this.id = id
        this.nombre = nombre
        this.apellido = apellido
        this.semestre = semestre
        this.promedio = promedio
    }

    constructor() // Constructor por defecto
}

