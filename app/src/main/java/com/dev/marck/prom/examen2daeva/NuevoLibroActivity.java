package com.dev.marck.prom.examen2daeva;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.dev.marck.prom.examen2daeva.db.DbManager;

public class NuevoLibroActivity extends AppCompatActivity {

    private EditText tfTitulo;
    private EditText tfAutor;
    private EditText tfIsbn;
    private EditText tfEditorial;
    private EditText tfPag;
    private CheckBox cbLeido;

    private DbManager manager;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_nuevo_libro );
//        instanciamos los atributos
        tfAutor = findViewById( R.id.db_nuevo_tf_autor );
        tfTitulo = findViewById( R.id.db_nuevo_tf_titulo );
        tfIsbn = findViewById( R.id.db_nuevo_tf_isbn );
        tfEditorial = findViewById( R.id.db_nuevo_tf_editorial );
        tfPag = findViewById( R.id.db_nuevo_tf_pag );
        cbLeido = findViewById( R.id.db_nuevo_leido );

        manager = new DbManager( this, DbManager.DATABASE_NAME, null, 1 );
    }

    public void clear( View view ) {
        limpiar();
    }

    private void limpiar(){
        tfAutor.setText( null );
        tfEditorial.setText( null );
        tfIsbn.setText( null );
        tfTitulo.setText( null );
        tfPag.setText( null );
        cbLeido.setChecked( false );
    }

    public void volver( View view ) {
        finish();
    }

    public void guardar( View v ){
        int numPag = Integer.parseInt( tfPag.getText().toString() );
        int leido = ( cbLeido.isChecked() )? 1 : 0;
        ContentValues vals = new ContentValues();
        vals.put( DbManager.COL_ISBN, tfIsbn.getText().toString() );
        vals.put( DbManager.COL_TITULO, tfTitulo.getText().toString() );
        vals.put( DbManager.COL_AUTOR, tfAutor.getText().toString() );
        vals.put( DbManager.COL_EDITORIAL, tfEditorial.getText().toString() );
        vals.put( DbManager.COL_NUM_PAG, numPag );
        vals.put( DbManager.COL_LEIDO, leido );
        SQLiteDatabase db = manager.getWritableDatabase();
        db.insert( DbManager.TABLE_NAME, null, vals );
        db.close();
        limpiar();
    }

}
