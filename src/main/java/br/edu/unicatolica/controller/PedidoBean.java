package br.edu.unicatolica.controller;

import br.edu.unicatolica.bo.ProdutoBO;
import br.edu.unicatolica.dao.ProdutoDAO;
import br.edu.unicatolica.entity.Item;
import br.edu.unicatolica.entity.Produto;
import br.edu.unicatolica.filter.ProdutoFilter;
import br.edu.unicatolica.jsf.util.FacesUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.DragDropEvent;

/**
 *
 * @author Danrley
 */
@ManagedBean
@ViewScoped
public class PedidoBean implements Serializable {

    private ProdutoFilter produtoFilter;
    private List<Produto> produtos;
    private List<Item> itens;
    private Produto produtoSelecionado;
    private List<Produto> produtosAux;
    private Item itemSelecionado;

    @PostConstruct
    public void init() {
        pesquisar();
        produtosAux = produtos;
    }

    public void onDrop(DragDropEvent ddEvent) {
        Produto produto = ((Produto) ddEvent.getData());

        Item item = new Item();
        item.setProduto(produto);
        item.setQuantidade(1);
        item.setValorUnitario(produto.getValorUnitario());

        itens.add(item);
        produtosAux.remove(produto);
        produtos.remove(produto);

        FacesUtil.addInfoMessage("Produto adicionado ao carrinho!");
    }

    public void adicionar(Produto produtoSelecionado) {
        Item item = new Item();
        item.setProduto(produtoSelecionado);
        item.setQuantidade(1);
        item.setValorUnitario(produtoSelecionado.getValorUnitario());
        itens.add(item);
    }

    public void remover(Item item) {
        itens.remove(item);
        produtos.add(item.getProduto());
        
        FacesUtil.addInfoMessage("Item removido do carrinho!");
    }

    public void atualizarListaProdutosFiltrados() {
        List<Produto> aux = new ArrayList<>();
        for (Produto produto : produtosAux) {
            if (produto.getDescricao().contains(produtoFilter.getDescricao())) {
                aux.add(produto);
            }
        }
        produtos = aux;
    }

    public void pesquisar() {
        produtos = ProdutoBO.getInstance().getProdutos(getProdutoFilter());
    }

    public ProdutoFilter getProdutoFilter() {
        if (produtoFilter == null) {
            produtoFilter = new ProdutoFilter();
        }
        return produtoFilter;
    }

    public void atualizar(Item item) {

        BigDecimal valor = item.getProduto().getValorUnitario();
        Integer outroValor = item.getQuantidade();
        BigDecimal resultado = valor.multiply(BigDecimal.valueOf(outroValor.longValue()));

        item.setValorUnitario(resultado);
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

    public List<Item> getItens() {
        if (itens == null) {
            itens = new ArrayList<>();
        }
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

    public List<Produto> getProdutosAux() {
        return produtosAux;
    }

    public void setProdutosAux(List<Produto> produtosAux) {
        this.produtosAux = produtosAux;
    }

    public Item getItemSelecionado() {
        return itemSelecionado;
    }

    public void setItemSelecionado(Item itemSelecionado) {
        this.itemSelecionado = itemSelecionado;
    }

}
