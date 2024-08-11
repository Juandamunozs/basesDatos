package com.example.basesdatos

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import com.example.basesdatos.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var ed1:EditText// Mostrar los resultados en pantalla/Mapeo delcampo de visualizaciòn
    lateinit var nombre:EditText
    lateinit var apellido:EditText
    lateinit var semestre:EditText
    lateinit var promedio:EditText
    lateinit var mensaje:TextView
    lateinit var codigo:TextView
    lateinit var id:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        nombre=binding.Nombre
        apellido=binding.Apellido
        semestre=binding.Semestre
        promedio=binding.Promedio
        mensaje=binding.mensaje
        id=binding.Codigo1
        codigo=binding.Codigo
        setContentView(binding.root)
    }
    fun crearDatos(view:View){
        if(nombre.text.toString().length>0 && apellido.text.toString().length>0){
            var usuario=
                Usuario(id.text.toString().toInt(),nombre.text.toString(),apellido.text.toString(),semestre.text.toString().toInt(),promedio.text.toString().toDouble())
            var Db= BaseDeDatos(this)
            mensaje.setText(Db.insertarDatos(usuario))
        }
    }
    fun leerDatos(view:View){
        var db= BaseDeDatos(this)
        var datos=db.traerDatos()
        mensaje.text=""
        for(i in 0..datos.size-1){
            val usuario =datos.get(i)
            mensaje.append(" cod:"+usuario.id +" Nombre:"+usuario.nombre+ " Apellido:"+usuario.apellido+ " Semestre:"+usuario.semestre+ " Prom:"+usuario.promedio+ "\n" )
        }
        db.close()
    }
    fun borrarDatos(view: View){
        var db=BaseDeDatos(this)
        db.borrar(codigo.text.toString())
    }
    fun actualizar(view: View){
        var db=BaseDeDatos(this)
        var usuario=
            Usuario(codigo.text.toString().toInt(),nombre.text.toString(),apellido.text.toString(),semestre.text.toString().toInt(),promedio.text.toString().toDouble())
        mensaje.setText(db.actualizar(usuario))
    }
}
