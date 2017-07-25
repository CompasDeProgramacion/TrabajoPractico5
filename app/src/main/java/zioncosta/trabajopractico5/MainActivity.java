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
   
   /* Esta funcion muestra un Fragment pasado como parametro en un Holder pasado como parametro*/
   public void IrAFragment(Fragment FragmentAPasar, int IdDelHolder)
   {
	  AdministradorDeFragments = getSupportFragmentManager();
	  
	  TransaccionDeFragment = AdministradorDeFragments.beginTransaction();
	  TransaccionDeFragment.replace(IdDelHolder, FragmentAPasar);
	  TransaccionDeFragment.commit();
   }
}
