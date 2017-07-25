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
   
   private String ConectMeBabe()
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
		 NombreBaseDatos = "BaseDeDatos";
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
			String Validacion = ConectMeBabe();
			if (Validacion.compareTo("Funciono") == 0)
			{
			   Statement Instruccion = Conexion.createStatement();
			   String SQLLectura =
						"INSERT INTO `Usuarios`(`UserName`, `Password`, `FechaIngreso`, `CantHamburguesas`, `McMejor`) " +
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
			String Validacion = ConectMeBabe();
			if (Validacion.compareTo("Funciono") == 0)
			{
			   Statement Instruccion = Conexion.createStatement();
			   String SQLLectura = "select * from Usuarios";
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
				  //Non avete niente, ragazzo
			   }
			}
			
		 } catch (SQLException e)
		 {
			
		 }
	  }
   };
   
   public boolean Existe;
   public Thread Login = new Thread()
   {
	  public void run()
	  {
		 Existe = false;
		 try
		 {
			String Validacion = ConectMeBabe();
			if (Validacion.compareTo("Funciono") == 0)
			{
			   Statement Instruccion = Conexion.createStatement();
			   String SQLLectura = "select * from Usuarios WHERE UserName = " + Usuario.UsuarioSuperCrack.UserName + "AND Password = " + Usuario.UsuarioSuperCrack.Password;
			   ResultSet Resultados = Instruccion.executeQuery(SQLLectura);
			   if (Resultados.first())
			   {
				  Existe = true;
				  int Id = Resultados.getInt(1);
				  Date Fecha = new Date();
				  Instruccion = Conexion.createStatement();
				  SQLLectura = "UPDATE `Usuarios` SET `FechaIngreso`=" + Fecha.toString() + " WHERE Id = " + Id;
				  Instruccion.executeQuery(SQLLectura);
			   }
			   else
			   {
				  //AY MI DIOS!
			   }
			}
		 } catch (SQLException e)
		 {
			//gg
		 }
	  }
   };
   public Thread ExisteEnLaBaseDeDatos = new Thread()
   {
	  public void run()
	  {
		 Existe = false;
		 try
		 {
			String Validacion = ConectMeBabe();
			if (Validacion.compareTo("Funciono") == 0)
			{
			   Statement Instruccion = Conexion.createStatement();
			   String SQLLectura = "select * from Usuarios WHERE UserName = " + Usuario.UsuarioSuperCrack.UserName + "AND Password = " + Usuario.UsuarioSuperCrack.Password;
			   ResultSet Resultados = Instruccion.executeQuery(SQLLectura);
			   if (Resultados.first())
			   {
				  Existe = true;
			   }
			   else
			   {
				  //AY MI DIOS!
			   }
			}
		 } catch (SQLException e)
		 {
			//gg
		 }
	  }
   };
}
