package controller;

import java.util.List;
import model.Usuario;

/**
 *
 * @author joseg github.com/JoseGabrielNF
 */
public class Controlador {

    public static void inicializarDados() {
        new Usuario().inicializarDados();
    }

    public static List<Usuario> lerUsuarios() {
        return new Usuario().lerUsuarios();
    }
    public static Usuario buscarUsuario(String id){
        return new Usuario().buscarUsuario(id);
    }

    public static boolean excluir(String id) {
        return new Usuario().excluirUsuario(id);
    }
    public static int atualizarUsuario(String id,String login, String senha1,String senha2,String email, String dataNascimento) {
        if(!senha1.equals(senha2)){
            return 2;
        }
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setDataNascimento(dataNascimento);
        usuario.setEmail(email);
        usuario.setLogin(login);
        usuario.setSenha(senha1);
        return usuario.atualizarUsuario(usuario);
    }
    public static int cadastrarUsuario(String login, String senha1,String senha2, String email, String dataNascimento) {
        if(!senha1.equals(senha2)){
            return 2;
        }
        Usuario usuario = new Usuario();
        usuario.setDataNascimento(dataNascimento);
        usuario.setEmail(email);
        usuario.setLogin(login);
        usuario.setSenha(senha1);
        return usuario.cadastrarUsuario(usuario);
    }
 
}
