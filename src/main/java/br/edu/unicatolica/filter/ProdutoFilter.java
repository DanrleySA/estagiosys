package br.edu.unicatolica.filter;

import java.io.Serializable;

/**
 *
 * @author Danrley
 */
public class ProdutoFilter implements Serializable {

    private String descricao;

    //<editor-fold defaultstate="collapsed" desc="get and set">
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
//</editor-fold>
}
