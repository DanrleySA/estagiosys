package br.edu.unicatolica.enumeration;

/**
 *
 * @author Danrley
 */
public enum TipoUsuario {
    PROPRIETARIO("Proprietário"), ADMINISTRADOR("Administrador"), VENDEDOR("Vendedor");

    private String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
