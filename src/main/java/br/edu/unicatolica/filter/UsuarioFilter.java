package br.edu.unicatolica.filter;

import java.io.Serializable;

/**
 *
 * @author Danrley
 */
public class UsuarioFilter implements Serializable {

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
