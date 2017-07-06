package zioncosta.trabajopractico5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrador on 21/6/2017.
 */

public class DbHelper extends SQLiteOpenHelper
{
   
   public  DbHelper(Context Contexto, String Nombre, SQLiteDatabase.CursorFactory Fabrica, int Version)
   {
	  super(Contexto, Nombre, Fabrica, Version);
   }
   
   @Override
   public void onCreate(SQLiteDatabase DatabaseLOL)
   {
	  //Creo la tabla personas con una columna "Nombre de Usuario" y otra con "Contraseña"
	  String CrearTablaUsuarios = "create table Usuarios (NombreUsuario text, Contrasenia text)";
	  DatabaseLOL.execSQL(CrearTablaUsuarios);
   }
   
   @Override
   public void onUpgrade(SQLiteDatabase Db, int versionAnterior, int versionNueva)
   {
   }
   
}