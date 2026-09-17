package com.laboratorio.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Libro {
    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    @JsonProperty("añoPublicacion")
    private Integer añoPublicacion;

    public Libro() {}

    public Libro(Long id, String titulo, String autor, String isbn, Integer añoPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.añoPublicacion = añoPublicacion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Integer getAnioPublicacion() { return añoPublicacion; }
    public void setAnioPublicacion(Integer añoPublicacion) { this.añoPublicacion = añoPublicacion; }
}