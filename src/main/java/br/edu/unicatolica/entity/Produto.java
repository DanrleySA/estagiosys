package br.edu.unicatolica.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Danrley
 */
@Entity
public class Produto implements EntidadeBase, Serializable {

    @Id    
    @GeneratedValue
    private Long id;
    @NotBlank
    @Column(name = "descricao", length = 150, nullable = false)
    private String descricao;
    private Integer qtdEstoque;
    private Double preco;
    private String codigoBarras;

    public Produto() {
    }

    public Produto(Long id, String descricao, Integer qtdEstoque, Double preco, String codigoBarras) {
        this.id = id;
        this.descricao = descricao;
        this.qtdEstoque = qtdEstoque;
        this.preco = preco;
        this.codigoBarras = codigoBarras;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
