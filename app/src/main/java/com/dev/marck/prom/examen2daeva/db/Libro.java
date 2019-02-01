package com.dev.marck.prom.examen2daeva.db;

public class Libro {
    private String titulo;
    private String autor;
    private String editorial;
    private String isbn;
    private int numPag;
    private boolean leido;

    public Libro( String isbn,String titulo, String autor, String editorial, int numPag, int leido ) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
        this.numPag = numPag;
        this.leido = ( leido == 0 )? false: true;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getNumPag() {
        return numPag;
    }

    public boolean isLeido() {
        return leido;
    }
}
