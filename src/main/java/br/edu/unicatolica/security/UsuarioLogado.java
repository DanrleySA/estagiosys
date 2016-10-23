package br.edu.unicatolica.security;

import java.io.Serializable;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 *
 * @author Danrley
 */
@Named
@RequestScoped
public class UsuarioLogado implements Serializable{

    public String getNomeUsuario() {
        String nome = null;
        UsuarioSistema usuarioLogado = getUsuarioLogado();

        if (usuarioLogado != null) {
            nome = usuarioLogado.getUsuario().getNome();
        }
        return nome;
    }

    private UsuarioSistema getUsuarioLogado() {
        UsuarioSistema usuario = null;
        UsernamePasswordAuthenticationToken auth
                = (UsernamePasswordAuthenticationToken) FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

        if (auth != null && auth.getPrincipal() != null) {
            usuario = (UsuarioSistema) auth.getPrincipal();
        }
        return usuario;
    }
}
