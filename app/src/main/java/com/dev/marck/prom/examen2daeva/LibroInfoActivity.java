package com.dev.marck.prom.examen2daeva;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dev.marck.prom.examen2daeva.db.DbManager;
import com.dev.marck.prom.examen2daeva.db.Libro;

public class LibroInfoActivity extends AppCompatActivity {
    private String isbn;
    private DbManager manager;
    private TextView _titulo;
    private TextView _autor;
    private TextView _editorial;
    private TextView _isbn;
    private TextView _numPag;
    private TextView _leido;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_libro_info );
        manager = new DbManager( this, DbManager.DATABASE_NAME, null, 1 );

        _isbn = findViewById( R.id.libro_info_isbn );
        _titulo = findViewById( R.id.libro_info_titulo );
        _autor = findViewById( R.id.libro_info_autor );
        _editorial = findViewById( R.id.libro_info_editorial );
        _numPag = findViewById( R.id.libro_info_pag );
        _leido = findViewById( R.id.libro_info_leido );
//        cogemos datos del intent
        isbn = getIntent().getStringExtra( DbManager.COL_ISBN );
        String titulo = getIntent().getStringExtra( DbManager.COL_TITULO );
        String autor = getIntent().getStringExtra( DbManager.COL_AUTOR );
        String editorial = getIntent().getStringExtra( DbManager.COL_EDITORIAL );
        int numPag = getIntent().getIntExtra( DbManager.COL_NUM_PAG, 0 );
        int leido = getIntent().getIntExtra( DbManager.COL_LEIDO, 0 );
        Libro l = new Libro( isbn, titulo, autor, editorial, numPag, leido );
        rellenar( l );
    }

    public void volver( View view ) {
        finish();
    }

    public void eliminar( View view ) {
        String sql = " DELETE FROM " + DbManager.TABLE_NAME +
                " WHERE " + DbManager.COL_ISBN + " = '" + isbn + "'";
        SQLiteDatabase db = manager.getWritableDatabase();
        db.execSQL( sql );
        db.close();
        finish();
    }

    private void rellenar( Libro l ){
        _autor.setText( l.getAutor() );
        _titulo.setText( l.getTitulo() );
        _editorial.setText( l.getEditorial() );
        _isbn.setText( l.getIsbn() );
        _numPag.setText( l.getNumPag() + " " + getString( R.string.libro_info_pag_sufij) );
        String read = ( l.isLeido() )?getString( R.string.libro_info_leido_true): getString( R.string.libro_info_leido_false);
        Log.e( "TEXTO", read );
        _leido.setText( read );
    }
}
