
package com.dev.marck.prom.examen2daeva;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;

import com.dev.marck.prom.examen2daeva.R;
import com.dev.marck.prom.examen2daeva.db.DbManager;
import com.dev.marck.prom.examen2daeva.db.Libro;
import com.dev.marck.prom.examen2daeva.db.LibroAdapter;

import java.util.ArrayList;

import static com.dev.marck.prom.examen2daeva.db.DbManager.COL_AUTOR;
import static com.dev.marck.prom.examen2daeva.db.DbManager.COL_EDITORIAL;
import static com.dev.marck.prom.examen2daeva.db.DbManager.COL_ISBN;
import static com.dev.marck.prom.examen2daeva.db.DbManager.COL_LEIDO;
import static com.dev.marck.prom.examen2daeva.db.DbManager.COL_NUM_PAG;
import static com.dev.marck.prom.examen2daeva.db.DbManager.COL_TITULO;
import static com.dev.marck.prom.examen2daeva.db.DbManager.TABLE_NAME;

public class ListarLibrosActivity extends AppCompatActivity {
    private ListView lista;
    private RadioButton[] rbOptions;
    private DbManager manager;
    private  LibroAdapter ad;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_listar_libros );
        rbOptions = new RadioButton[3];
        rbOptions[0] = findViewById( R.id.db_listado_rb_leido );
        rbOptions[1] = findViewById( R.id.db_listado_rb_no_leido );
        rbOptions[2] = findViewById( R.id.db_listado_rb_todos );
        lista = findViewById( R.id.db_listado_lista );
        manager = new DbManager( this, DbManager.DATABASE_NAME, null, 1 );
        ad = new LibroAdapter( this, R.layout.lista_libro_item, getLibros() );
        for ( RadioButton r : rbOptions ){
            r.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick( View v ) {
                    ad = new LibroAdapter( ListarLibrosActivity.this, R.layout.lista_libro_item, getLibros() );
                    lista.setAdapter( ad );
                }
            } );
        }
        lista.setAdapter( ad );
        lista.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView< ? > parent, View view, int position, long id ) {
                Intent i = new Intent( ListarLibrosActivity.this, LibroInfoActivity.class );
                i.putExtra( DbManager.COL_ISBN, ad.getItem( position ).getIsbn() );
                i.putExtra( DbManager.COL_TITULO, ad.getItem( position ).getTitulo() );
                i.putExtra( DbManager.COL_AUTOR, ad.getItem( position ).getAutor() );
                i.putExtra( DbManager.COL_EDITORIAL, ad.getItem( position ).getEditorial() );
                i.putExtra( DbManager.COL_NUM_PAG, ad.getItem( position ).getNumPag() );
                i.putExtra( DbManager.COL_LEIDO, ( ad.getItem( position ).isLeido() )? 1 : 0 );
                startActivity( i );
            }
        } );
    }

    private Libro[] getLibros(){
        ArrayList<Libro> tmp = new ArrayList<Libro>();
        String sql = "SELECT " + COL_ISBN + ", " +
                COL_TITULO + "," +
                COL_AUTOR + ", " +
                COL_EDITORIAL + "," +
                COL_NUM_PAG + "," +
                COL_LEIDO + " FROM " + TABLE_NAME;
        if( rbOptions[0].isChecked() )
            sql += " WHERE " + COL_LEIDO + " = 1";
        else if ( rbOptions[1].isChecked() )
            sql += " WHERE " + COL_LEIDO + " = 0";
        SQLiteDatabase db = manager.getReadableDatabase();
        Cursor resul = db.rawQuery( sql, null );
        if ( resul.moveToFirst() )
            do{
                Libro l = new Libro(
                        resul.getString( 0 ),
                        resul.getString( 1 ),
                        resul.getString( 2 ),
                        resul.getString( 3 ),
                        resul.getInt( 4 ),
                        resul.getInt( 5 ) );
                tmp.add( l );
            } while( resul.moveToNext() );
        db.close();

        Libro[] out = new Libro[ tmp.size() ];
        tmp.toArray( out );
        return out;
    }

    public void back( View view ) {
        finish();
    }
}
