package zioncosta.trabajopractico5;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 42077426 on 27/6/2017.
 */

public class baseMySql
{
   Connection Conexion;
   
   private String NiceConn()
   {
	  try
	  {
		 //Fuerzo la carga a memoria del driver de acceso a la base
		 Class.forName("com.mysql.jdbc.Driver");
		 
		 //Guardo en variables los parámetros de acceso, solo para facilitar su lectura
		 String RutaServidorMySql, NombreBaseDatos, NombreUsuario, PasswordUsuario, CadenaCompletaConexion;
		 int PuertoServidor;
		 
		 RutaServidorMySql = "10.0.2.2";
		 PuertoServidor = 3306;
		 NombreBaseDatos = "db";
		 NombreUsuario = "root";
		 PasswordUsuario = "root";
		 
		 //Armo la cadena completa de acceso a la base
		 CadenaCompletaConexion = "jdbc:mysql://" + RutaServidorMySql + ":" + PuertoServidor + "/" + NombreBaseDatos;
		 
		 //Establezco la conexión a la base
		 Conexion = DriverManager.getConnection(CadenaCompletaConexion, NombreUsuario, PasswordUsuario);
		 
		 String Funciono = "Funciono";
		 return Funciono;
	  } catch (ClassNotFoundException e)
	  {
		 String Error = "Error" + e.getMessage();
		 return Error;
	  } catch (SQLException e)
	  {
		 String Error = "Error" + e.getMessage();
		 return Error;
	  }
   }
   
   public Thread Registrar = new Thread()
   {
	  public void run()
	  {
		 try
		 {
			String Validacion = NiceConn();
			if (Validacion.compareTo("Funciono") == 0)
			{
			   Statement Instruccion = Conexion.createStatement();
			   String SQLLectura =
						"INSERT INTO `Usuarios`(`UserName`, `Password`, `FechaIngreso`, `Commie`, `SoretePromCm`) " +
								 "VALUES (" + Usuario.UsuarioSuperCrack.UserName + "," + Usuario.UsuarioSuperCrack.Password + "," +
								 Usuario.UsuarioSuperCrack.FechaIngreso + "," + Usuario.UsuarioSuperCrack.McMejor + "," +
								 Usuario.UsuarioSuperCrack.CantHamburguesas + ")";
			   Instruccion.executeQuery(SQLLectura);
			}
		 } catch (SQLException e)
		 {
			Log.d("Insert", "Error: " + e.getMessage());
		 }
	  }
   };
   
   public ArrayList<Usuario> ListUsers = new ArrayList<>();
   
   public Thread Traer = new Thread()
   {
	  public void run()
	  {
		 ListUsers = new ArrayList<>();
		 try
		 {
			String result = NiceConn();
			if (result.compareTo("Eh ameo te salio todo re cheto gatito") == 0)
			{
			   Statement Instruccion = Conexion.createStatement();
			   String SQLLectura = "select * from usuarios";
			   ResultSet Resultados = Instruccion.executeQuery(SQLLectura);
			   if (Resultados.first())
			   {
				  Usuario UsuarioSuperCrack = new Usuario();
				  UsuarioSuperCrack.Id = Resultados.getInt(1);
				  UsuarioSuperCrack.UserName = Resultados.getString(1);
				  UsuarioSuperCrack.Password = Resultados.getString(2);
				  UsuarioSuperCrack.FechaIngreso = Resultados.getString(3);
				  UsuarioSuperCrack.McMejor = Resultados.getBoolean(4);
				  UsuarioSuperCrack.CantHamburguesas = Resultados.getInt(5);
				  ListUsers.add(UsuarioSuperCrack);
				  while (Resultados.next())
				  {
					 UsuarioSuperCrack.Id = Resultados.getInt(1);
					 UsuarioSuperCrack.UserName = Resultados.getString(2);
					 UsuarioSuperCrack.Password = Resultados.getString(3);
					 UsuarioSuperCrack.FechaIngreso = Resultados.getString(4);
					 UsuarioSuperCrack.McMejor = Resultados.getBoolean(5);
					 UsuarioSuperCrack.CantHamburguesas = Resultados.getInt(6);
					 ListUsers.add(UsuarioSuperCrack);
				  }
			   }
			   else
			   {
				  Log.d("SelectUsuarios", "No hay nada");
			   }
			}
			
			
		 } catch (SQLException e)
		 {
			
		 }
	  }
   };
   public boolean LogInExito;
   public Thread LogIn = new Thread()
   {
	  public void run()
	  {
		 LogInExito = false;
		 try
		 {
			String result = NiceConn();
			if (result.compareTo("Eh ameo te salio todo re cheto gatito") == 0)
			{
			   Statement Instruccion = Conexion.createStatement();
			   String SQLLectura = "select * from usuarios WHERE Nombre = " + Usuario.UsuarioSuperCrack.UserName + "AND Contrasena = " + Usuario.UsuarioSuperCrack.Password;
			   ResultSet Resultados = Instruccion.executeQuery(SQLLectura);
			   if (Resultados.first())
			   {
				  LogInExito = true;
				  int id = Resultados.getInt(1);
				  Date d = new Date();
				  Instruccion = Conexion.createStatement();
				  SQLLectura = "UPDATE `usuarios` SET `FechaIngreso`=" + d.toString() + " WHERE Id = " + id;
				  Instruccion.executeQuery(SQLLectura);
			   }
			   else
			   {
				  Log.d("SelectUsuarios", "No hay nada");
			   }
			}
		 } catch (SQLException e)
		 {
			
		 }
	  }
   };
}
