package com.dev.marck.prom.examen2daeva;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dev.marck.prom.examen2daeva.db.DbManager;

public class BaseDatosMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_base_datos_menu );
//        DbManager db = new DbManager( this, DbManager.DATABASE_NAME, null, 1 );
//        Cursor d = db.getReadableDatabase().rawQuery( "Select * from " + DbManager.TABLE_NAME, null );
//        for ( int i = 0; i < d.getColumnCount(); i++ ){
//            Log.e( "COL", d.getColumnName( i ) );
//        }
    }

    public void nuevo( View v ){
        Intent i = new Intent( this, NuevoLibroActivity.class );
        startActivity( i );
    }

    public void listado( View v ){
        Intent i = new Intent( this, ListarLibrosActivity.class );
        startActivity( i );
    }

    public void buscar( View v ){
        Intent i = new Intent( this, BuscarLibroActivity.class );
        startActivity( i );
    }

    public void salir( View view ) {
        finish();
    }
}
