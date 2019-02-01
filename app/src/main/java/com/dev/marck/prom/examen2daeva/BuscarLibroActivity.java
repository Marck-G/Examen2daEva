package com.dev.marck.prom.examen2daeva;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.marck.prom.examen2daeva.db.DbManager;

import java.util.ArrayList;

import static com.dev.marck.prom.examen2daeva.db.DbManager.COL_AUTOR;
import static com.dev.marck.prom.examen2daeva.db.DbManager.COL_EDITORIAL;
import static com.dev.marck.prom.examen2daeva.db.DbManager.COL_ISBN;
import static com.dev.marck.prom.examen2daeva.db.DbManager.COL_LEIDO;
import static com.dev.marck.prom.examen2daeva.db.DbManager.COL_NUM_PAG;
import static com.dev.marck.prom.examen2daeva.db.DbManager.COL_TITULO;
import static com.dev.marck.prom.examen2daeva.db.DbManager.TABLE_NAME;

public class BuscarLibroActivity extends AppCompatActivity {

    private RadioButton titulo;
    private RadioButton autor;
    private RadioButton editorial;
    private RelativeLayout bqLayout;
    private EditText busqueda;
    private TextView busquedaInfo;
    private DbManager manager;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_buscar_libro );

        titulo = findViewById( R.id.db_buscar_rb_titulo );
        autor = findViewById( R.id.db_buscar_rb_autor );
        editorial = findViewById( R.id.db_buscar_rb_editorial );
        busqueda = findViewById( R.id.db_buscar_tf_buqueda );
        busquedaInfo = findViewById( R.id.db_buscar_option );
        bqLayout = findViewById( R.id.db_buscar_rlL );
        bqLayout.setVisibility( View.INVISIBLE );
        titulo.setOnClickListener( event() );
        editorial.setOnClickListener( event() );
        autor.setOnClickListener( event() );
        manager = new DbManager( this, DbManager.DATABASE_NAME, null, 1 );

    }

    private String getColum(){
        if( editorial.isChecked() )
            return DbManager.COL_EDITORIAL;
        if( autor.isChecked() )
            return DbManager.COL_AUTOR;
        return DbManager.COL_TITULO;
    }

    private View.OnClickListener event(){
        return new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                bqLayout.setVisibility( View.VISIBLE );
                busquedaInfo.setText( getColum().toUpperCase() );
            }
        };
    }

    public void buscar( View view ) {
        SQLiteDatabase db = manager.getReadableDatabase();
        String data = busqueda.getText().toString();
        String sql = "SELECT " + COL_ISBN + ", " +
                COL_TITULO + "," +
                COL_AUTOR + ", " +
                COL_EDITORIAL + "," +
                COL_NUM_PAG + "," +
                COL_LEIDO + " FROM " + TABLE_NAME +
                " WHERE " + getColum() + " = '" + data + "'" ;
        Cursor r = db.rawQuery( sql, null );
        if( titulo.isChecked() ) {
            Intent i = new Intent( this, LibroInfoActivity.class );
            if( r.moveToFirst() ){
                i.putExtra( COL_ISBN,  r.getString( 0 ) );
                i.putExtra( COL_TITULO,  r.getString( 1 ) );
                i.putExtra( COL_AUTOR,  r.getString( 2 ) );
                i.putExtra( COL_EDITORIAL,  r.getString( 3 ) );
                i.putExtra( COL_NUM_PAG,  r.getInt( 4 ) );
                i.putExtra( COL_LEIDO,  r.getInt( 5 ) );
                r.close();
                db.close();
                startActivity( i );

            }
        }else{
            ArrayList< String > isbns = new ArrayList<String>();
            ArrayList< String > titulos = new ArrayList<String>();
            ArrayList< String > autores = new ArrayList<String>();
            ArrayList< String > editoriales = new ArrayList<String>();
            ArrayList< Integer > numPags = new ArrayList<Integer>();
            ArrayList< Integer > leidos = new ArrayList<Integer>();
            if( r.moveToFirst() )
                do {
                    isbns.add( r.getString( 0 ) );
                    titulos.add( r.getString( 1 ) );
                    autores.add( r.getString( 2 ) );
                    editoriales.add( r.getString( 3 ) );
                    numPags.add( r.getInt( 4 ) );
                    leidos.add( r.getInt( 5 ) );

                } while ( r.moveToNext() );
            r.close();
            db.close();
            Intent i = new Intent( this, BusquedaListadoActivity.class );
            i.putExtra( COL_ISBN, isbns );
            i.putExtra( COL_TITULO, titulos );
            i.putExtra( COL_AUTOR, autores );
            i.putExtra( COL_EDITORIAL, editoriales );
            i.putExtra( COL_NUM_PAG, numPags );
            i.putExtra( COL_LEIDO, leidos );
            startActivity( i );
        }
    }
}
