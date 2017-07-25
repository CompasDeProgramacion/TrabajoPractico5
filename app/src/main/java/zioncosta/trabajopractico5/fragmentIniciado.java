package zioncosta.trabajopractico5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by ezequ on 27/6/2017.
 */

public class fragmentIniciado extends Fragment
{
   
   public View onCreateView(LayoutInflater InfladorDeLayouts, ViewGroup GrupoDeLaVista, Bundle DatosRecibidos)
   {
	  View VistaADevolver;
	  VistaADevolver = InfladorDeLayouts.inflate(R.layout.fragment_iniciado, GrupoDeLaVista, false);
	  MainActivity ActividadPrincipal = (MainActivity) getActivity();
	  baseMySql MySql = new baseMySql();
	  
	  MySql.Traer.start();
	  if (MySql.ListUsers == null)
	  {
		 //tf you mean?
	  }
	  else
	  {
		 ListView ListViewUsers = (ListView) VistaADevolver.findViewById(R.id.ListViewUsuarios);
		 
		 AdaptadorUsuarios Adapter = new AdaptadorUsuarios(MySql.ListUsers, ActividadPrincipal.getApplicationContext());
		 
		 ListViewUsers.setAdapter(Adapter);
	  }
	  return VistaADevolver;
   }
}
