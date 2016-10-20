package br.edu.unicatolica.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Danrley
 */
@Entity
public class Usuario implements Serializable {

    @Id
    private Long id;
    @Column(name = "enable", columnDefinition = "BOOLEAN")
    private Boolean enable;
    @Column(unique = true)
    private String username;
    @Column(length = 15)
    private String senha;
    @OneToMany
    private List<Autorizacao> autorizacoes;
    private Usuario tipo_usuario;

    public Usuario() {
    }

    public Usuario(Long id, Boolean status, String login, String senha, List<Autorizacao> autorizacoes) {
        this.id = id;
        this.enable = status;
        this.username = login;
        this.senha = senha;
        this.autorizacoes = autorizacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return enable;
    }

    public void setStatus(Boolean status) {
        this.enable = status;
    }

    public String getLogin() {
        return username;
    }

    public void setLogin(String login) {
        this.username = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Autorizacao> getAutorizacoes() {
        return autorizacoes;
    }

    public void setAutorizacoes(List<Autorizacao> autorizacoes) {
        this.autorizacoes = autorizacoes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
