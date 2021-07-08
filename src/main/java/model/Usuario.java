/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.BDUsuario;
import java.util.List;

/**
 *
 * @author joseg github.com/JoseGabrielNF
 */
public class Usuario {
    private String id;
    private String email;
    private String login;
    private String senha;
    private String dataNascimento;

    public Usuario() {
    }
    public Usuario(String email, String login, String senha, String dataNascimento) {
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public List <Usuario>lerUsuarios(){
        return BDUsuario.lerUsuarios();
    }
    public void inicializarDados() {
               BDUsuario.start();
    }
    public int cadastrarUsuario(Usuario usuario) {
        return BDUsuario.cadastrar(usuario);
    }
    public int atualizarUsuario(Usuario usuario) {
        return BDUsuario.atualizar(usuario);
    }
    public Usuario buscarUsuario(String id) {
        return BDUsuario.buscarUsuario(id);
    }
    public boolean excluirUsuario(String id) {
        return BDUsuario.excluir(id);
    }
}
