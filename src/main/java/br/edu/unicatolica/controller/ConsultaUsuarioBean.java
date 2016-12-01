package br.edu.unicatolica.controller;

import br.edu.unicatolica.bo.UsuarioBO;
import br.edu.unicatolica.entity.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Danrley
 */
@ManagedBean
@ViewScoped
public class ConsultaUsuarioBean implements Serializable {

    private List<Usuario> usuarios;

    @PostConstruct
    public void init() {
        pesquisar();
    }

    public void pesquisar() {
        usuarios = UsuarioBO.getInstance().getUsuarios();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
