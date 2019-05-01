/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantas.locadora;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Thiago
 */
public class Banco {
    private static final String DRIVER = "org.postgresql.Driver";   
    private static final String URL = "jdbc:postgresql://ec2-54-83-205-27.compute-1.amazonaws.com:5432/d9cpk5q3kb1doh?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";   
    private static final String USERNAME = "vajozrpxbmxspv";   
    private static final String PASSWORD = "74fe188e87cf558ce7f2e41cee8430e2a5b7849eacbda6cce46a4aa9081d75f1";
    
//    private static final String DRIVER = "org.postgresql.Driver";   
//    private static final String URL = "jdbc:postgresql://localhost:5432/dantas";   
//    private static final String USERNAME = "postgres";   
//    private static final String PASSWORD = "1234";
    
    
    private Connection conexao;
	
        public void conecta() {
        try {
            Class.forName(DRIVER); //Carrega o driver (inicializa um objeto da classe org.postgresql.Driver) 
            conexao = DriverManager.getConnection(URL, USERNAME, PASSWORD); //Cria a conexao
            System.out.println("Banco conectado");
            } catch (Exception e) {
           
        }
    }

    //METODO QUE DESCONECTA O BANCO DE DADOS
    public void desconecta() {
        try {
            conexao.close();//fecha a conexao
        } catch (SQLException ex) {
            System.err.println(ex);
            ex.printStackTrace();
        }
    }

    public Connection getConexao() {
        return conexao;
    }
}
