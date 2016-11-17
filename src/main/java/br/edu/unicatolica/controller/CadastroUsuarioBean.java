/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unicatolica.controller;

import br.edu.unicatolica.bo.UsuarioBO;
import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.jsf.util.FacesUtil;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Danrley
 */
@ManagedBean
@ViewScoped
public class CadastroUsuarioBean implements Serializable {
    
    private Usuario usuario;

    public void salvar(){
        UsuarioBO.getInstance().salvarOuAtualizar(usuario);
        FacesUtil.addInfoMessage("Usuário cadastrado com sucesso");
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
