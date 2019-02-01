package com.dev.marck.prom.examen2daeva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dev.marck.prom.examen2daeva.db.DbManager;
import com.dev.marck.prom.examen2daeva.db.Libro;
import com.dev.marck.prom.examen2daeva.db.LibroAdapter;

import java.util.ArrayList;

public class BusquedaListadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_busqueda_listado );

        ArrayList< Libro > libros = new ArrayList<Libro>();
        Bundle ex = getIntent().getExtras();
        for( int i = 0; i < ex.getStringArrayList( DbManager.COL_ISBN ).size(); i++ ){
            Libro l = new Libro(
                    ex.getStringArrayList( DbManager.COL_ISBN ).get( i ),
                    ex.getStringArrayList( DbManager.COL_TITULO ).get( i ),
                    ex.getStringArrayList( DbManager.COL_AUTOR ).get( i ),
                    ex.getStringArrayList( DbManager.COL_EDITORIAL ).get( i ),
                    ex.getIntegerArrayList( DbManager.COL_NUM_PAG ).get( i ),
                    ex.getIntegerArrayList( DbManager.COL_LEIDO ).get( i )
            );
            libros.add( l );
            ListView lista = findViewById( R.id.db_buscar_listado_lista );
            Libro[] data = new Libro[ libros.size() ];
            libros.toArray( data );
            final LibroAdapter ad = new LibroAdapter( this, R.layout.lista_libro_item, data );
            lista.setAdapter( ad );
            lista.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick( AdapterView< ? > parent, View view, int position, long id ) {
                    Intent i = new Intent( BusquedaListadoActivity.this, LibroInfoActivity.class );
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
    }

    public void volver( View view ) {
        finish();
    }
}
