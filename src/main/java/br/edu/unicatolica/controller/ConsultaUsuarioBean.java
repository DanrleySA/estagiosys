package br.edu.unicatolica.controller;

import br.edu.unicatolica.bo.UsuarioBO;
import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.filter.UsuarioFilter;
import br.edu.unicatolica.jsf.util.FacesUtil;
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
    private Usuario usuarioSelecionado;

    public void pesquisar() {
        usuarios = UsuarioBO.getInstance().getUsuarios(usuarioFilter);
    }

    public void remover() {
        UsuarioBO.getInstance().remover(usuarioSelecionado);
        usuarios.remove(usuarioSelecionado);
        FacesUtil.addInfoMessage("Usuário removido com sucesso!");

    }
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">

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

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }
//</editor-fold>

}
