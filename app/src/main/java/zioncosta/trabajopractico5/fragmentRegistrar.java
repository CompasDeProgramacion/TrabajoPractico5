package zioncosta.trabajopractico5;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by 42100095 on 13/6/2017.
 */

public class fragmentRegistrar extends Fragment implements View.OnClickListener
{
   EditText RegistroUsuarioo;
   EditText RegistroContraseñaa;
   EditText RegistroConfirmarContraseñaa;
   EditText edtCantHamburguesas;
   String RegistroUsuario;
   String RegistroContraseña;
   String RegistroConfirmarContraseña;
   String strCantHamburguesas;
   CheckBox McMejorQueBurger;
   
   MainActivity ActividadAnfitriona;
   
   public View onCreateView(LayoutInflater InfladorDeLayouts, ViewGroup GrupoDeLaVista, Bundle DatosRecibidos)
   {
	  View VistaADevolver;
	  VistaADevolver = InfladorDeLayouts.inflate(R.layout.fragment_registrar, GrupoDeLaVista, false);
	  
	  ActividadAnfitriona = (MainActivity) getActivity();
	  
	  RegistroUsuarioo = (EditText) VistaADevolver.findViewById(R.id.RegistroNombre);
	  RegistroContraseñaa = (EditText) VistaADevolver.findViewById(R.id.RegistroContraseña);
	  RegistroConfirmarContraseñaa = (EditText) VistaADevolver.findViewById(R.id.RegistroConfirmarContraseña);
	  
	  edtCantHamburguesas = (EditText) VistaADevolver.findViewById(R.id.CantidadHamburguesas);
	  McMejorQueBurger = (CheckBox)VistaADevolver.findViewById(R.id.McMejorQueBurguer);
	  
	  RegistroUsuario = RegistroUsuarioo.getText().toString();
	  RegistroContraseña = RegistroContraseñaa.getText().toString();
	  RegistroConfirmarContraseña = RegistroConfirmarContraseñaa.getText().toString();
	  
	  Button BotonRegistroUsuario = (Button) VistaADevolver.findViewById(R.id.BotonRegistro);
	  BotonRegistroUsuario.setOnClickListener(this);
	  
	  return VistaADevolver;
   }
   
   public void onClick(View VistaLlamadora)
   {
	  RegistroUsuario = RegistroUsuarioo.getText().toString();
	  RegistroContraseña = RegistroContraseñaa.getText().toString();
	  RegistroConfirmarContraseña = RegistroConfirmarContraseñaa.getText().toString();
	  strCantHamburguesas = edtCantHamburguesas.getText().toString();
	  
	  Usuario.UsuarioSuperCrack.UserName = RegistroUsuario;
	  Usuario.UsuarioSuperCrack.Password = RegistroContraseña;
	  baseMySql MySql = new baseMySql();
	  MySql.ExisteEnLaBaseDeDatos.start();
	  
	  if (RegistroUsuario.trim().matches("") || RegistroContraseña.trim().matches("") ||
		  RegistroConfirmarContraseña.trim().matches("") || strCantHamburguesas.trim().matches("")
		 )
	  {
		 Toast MensajeError = Toast.makeText(getActivity(), "Uno o varios de los campos están vacíos. Por favor, verifique que todos los campos estén llenos antes de continuar", Toast.LENGTH_SHORT);
		 MensajeError.show();
	  }
	  else if (RegistroContraseña.compareTo(RegistroConfirmarContraseña) != 0)
	  {
		 Toast MensajeError = Toast.makeText(getActivity(), "Las contraseñas no coinciden. Por favor, verifique que sean iguales. No tenemos todo el día, señor/a, somos Google", Toast.LENGTH_SHORT);
		 MensajeError.show();
	  }
	  else if (!MySql.Existe)
	  {
		 Toast MensajeError = Toast.makeText(getActivity(), "Ya existe ese nombre de usuario. Qué lastima papu :c", Toast.LENGTH_SHORT);
		 MensajeError.show();
	  }
	  else
	  {
		 Date Fecha = new Date();
	   	   
		 Usuario.UsuarioSuperCrack.UserName = RegistroUsuario;
		 Usuario.UsuarioSuperCrack.Password = RegistroContraseña;
		 Usuario.UsuarioSuperCrack.FechaIngreso = Fecha.toString();
		 Usuario.UsuarioSuperCrack.McMejor = McMejorQueBurger.isChecked();
		 Usuario.UsuarioSuperCrack.CantHamburguesas = Integer.parseInt(strCantHamburguesas);
		 
		 MySql = new baseMySql();
		 MySql.Registrar.start();
		 
		 Fragment frgLogin = new fragmentLogin();
	     ActividadAnfitriona.IrAFragment(frgLogin, R.id.AlojadorDeFragment);
	  }
   }
   
   
   /*public void RecibirBoton(View VistaBonita) N-O-B-O-R-R-A-R
   {
	  switch (VistaBonita.getId())
	  {
		 case R.id.BotonRegistro:
			
			break;
	  }
   }*/
}
