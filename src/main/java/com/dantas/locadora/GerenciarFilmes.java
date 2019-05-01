package com.dantas.locadora;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import com.dantas.locadora.ModeloFilme;
import java.util.Date;
import javax.faces.bean.SessionScoped;

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
    private java.util.Date data_lancamento = new java.util.Date();
    private java.sql.Date dataSql = new java.sql.Date(data_lancamento.getTime());
    private double totalcompra = 0;
    String dataFormatada;
    
    private ModeloFilme filmeAtual = new ModeloFilme();
    private ArrayList<Integer> listaIds;
    private ArrayList<ModeloFilme> listaDeFilmes;
    private ArrayList<ModeloFilme> cesta = new ArrayList<ModeloFilme>();
    private FilmeDAO DAO = new FilmeDAO();
        
    public String cadastrarFilme(){
        filmeAtual.setData_lancamento(dataSql);
        listaIds = DAO.listarIds();
        if(!listaIds.contains(filmeAtual.getId())){
            DAO.cadastrar(filmeAtual);
            limparCampos();
            return "cadastrado";
        }else{
            return "jacadastrado";
        }
    }
    public void editar(ModeloFilme fi){
        this.filmeAtual = fi;
    }
    public void salvar(){
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
        data_lancamento = new Date("__/__/___");
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

    public java.sql.Date getDataSql() {
        return dataSql;
    }

    public void setDataSql(java.sql.Date dataSql) {
        this.dataSql = dataSql;
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
