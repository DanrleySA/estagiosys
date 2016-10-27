package br.edu.unicatolica.dao;

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
        g.setDescricao("Proprietarios");
        
        Usuario u = new Usuario();
        u.setEmail("dan@gmail.com");
        u.setNome("Danrley");
        u.setSenha("123");
        u.getGrupos().add(g);
        
        GrupoDAO.getInstance().salvarOuAtualizar(g);
        UsuarioDAO.getInstance().salvarOuAtualizar(u);
        
    }
}
