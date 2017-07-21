package zioncosta.trabajopractico5;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by 42100095 on 13/6/2017.
 */
public class fragmentLogin extends Fragment implements View.OnClickListener
{
   EditText NombreUsuarioo;
   EditText Contraseñaa;
   String NombreUsuario;
   String Contraseña;
   Button BotonLogin;
   Button BotonRegistrarse;
   MainActivity ActividadAnfitriona;
   
   public View onCreateView(LayoutInflater InfladorDeLayouts, ViewGroup GrupoDeLaVista, Bundle DatosRecibidos)
   {
	  View VistaADevolver;
	  VistaADevolver = InfladorDeLayouts.inflate(R.layout.fragment_login, GrupoDeLaVista, false);
	  
	  ActividadAnfitriona = (MainActivity) getActivity();
	 
	  NombreUsuarioo = (EditText) VistaADevolver.findViewById(R.id.LoginUsuario);
	  Contraseñaa = (EditText) VistaADevolver.findViewById(R.id.LoginContrasena);
	  
	  BotonLogin = (Button) VistaADevolver.findViewById(R.id.BotonLogin);
	  BotonRegistrarse = (Button) VistaADevolver.findViewById(R.id.BotonRegistrarse);
   	  
	  BotonLogin.setOnClickListener(this);
	  BotonRegistrarse.setOnClickListener(this);
	  
	  return VistaADevolver;
   }
   
   public void onClick(View VistaLlamadora)
   {
	  if (VistaLlamadora.getId() == R.id.BotonLogin)
	  {
		 NombreUsuario = NombreUsuarioo.getText().toString();
		 Contraseña = Contraseñaa.getText().toString();
		 if (NombreUsuario.trim().matches("") || Contraseña.trim().matches(""))
		 {
			Toast MensajeError = Toast.makeText(getActivity(), "Uno o ambos de los campos están vacíos. Por favor, verifique que todos los campos estén llenos antes de continuar", Toast.LENGTH_SHORT);
			MensajeError.show();
		 }
		 else if (ActividadAnfitriona.Login(NombreUsuario, Contraseña))
		 {
			Fragment frgSesionIniciada = new fragmentIniciado();
			ActividadAnfitriona.IrAFragment(frgSesionIniciada, R.id.AlojadorDeFragment);
		 }
		 else
		 {
			Toast MensajeError = Toast.makeText(getActivity(), "Sorry bro no estás en la lista, a registrarse como corresponde la próxima", Toast.LENGTH_SHORT);
			MensajeError.show();
		 }
	  }
	  else
	  {
		 Fragment frgRegistrarse = new fragmentRegistrar();
		 ActividadAnfitriona.IrAFragment(frgRegistrarse, R.id.AlojadorDeFragment);
	  }
   }
   
   /*public void botonRegistrar(View Vista)
   {
	  
	  ActividadAnfitriona.botonRegistrar(Vista);
   }*/
}

