/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unicatolica.bo;

import br.edu.unicatolica.dao.UsuarioDAO;
import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.filter.UsuarioFilter;
import br.edu.unicatolica.jsf.util.FacesUtil;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Danrley
 */
public class UsuarioBO implements Serializable {

    private static UsuarioBO instance;

    private UsuarioBO() {

    }

    public static UsuarioBO getInstance() {
        if (instance == null) {
            instance = new UsuarioBO();
        }
        return instance;
    }

    public boolean validarUsuario(Usuario usuario) {
        if (usuario.getNome().replace(" ", "").equals("")) {
            FacesUtil.addErrorMessage("O campo 'Nome' não pode estar em branco");
            return false;
        }

        return true;
    }

    public void salvarOuAtualizar(Usuario usuario) {
        if (validarUsuario(usuario)) {
            UsuarioDAO.getInstance().salvarOuAtualizar(usuario);
            if (usuario.getId() == null) {
                FacesUtil.addInfoMessage("Usuário cadastrado com sucesso!");
            } else {
                FacesUtil.addInfoMessage("Usuário atualizado com sucesso!");
            }
        }
    }

    public void remover(Usuario usuario) {
        UsuarioDAO.getInstance().remover(usuario);
    }

    public List<Usuario> getUsuarios(UsuarioFilter usuarioFilter) {
        return UsuarioDAO.getInstance().getUsuarios(usuarioFilter);
    }

}
