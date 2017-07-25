package zioncosta.trabajopractico5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrador on 13/7/2017.
 */

public class AdaptadorUsuarios extends BaseAdapter
{
   private ArrayList<Usuario> _ListUsers;
   private Context _Context;
   
   public AdaptadorUsuarios(ArrayList<Usuario> ListaUsuariosAUsar, Context ContextoAUsar)
   {
	  _ListUsers = ListaUsuariosAUsar;
	  _Context = ContextoAUsar;
   }
   
   public int getCount()
   {
	  return _ListUsers.size();
   }
   
   public Usuario getItem(int PosAObt)
   {
	  Usuario UsuarioADevolver;
	  UsuarioADevolver = _ListUsers.get(PosAObt);
	  return UsuarioADevolver;
   }
   
   public long getItemId(int PosAObt)
   {
	  long Id = _ListUsers.get(PosAObt).Id;
	  return Id;
   }
   
   public View getView(int PosicionActual, View VistaActual, ViewGroup GrupoActual)
   {
	  View VistaADevolver;
	  LayoutInflater InfladorDeLayouts = (LayoutInflater) _Context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  VistaADevolver = InfladorDeLayouts.inflate(R.layout.listview_usuarios_detalles, GrupoActual, false);
	  String Capo = EmojiBruh(0x1F609);
	  String NoCapo = EmojiBruh(0x1F612);
	  
	  TextView UserName = (TextView) VistaADevolver.findViewById(R.id.TxtUserName);
	  TextView CantHamburguesas = (TextView) VistaADevolver.findViewById(R.id.TxtCantHamburguesas);
	  TextView McMejor = (TextView) VistaADevolver.findViewById(R.id.TxtMcMejorQueBurguer);
	  TextView UltimaConexion = (TextView) VistaADevolver.findViewById(R.id.TxtFechaUltimaConexion);
	  
	  Usuario UsuarioGenial = new Usuario();
	  UsuarioGenial = getItem(PosicionActual);
	  
	  UserName.setText(UsuarioGenial.UserName);
	  String McBetter = UsuarioGenial.McMejor ? "Usted, señor, sabe lo que es una buena hamburguesa " + Capo : "Ronald se está revolcando en su tumba " + NoCapo;
	  McMejor.setText(McBetter);
	  CantHamburguesas.setText(UsuarioGenial.CantHamburguesas);
	  UltimaConexion.setText(UsuarioGenial.FechaIngreso);
	  
	  return VistaADevolver;
   }
   
   public String EmojiBruh(int Unicode)
   {
	  return new String(Character.toChars(Unicode));
   }
}
