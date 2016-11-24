package br.edu.unicatolica.hibernate.util;

import br.edu.unicatolica.dao.UsuarioDAO;
import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.enumeration.TipoUsuario;

/**
 *
 * @author Danrley
 */
public class CriaUsuario {

    public static void main(String[] args) {
        Usuario usuario = new Usuario();
        usuario.setEmail("usu1@gmail.com");
        usuario.setNome("Usuario1");
        usuario.setSenha("123");
        usuario.setTipo(TipoUsuario.PROPRIETARIO);
        UsuarioDAO.getInstance().salvarOuAtualizar(usuario);
    }
}
