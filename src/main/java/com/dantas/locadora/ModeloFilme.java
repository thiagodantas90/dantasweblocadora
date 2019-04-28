/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantas.locadora;

/**
 *
 * @author Thiago
 */
public class ModeloFilme {
    private int id;
    private String titulo, descricao;
    private String data_lancamento;
    private int nota;
    private int quantidade;

    public ModeloFilme(int id, String titulo, String decricao, String data_lancamento, int nota, int quantidade) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = decricao;
        this.data_lancamento = data_lancamento;
        this.nota = nota;
        this.quantidade = quantidade;
    }
    public ModeloFilme(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String decricao) {
        this.descricao = decricao;
    }

    public String getData_lancamento() {
        return data_lancamento;
    }

    public void setData_lancamento(String data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
