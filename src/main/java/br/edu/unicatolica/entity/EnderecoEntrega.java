package br.edu.unicatolica.entity;

import br.edu.unicatolica.enumeration.UnidadeFederacao;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Danrley
 */
@Embeddable
public class EnderecoEntrega implements Serializable {

    private String logradouro;
    private String numero;
    private String complemento;
    private String cidade;
    private UnidadeFederacao uf;
    private String cep;

    @Column(length = 150)
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Column(length = 150)
    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Column(length = 60)
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public UnidadeFederacao getUf() {
        return uf;
    }

    public void setUf(UnidadeFederacao uf) {
        this.uf = uf;
    }

    @Column(length = 9)
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

}
