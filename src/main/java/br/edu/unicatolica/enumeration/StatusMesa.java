package br.edu.unicatolica.enumeration;

/**
 *
 * @author Danrley
 */
public enum StatusMesa {
    LIVRE("Livre"), OCUPADA("Ocupada");

    private String descricao;

    StatusMesa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
