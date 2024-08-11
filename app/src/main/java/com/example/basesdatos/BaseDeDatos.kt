package com.example.basesdatos

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val Basedatosfinal="Dasedatosfinal"
class BaseDeDatos(contexto: Context) :SQLiteOpenHelper(contexto, Basedatosfinal, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val crearTabla="CREATE TABLE " +
                "Usuario (id INTEGER PRIMARY KEY, nombre VARCHAR(256),apellido VARCHAR(256), semestre INTEGER, promedio REAL)";
        db?.execSQL(crearTabla);
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
    fun insertarDatos(usuario: Usuario):String{
        val db = this.writableDatabase
        var contenedordeValores = ContentValues();
        contenedordeValores.put("id", usuario.id)
        contenedordeValores.put("nombre", usuario.nombre)
        contenedordeValores.put("apellido", usuario.apellido)
        contenedordeValores.put("semestre", usuario.semestre)
        contenedordeValores.put("promedio", usuario.promedio)
        var resultado=db.insert("Usuario ", null, contenedordeValores)
        if(resultado==-1.toLong()) {
            return "fallas en la inserción";
        }else{
            return "Datos insertados"
        }
    }//insertarDatos
    fun traerDatos():MutableList<Usuario>{
        var lista:MutableList<Usuario> = ArrayList()
        val db =this.readableDatabase
        //val sql="select * from Usuario where id=10"
        val sql="select * from Usuario"
        val resultado= db.rawQuery(sql,null)
        if(resultado.moveToFirst()){
            do {
                var usuario=Usuario()
                usuario.id = resultado.getInt(resultado.getColumnIndex("id").toInt())
                usuario.nombre = resultado.getString(resultado.getColumnIndex("nombre").toInt()) // Lee el nombre como una cadena
                usuario.apellido = resultado.getString(resultado.getColumnIndex("apellido").toInt()) // Lee el apellido como una cadena
                usuario.semestre = resultado.getInt(resultado.getColumnIndex("semestre").toInt()) // Lee el semestre como un entero
                usuario.promedio = resultado.getDouble(resultado.getColumnIndex("promedio").toInt()) // Lee el promedio como un entero
                lista.add(usuario)
            }while (resultado.moveToNext())
        }//if
        resultado.close()
        db.close()
        return lista
    }//listar
    fun borrar(codigo:String){
        if(codigo.length>0){
            val db =this.writableDatabase
            db.delete("Usuario","id=?",arrayOf(codigo))
            //para borar todos
            //db.delete("Usuario",null,null)
            db.close()
        }
    }//borrar
    fun actualizar(usuario: Usuario):String{
        val db = this.writableDatabase
        var contenedordeValores = ContentValues();
        contenedordeValores.put("id", usuario.id)
        contenedordeValores.put("nombre", usuario.nombre)
        contenedordeValores.put("apellido", usuario.apellido)
        contenedordeValores.put("semestre", usuario.semestre)
        contenedordeValores.put("promedio", usuario.promedio)
        var resultado=db.update("Usuario", contenedordeValores,
            "id=?", arrayOf(usuario.id.toString()))
        if(resultado>0){
            return "Actualización exitosa"
        }else{
            return "no re actualizó nombre "+usuario.nombre+"-id-"+usuario.id+"-apellido"+usuario.apellido+"-semestre"+usuario.semestre+"-promedio"+usuario.promedio
        }
    }//actualizar
}//clase
