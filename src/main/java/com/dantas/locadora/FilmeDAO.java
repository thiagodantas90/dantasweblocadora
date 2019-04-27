/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantas.locadora;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thiago
 */
public class FilmeDAO {
    Banco conecta = new Banco();
    private final String CADASTRARFILME = "INSERT INTO FILMES (TITULO, DATA_LANCAMENTO, NOTA, DESCRICAO, QUANTIDADE) VALUES (?,?,?,?,?)";
    
  
    public void cadastrar(ModeloFilme f1) {
        try {
            conecta.conecta();
            PreparedStatement prepararInstrucao;
            prepararInstrucao = conecta.getConexao().prepareStatement(CADASTRARFILME);
            
            prepararInstrucao.setString(1, f1.getTitulo());
            prepararInstrucao.setString(2, f1.getData_lancamento());
            prepararInstrucao.setInt(3, f1.getNota());
            prepararInstrucao.setString(4, f1.getDescricao());
            prepararInstrucao.setInt(5, f1.getQuantidade());
            
            prepararInstrucao.execute();
            
            conecta.desconecta();
        } catch (SQLException ex) {
            Logger.getLogger(FilmeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
