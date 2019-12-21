/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.sql.Date;

/**
 *
 * @author Ludy
 */
public class Aluno {
    
    private int id;
    private String dataNasc;
    private String nome;
    private String bairro;
    private String escola;
    private String curso;
    private double media;

    
    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Aluno(String dataNasc, String nome, String bairro, String escola, double media) {
        this.dataNasc = dataNasc;
        this.nome = nome;
        this.bairro = bairro;
        this.escola = escola;
        this.media = media;
    }

    public Aluno() {
    }
    
    
    

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEscola() {
        return escola;
    }


    public void setEscola(String escola) {
        this.escola = escola;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    
    }

  
    
    
    
}
