package com.dantas.locadora;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thiago
 */
@ManagedBean
public class GerenciarFilmes {
    private String titulo, descricao;
    private String data_lancamento;
    private int nota;
    private int quantidade;
    private ArrayList<ModeloFilme> listaDeFilmes;
    private ArrayList<ModeloFilme> cesta;
    
    public ModeloFilme f;
    private FilmeDAO novo = new FilmeDAO();
    
    public String cadastrarFilme(){
        f = new ModeloFilme(titulo, descricao, data_lancamento, nota, quantidade);
        novo.cadastrar(f);
        return "cadastrado";
    }
    public String cancelar(){
        return "cancelar";
    }
    
    public void adicionarCesta(String titulo){
        //cesta.add();
    }
    
    public ArrayList<ModeloFilme> listarFilmes(){
        return novo.consultar();
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

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public ArrayList<ModeloFilme> getListaDeFilmes() {
        return listaDeFilmes;
    }

    public void setListaDeFilmes(ArrayList<ModeloFilme> listaDeFilmes) {
        this.listaDeFilmes = listaDeFilmes;
    }
    
}
