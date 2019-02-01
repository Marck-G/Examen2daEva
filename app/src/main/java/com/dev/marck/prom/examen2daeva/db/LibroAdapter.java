package com.dev.marck.prom.examen2daeva.db;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dev.marck.prom.examen2daeva.R;

public class LibroAdapter extends ArrayAdapter<Libro> {
    public LibroAdapter( @NonNull Context context, int resource, @NonNull Libro[] objects ) {
        super( context, resource, objects );
    }

    @NonNull
    @Override
    public View getView( int position, @Nullable View convertView, @NonNull ViewGroup parent ) {
        LayoutInflater inflater = LayoutInflater.from( getContext() );
        View layout = inflater.inflate( R.layout.lista_libro_item, null );
        ( ( TextView ) layout.findViewById( R.id.libro_item_titulo ) ).setText( getItem( position ).getTitulo() );
        ( ( TextView ) layout.findViewById( R.id.libro_item_autor ) ).setText( getItem( position ).getAutor() );
        return layout;
    }
}
