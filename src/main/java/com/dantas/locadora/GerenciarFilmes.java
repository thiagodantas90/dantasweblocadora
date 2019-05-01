package com.dantas.locadora;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import com.dantas.locadora.ModeloFilme;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Thiago
 */
@ManagedBean
//@ApplicationScoped
@SessionScoped
public class GerenciarFilmes {
    private int id, nota,quantidade,tamanho;
    private String titulo, descricao;
    private java.util.Date data_lancamento = new Date();
    private java.sql.Date dataSql;
    private double totalcompra = 0;
       
    private ModeloFilme filmeAtual = new ModeloFilme();
    private ArrayList<Integer> listaIds;
    private ArrayList<ModeloFilme> listaDeFilmes;
    private ArrayList<ModeloFilme> cesta = new ArrayList<ModeloFilme>();
    private FilmeDAO DAO = new FilmeDAO();
    
    private java.sql.Date converteData(){
        java.sql.Date dataSql = new java.sql.Date(filmeAtual.getData_lancamento().getTime());
        return dataSql;
    }
    public void cadastrarFilme(){
        dataSql = new java.sql.Date(data_lancamento.getTime());
        filmeAtual.setData_lancamento(dataSql);
        listaIds = DAO.listarIds();
        if(!listaIds.contains(filmeAtual.getId())){
            DAO.cadastrar(filmeAtual);
            limparCampos();
             FacesContext.getCurrentInstance().addMessage("y:cad", new FacesMessage("Já Cadastrado"));
        }else{
             FacesContext.getCurrentInstance().addMessage("y:cad", new FacesMessage("Já Cadastrado"));
        }
    }
    public void editar(ModeloFilme fi){
        data_lancamento = fi.getData_lancamento();
        this.filmeAtual = fi;
    }
    public void salvar(){
        dataSql = new java.sql.Date(data_lancamento.getTime());
        filmeAtual.setData_lancamento(dataSql);
        DAO.atualizar(filmeAtual);
        limparCampos();
    }
    public void cancelar(){
        limparCampos();
    }
    
    public void adicionarCesta(ModeloFilme fi){
            cesta.add(fi);
            atriTamanho();
            DAO.alterarQuantidades(fi);         
    }
    public void remover(ModeloFilme ce){
        if(cesta.contains(ce)){
            DAO.devolverItem(ce);
            cesta.remove(ce);
            atriTamanho();
        }
    }
    public void limparCesta(){
        for(int i=0;i<cesta.size();i++){
            DAO.devolverItem(cesta.get(i));
        }
        cesta.clear();
        atriTamanho();
    }
    public ArrayList<ModeloFilme> verCesta(){
        return cesta;
    }
    public String finalizar(){
        calcularPreco();
        atriTamanho();
        cesta.clear();
        return "totalCompra";
    }
    private void calcularPreco(){
        totalcompra = 4.5 * cesta.size();
    }
    public ArrayList<ModeloFilme> listarFilmes(){
        return DAO.consultar();
    }
    
    private void atriTamanho() {
        tamanho = cesta.size();
    }

    private void limparCampos() {
        filmeAtual = new ModeloFilme();
        
    }
    // get e set

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
//
//    public Date getData_lancamento() {
//        return data_lancamento;
//    }
//
//    public void setData_lancamento(Date data_lancamento) {
//        this.data_lancamento = data_lancamento;
//    }

    public Date getData_lancamento() {
        return data_lancamento;
    }

    public void setData_lancamento(Date data_lancamento) {
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
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
  
}
