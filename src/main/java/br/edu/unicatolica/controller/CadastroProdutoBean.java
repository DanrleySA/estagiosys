package br.edu.unicatolica.controller;

import br.edu.unicatolica.bo.ProdutoBO;
import br.edu.unicatolica.entity.Produto;
import br.edu.unicatolica.jsf.util.FacesUtil;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Danrley
 */
@ManagedBean
@ViewScoped
public class CadastroProdutoBean implements Serializable {

    private Produto produto;

    public CadastroProdutoBean() {
        limpar();
    }

    public void salvar() {
        if (produto.getId() == null) {
            if (ProdutoBO.getInstance().salvarOuAtualizar(produto)) {
                FacesUtil.addInfoMessage("Produto cadastrado com sucesso!");
                limpar();
            }
        } else {
            if (ProdutoBO.getInstance().salvarOuAtualizar(produto)) {
                FacesUtil.addInfoMessage("Produto atualizado com sucesso!");
                limpar();
            }
        }
    }

    private void limpar() {
        produto = new Produto();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
