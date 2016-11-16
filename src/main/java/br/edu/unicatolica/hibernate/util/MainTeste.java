package br.edu.unicatolica.hibernate.util;

import br.edu.unicatolica.dao.GrupoDAO;
import br.edu.unicatolica.dao.UsuarioDAO;
import br.edu.unicatolica.entity.Grupo;
import br.edu.unicatolica.entity.Usuario;

/**
 *
 * @author Danrley
 */
public class MainTeste {

    public static void main(String[] args) {
        Grupo g = new Grupo();
        g.setNome("PROPRIETARIOS");
        g.setDescricao("PROPRIETARIOS");
        
        Usuario u = new Usuario();
        u.setEmail("dan@gmail.com");
        u.setNome("Danrley");
        u.setSenha("123");
        u.getGrupos().add(g);
        
        UsuarioDAO.getInstance().salvarOuAtualizar(u);
    }
}
