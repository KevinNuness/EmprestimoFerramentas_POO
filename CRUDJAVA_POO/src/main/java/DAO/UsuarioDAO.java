/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author apena
 */
public class UsuarioDAO {
    
    PreparedStatement pstm;
    Connection conn;
    ResultSet rs;
    ArrayList<UsuarioDTO> lista = new ArrayList<>();

    public ResultSet autenticacaoUsuario(UsuarioDTO objusuarioDTO) {
        conn = new ConexaoDAO().conectaBD();

        try {
            String sql = "select * from usuario where nome_usuario = ? and senha_usuario = ? ";
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, objusuarioDTO.getNome_usuario());
            pstm.setString(2, objusuarioDTO.getSenha_usuario());

            rs = pstm.executeQuery();
            return rs;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO Autenticação:  " + erro);
            return null;
        }

    }

    public void cadastrarUsuario(UsuarioDTO objusuarioDTO) {
        String sql = "insert into usuario (nome_usuario,senha_usuario) values (?, ?)";

        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,objusuarioDTO.getNome_usuario());
            pstm.setString(2,objusuarioDTO.getSenha_usuario());
             
            pstm.execute();
            pstm.close();
 
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO Cadastrar:  " + erro);
        }
    }
    public ArrayList<UsuarioDTO> pesquisarUsuario(){
        String sql="select * from usuario";
        conn = new ConexaoDAO().conectaBD();
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                UsuarioDTO objusuariodto = new UsuarioDTO();
                objusuariodto.setId_usuario(rs.getInt("id_usuario"));
                objusuariodto.setNome_usuario(rs.getString("nome_usuario"));
                objusuariodto.setSenha_usuario(rs.getString("senha_usuario"));
                
                lista.add(objusuariodto);
            }
            
        } catch (SQLException erro) {
            
        
             JOptionPane.showMessageDialog(null, "UsuarioDAO Pesquisar: " + erro);
    }
        return lista;
}
    public void alterarUsuario(UsuarioDTO objusuarioDTO){
        String sql = "update usuario set nome_usuario = ? , senha_usuario = ? where id_usuario = ?";
        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,objusuarioDTO.getNome_usuario());
            pstm.setString(2,objusuarioDTO.getSenha_usuario());
            pstm.setInt(3,objusuarioDTO.getId_usuario());
             
            pstm.execute();
            pstm.close();
 
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO Alterar:  " + erro);
        }
    }
    
    public void ExcluirUsuario(UsuarioDTO objusuarioDTO){
        String sql = "delete from usuario where id_usuario = ?";
       
        conn = new ConexaoDAO().conectaBD();

        try {
            
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1 ,objusuarioDTO.getId_usuario());
             
            pstm.execute();
            pstm.close();
 
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO Alterar:  " + erro);
        }
    }
   public ResultSet ListarCargo(){
       conn = new ConexaoDAO().conectaBD();
       String sql = "SELECT * FROM cargo ORDER BY descricao_cargo";
       
       try {
           pstm = conn.prepareStatement(sql);
           return pstm.executeQuery();
       } catch (SQLException erro){
           JOptionPane.showMessageDialog(null,"UsuarioDAO ListarCargo: " + erro);
           return null;
       }
       
   } 
}

