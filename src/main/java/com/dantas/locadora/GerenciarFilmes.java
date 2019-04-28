package com.dantas.locadora;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import com.dantas.locadora.ModeloFilme;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.SessionScoped;

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
//@ApplicationScoped
@SessionScoped
public class GerenciarFilmes {
    private int id;
    private String titulo, descricao;
    private String data_lancamento;
    private int nota;
    private int quantidade;
    private double totalcompra = 0;
    private int tamanho;
    
    private ModeloFilme filmeAtual;
    private ArrayList<Integer> listaIds;
    private ArrayList<ModeloFilme> listaDeFilmes;
    private ArrayList<ModeloFilme> cesta = new ArrayList<ModeloFilme>();
    
    
    private FilmeDAO DAO = new FilmeDAO();
    
    public String cadastrarFilme(){
        listaIds = DAO.listarIds();
        if(!listaIds.contains(filmeAtual.id)){
            DAO.cadastrar(filmeAtual);
            return "cadastrado";
        }else{
            return "jacadastrado";
        }
    }
    public void editar(ModeloFilme fi){
        this.filmeAtual = fi;
    }
    public void salvar(){
        DAO.atualizar(filmeAtual);
    }
    public void cancelar(){
        filmeAtual = new ModeloFilme();
        //return "cancelar";
    }
    
    public void adicionarCesta(ModeloFilme fi){
            cesta.add(fi);
            DAO.alterarQuantidades(fi);         
    }
    public void remover(ModeloFilme ce){
        if(cesta.contains(ce)){
            DAO.devolverItem(ce);
            cesta.remove(ce);
        }
    }
    public void limparCesta(){
        for(int i=0;i<tamanho;i++){
            DAO.devolverItem(cesta.get(i));
        }
        cesta.clear();
    }
    public ArrayList<ModeloFilme> verCesta(){
        return cesta;
    }
    public String finalizar(){
        calcularPreco();
        tamanho = cesta.size();
        cesta.clear();
        return "totalCompra";
    }
    private void calcularPreco(){
        totalcompra = 4.5 * cesta.size();
    }
    public ArrayList<ModeloFilme> listarFilmes(){
        return DAO.consultar();
    }

    public ArrayList<ModeloFilme> getCesta() {
        return cesta;
    }

    public void setCesta(ArrayList<ModeloFilme> cesta) {
        this.cesta = cesta;
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

    public ModeloFilme getFilmeAtual() {
        return filmeAtual;
    }

    public void setFilmeAtual(ModeloFilme filmeAtual) {
        this.filmeAtual = filmeAtual;
    }

    public double getTotalcompra() {
        return totalcompra;
    }

    public void setTotalcompra(double totalcompra) {
        this.totalcompra = totalcompra;
    }

    public int getTamanho() {
        return tamanho = cesta.size();
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
    
}
