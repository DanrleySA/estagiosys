package br.edu.unicatolica.entity;

import br.edu.unicatolica.exception.NegocioException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Danrley
 */
@Entity
public class Produto implements EntidadeBase, Serializable {

    private Long id;
    private String descricao;
    private Integer qtdEstoque;
    private BigDecimal valorUnitario;

    public Produto() {
    }

    public void atualizarEstoque(Integer quantidade) {
        int novaQuantidade = getQtdEstoque() - quantidade;

        if (novaQuantidade < 0) {
            throw new NegocioException("Não existe no estoque " + quantidade
                    + " itens do produto " + getDescricao() + "!");
        }
        setQtdEstoque(novaQuantidade);
    }

    @Id
    @GeneratedValue
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank
    @Column(name = "descricao", length = 150, nullable = false)
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @NotNull
    @Min(0)
    @Column(nullable = false, length = 5)
    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    @NotNull
    @Column(nullable = false, precision = 10, scale = 2)
    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    //<editor-fold defaultstate="collapsed" desc="equals and hashCode">
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
//</editor-fold>
}
