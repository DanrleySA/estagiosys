package br.edu.unicatolica.controller;

import br.edu.unicatolica.bo.ProdutoBO;
import br.edu.unicatolica.entity.Produto;
import br.edu.unicatolica.filter.ProdutoFilter;
import br.edu.unicatolica.jsf.util.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Danrley
 */
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {

    private Produto produto;
    private ProdutoFilter produtoFilter;
    private List<Produto> produtos;
    private Produto produtoSelecionado;

    @PostConstruct
    public void init() {
        pesquisar();
    }

    public ProdutoBean() {
        limpar();
    }

    public void salvar() {
        if (produto.getId() == null) {
            ProdutoBO.getInstance().salvarOuAtualizar(produto);

            FacesUtil.addInfoMessage("Produto cadastrado com sucesso!");
        } else {
            ProdutoBO.getInstance().salvarOuAtualizar(produto);

            FacesUtil.addInfoMessage("Produto atualizado com sucesso!");
        }
        limpar();
    }

    public void remover() {
        ProdutoBO.getInstance().remover(produtoSelecionado);
        produtos.remove(produtoSelecionado);
        FacesUtil.addInfoMessage("Produto excluído com sucesso!");
    }

    public void pesquisar() {
        produtos = ProdutoBO.getInstance().getProdutos(getProdutoFilter());
    }

    public void limpar() {
        produto = new Produto();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ProdutoFilter getProdutoFilter() {
        if (produtoFilter == null) {
            produtoFilter = new ProdutoFilter();
        }
        return produtoFilter;
    }

    public void setProdutoFilter(ProdutoFilter produtoFilter) {
        this.produtoFilter = produtoFilter;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

}
