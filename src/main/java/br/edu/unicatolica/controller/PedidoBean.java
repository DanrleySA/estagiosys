package br.edu.unicatolica.controller;

import br.edu.unicatolica.bo.ProdutoBO;
import br.edu.unicatolica.dao.ProdutoDAO;
import br.edu.unicatolica.entity.Item;
import br.edu.unicatolica.entity.Pedido;
import br.edu.unicatolica.entity.Produto;
import br.edu.unicatolica.filter.ProdutoFilter;
import br.edu.unicatolica.jsf.util.FacesUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
    private Pedido pedido = new Pedido();

    @PostConstruct
    public void init() {
        pesquisar();
        produtosAux = produtos;
        pedido.setValorTotal(new BigDecimal(0.00D));
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

        pedido.setValorTotal((itens.size() == 1) ? produto.getValorUnitario() : pedido.getValorTotal().add(produto.getValorUnitario()));
        FacesUtil.addInfoMessage("Produto adicionado ao carrinho!");
    }

    public void remover(Item item) {
        int index = 0;
        for (Item t : itens) {
            if (t.getProduto().equals(item.getProduto())) {
                break;
            }
            index++;
        }
        System.out.println(itens.get(index).getProduto().getDescricao());
        itens.remove(index);
        produtos.add(item.getProduto());
        pedido.setValorTotal(pedido.getValorTotal().subtract(item.getValorUnitario()));
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
        BigDecimal valorAntigo = item.getValorUnitario();
        item.setValorUnitario(item.getProduto().getValorUnitario().multiply(new BigDecimal(item.getQuantidade())));

        if (item.getValorUnitario().compareTo(valorAntigo) == 1) {
            pedido.setValorTotal(pedido.getValorTotal().add(item.getValorUnitario().subtract(valorAntigo)));
        } else {
            pedido.setValorTotal(pedido.getValorTotal().subtract(valorAntigo.subtract(item.getValorUnitario())));
        }
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

    public Pedido getPedido() {
        if (pedido == null) {
            pedido = new Pedido();
            pedido.setValorTotal(new BigDecimal(0.00D));
        }
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}
