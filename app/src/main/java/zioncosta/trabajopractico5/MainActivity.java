package zioncosta.trabajopractico5;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
   
   FragmentManager AdministradorDeFragments;
   FragmentTransaction TransaccionDeFragment;
   
   DbHelper AccesoDb;
   SQLiteDatabase BaseDeDatosRicolina;
   
   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_main);
	  
	  AdministradorDeFragments = getSupportFragmentManager();
	  
	  Fragment frgIngreso;
	  frgIngreso = new fragmentInicio();
	  
	  TransaccionDeFragment = AdministradorDeFragments.beginTransaction();
	  TransaccionDeFragment.replace(R.id.AlojadorDeFragment, frgIngreso);
	  TransaccionDeFragment.commit();
   }
   
   public void botonEntrar(View Vista)
   {
	  AdministradorDeFragments = getSupportFragmentManager();
	  
	  Fragment frgIngreso;
	  frgIngreso = new fragmentLogin();
	  
	  TransaccionDeFragment = AdministradorDeFragments.beginTransaction();
	  TransaccionDeFragment.replace(R.id.AlojadorDeFragment, frgIngreso);
	  TransaccionDeFragment.commit();
   }
   
   
   public void cambiarVista()
   {
	  AdministradorDeFragments = getSupportFragmentManager();
	  
	  Fragment frgIngreso;
	  frgIngreso = new fragmentLogin();
	  
	  TransaccionDeFragment = AdministradorDeFragments.beginTransaction();
	  TransaccionDeFragment.replace(R.id.AlojadorDeFragment, frgIngreso);
	  TransaccionDeFragment.commit();
   }
   
   /* Esta funcion muestra un Fragment pasado como parametro en un Holder pasado como parametro*/
   public void IrAFragment(Fragment FragmentAPasar, int IdDelHolder)
   {
	  AdministradorDeFragments = getSupportFragmentManager();
	  
	  TransaccionDeFragment = AdministradorDeFragments.beginTransaction();
	  TransaccionDeFragment.replace(IdDelHolder, FragmentAPasar);
	  TransaccionDeFragment.commit();
   }
   
   public ArrayList<String> ListarUsuarios()
   {
	  ArrayList<String> ListaUsuariosCapos = new ArrayList<>();
	  if (ConexionBaseDatos())
	  {
		 //Ejecuto una consulta que devuelve los registros
		 Cursor Registros = BaseDeDatosRicolina.rawQuery("select NombreUsuario from Usuarios", null);
		 //Si hay registros entro al if y la repetitiva
		 if (Registros.moveToFirst())
		 {
			//Leo todos los registros y los agrego uno por uno al arraylist
			do
			{
			   ListaUsuariosCapos.add(Registros.getString(0));
			}
			while (Registros.moveToNext());
		 }
	  }
	  return ListaUsuariosCapos;
   }
   
   public boolean ConexionBaseDatos()
   {
	  boolean Respuesta = false;
	  //Declaro el helper y la base de datos
	  AccesoDb = new DbHelper(this, "BaseTP4", null, 1);
	  BaseDeDatosRicolina = AccesoDb.getWritableDatabase();
	  //Verifico que la base de datos exista, comprobando que no sea null
	  if (BaseDeDatosRicolina != null)
	  {
		 Respuesta = true;
	  }
	  return Respuesta;
   }
   
   public boolean ExisteEnLaBaseDeDatos(String Nombre)
   {
	  if (ConexionBaseDatos())
	  {
		 //Ejecuto una consulta que devuelve los registros
		 Cursor Registros = BaseDeDatosRicolina.rawQuery("select NombreUsuario from Usuarios", null);
		 //Si hay registros entro al if y la repetitiva
		 if (Registros.moveToFirst())
		 {
			//Leo los registros hasta que encuentre un nombre igual al ingresado, o que termine de recorer los registros
			do
			{
			   //Si el nombre ingresado es igual al del registro, devuelvo true finalizando el do while
			   String NombreSQL = Registros.getString(0);
			   int Comparador = NombreSQL.compareTo(Nombre);
			   if (Comparador == 0)
			   {
				  return true;
			   }
			} while (Registros.moveToNext());
		 }
	  }
	  //BaseDeDatosRicolina.close();
	  //Si no encontre un nombre igual o no pude abrir la Db devuelvo false
	  return false;
   }
   
   public boolean Login(String Nombre, String Contraseña)
   {
	  if (ConexionBaseDatos())
	  {
		 //Ejecuto una consulta que devuelve los registros
		 Cursor Registros = BaseDeDatosRicolina.rawQuery("select NombreUsuario, Contrasenia from Usuarios", null); //WHERE NombreUsuario ='" + Nombre + "'and Contrasenia ='" + Contraseña + "'"
		 //Si hay registros entro al if y la repetitiva
		 if (Registros.moveToFirst())
		 {
			//Leo los registros hasta que encuentre un nombre igual al ingresado, o que termine de recorer los registros
			do
			{
			   //Si el nombre ingresado es igual al del registro, devuelvo true finalizando el do while
			   String NombreSQL = Registros.getString(0);
			   String ContrasenaSQL = Registros.getString(1);
			   if (NombreSQL.equals(Nombre) && ContrasenaSQL.equals(Contraseña))
			   {
				  return true;
			   }
			} while (Registros.moveToNext());
		 }
	  }
	  //BaseDeDatosRicolina.close();
	  //Si no encontre un nombre igual o no pude abrir la Db devuelvo false
	  return false;
   }
   
   public void AgregarABaseDatos(String NombreUsuarioJEJE, String Contrasenia)
   {
	  if (ConexionBaseDatos())
	  {
		 ContentValues NuevoRegistro = new ContentValues();
		 NuevoRegistro.put("NombreUsuario", NombreUsuarioJEJE);
		 NuevoRegistro.put("Contrasenia", Contrasenia);
		 BaseDeDatosRicolina.insert("Usuarios", null, NuevoRegistro);
		 BaseDeDatosRicolina.close();
	  }
   }
   
}
