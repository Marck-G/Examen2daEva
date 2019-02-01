package com.dev.marck.prom.examen2daeva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }


    public void menuLibros( View v ){
        Intent i = new Intent( this, BaseDatosMenuActivity.class );
        startActivity( i );
    }

    public void salir( View view ) {
        finish();
    }
}
