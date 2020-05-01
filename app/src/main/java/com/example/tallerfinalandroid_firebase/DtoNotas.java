package com.example.tallerfinalandroid_firebase;

public class DtoNotas {
    private String idUnico;
    private String codigomatricula;
    private String nombre;
    private String nota1;
    private String nota2;
    private String nota3;
    private String notafin;

    public DtoNotas(String idUnico, String codigomatricula, String nombre, String nota1, String nota2, String nota3, String notafin) {

        this.idUnico = idUnico;
        this.codigomatricula = codigomatricula;
        this.nombre = nombre;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.notafin = notafin;
    }

    public String getIdUnico() {
        return idUnico;
    }

    public String getCodigomatricula() {
        return codigomatricula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNota1() {
        return nota1;
    }

    public String getNota2() {
        return nota2;
    }

    public String getNota3() {
        return nota3;
    }

    public String getNotafin() {
        return notafin;
    }
}
