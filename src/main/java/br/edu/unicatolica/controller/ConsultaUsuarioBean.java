package br.edu.unicatolica.controller;

import br.edu.unicatolica.bo.UsuarioBO;
import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.filter.UsuarioFilter;
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
    private UsuarioFilter usuarioFilter = new UsuarioFilter();

    @PostConstruct
    public void init() {
        pesquisar();
    }

    public void pesquisar() {
        usuarios = UsuarioBO.getInstance().getUsuarios(usuarioFilter);
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public UsuarioFilter getUsuarioFilter() {
        return usuarioFilter;
    }

    public void setUsuarioFilter(UsuarioFilter usuarioFilter) {
        this.usuarioFilter = usuarioFilter;
    }

}
