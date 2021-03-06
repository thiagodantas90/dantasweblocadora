/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantas.locadora;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;

/**
 *
 * @author Thiago
 */
public class FilmeDAO {
    private Banco conecta = new Banco();
    private final String CADASTRARFILME = "INSERT INTO FILMES (TITULO, NOTA, DESCRICAO, QUANTIDADE, DATA_LANCAMENTO) VALUES (?,?,?,?,?)";
    private final String LISTARFILMES = "SELECT * FROM FILMES ORDER BY ID_FILMES";
    private final String ALTERARQUANTIDADE = "UPDATE FILMES SET QUANTIDADE = QUANTIDADE - 1 WHERE ID_FILMES = (?)";
    private final String DEVOLVERITEM = "UPDATE FILMES SET QUANTIDADE = QUANTIDADE + 1 WHERE ID_FILMES = (?)";
    private final String ATUALIZARFILME = "UPDATE FILMES SET TITULO = ?,  NOTA = ?,DESCRICAO = ?, QUANTIDADE = ?, DATA_LANCAMENTO = ? WHERE ID_FILMES = ?";
    private final String LISTARIDS = "SELECT ID_FILMES FROM FILMES";
  
    public void cadastrar(ModeloFilme f1) {
        try {
            conecta.conecta();
            PreparedStatement prepararInstrucao;
            prepararInstrucao = conecta.getConexao().prepareStatement(CADASTRARFILME);
            
            prepararInstrucao.setString(1, f1.getTitulo());
            prepararInstrucao.setInt(2, f1.getNota());
            prepararInstrucao.setString(3, f1.getDescricao());
            prepararInstrucao.setInt(4, f1.getQuantidade());
            prepararInstrucao.setDate(5, f1.getData_lancamento());
            
            prepararInstrucao.execute();
            
            conecta.desconecta();
        } catch (SQLException ex) {
            Logger.getLogger(FilmeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<ModeloFilme> consultar(){
        ArrayList<ModeloFilme> lista = new ArrayList<ModeloFilme>();
                
        try {
            conecta.conecta();
            PreparedStatement prepararInstrucao;
            prepararInstrucao = conecta.getConexao().prepareStatement(LISTARFILMES);
            ResultSet rs = prepararInstrucao.executeQuery();
            while(rs.next()){
                ModeloFilme f = new ModeloFilme(rs.getInt("ID_FILMES"),rs.getString("TITULO"), rs.getString("DESCRICAO"),rs.getDate("DATA_LANCAMENTO"),rs.getInt("NOTA"),rs.getInt("QUANTIDADE"));
                lista.add(f);
            }
            conecta.desconecta();
        } catch (SQLException ex) {
            Logger.getLogger(FilmeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return lista;
    }

    public void alterarQuantidades(ModeloFilme fi) {
        try {
            conecta.conecta();
            PreparedStatement prepararInstrucao;
            prepararInstrucao = conecta.getConexao().prepareStatement(ALTERARQUANTIDADE);
            prepararInstrucao.setInt(1, fi.getId());

            prepararInstrucao.execute();
  
            conecta.desconecta();
        } catch (SQLException ex) {
            Logger.getLogger(FilmeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizar(ModeloFilme fi) {
      
        try {
            conecta.conecta();
            PreparedStatement prepararInstrucao;
            prepararInstrucao = conecta.getConexao().prepareStatement(ATUALIZARFILME);
            
            prepararInstrucao.setString(1, fi.getTitulo());
            prepararInstrucao.setInt(2, fi.getNota());
            prepararInstrucao.setString(3, fi.getDescricao());
            prepararInstrucao.setInt(4, fi.getQuantidade());
            prepararInstrucao.setDate(5, fi.getData_lancamento());
            prepararInstrucao.setInt(6, fi.getId());
            prepararInstrucao.executeUpdate();
                        
            conecta.desconecta();
        } catch (SQLException ex) {
            Logger.getLogger(FilmeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   public ArrayList<Integer> listarIds() {
        ArrayList<Integer> lista = new ArrayList<Integer>();
        int id;
        try {
            conecta.conecta();
            PreparedStatement prepararInstrucao;
            prepararInstrucao = conecta.getConexao().prepareStatement(LISTARIDS);
                    
            ResultSet rs = prepararInstrucao.executeQuery();
            
            while (rs.next()) {                
                id  = (rs.getInt("ID_FILMES"));
                lista.add(id);
            }
            conecta.desconecta();
            
        } catch (SQLException ex) {
            Logger.getLogger(FilmeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return lista;
    }

    void devolverItem(ModeloFilme ce) {
         try {
            conecta.conecta();
            PreparedStatement prepararInstrucao;
            prepararInstrucao = conecta.getConexao().prepareStatement(DEVOLVERITEM);
            prepararInstrucao.setInt(1, ce.getId());

            prepararInstrucao.execute();
  
            conecta.desconecta();
        } catch (SQLException ex) {
            Logger.getLogger(FilmeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
