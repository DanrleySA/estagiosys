package br.edu.unicatolica.controller;

import br.edu.unicatolica.bo.PedidoBO;
import br.edu.unicatolica.bo.ProdutoBO;
import br.edu.unicatolica.dao.UsuarioDAO;
import br.edu.unicatolica.entity.Item;
import br.edu.unicatolica.entity.Pedido;
import br.edu.unicatolica.entity.Produto;
import br.edu.unicatolica.filter.ProdutoFilter;
import br.edu.unicatolica.jsf.util.FacesUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.DragDropEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

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
    private List<Produto> produtosAux;
    private Pedido pedido;

    @PostConstruct
    public void init() {
        pesquisar();
        produtosAux = produtos;
    }

    public void salvar() {
        try {
            for (Item t : itens) {
                t.setPedido(pedido);
                t.getProduto().setQtdEstoque(t.getProduto().getQtdEstoque() - t.getQuantidade());
                ProdutoBO.getInstance().salvarOuAtualizar(t.getProduto());
            }
            pedido.setItens(itens);
            PedidoBO.getInstance().salvarOuAtualizar(pedido);

            limpar();

            FacesUtil.addInfoMessage("Pedido realizado com sucesso!");
        } catch (Exception e) {
            FacesUtil.addErrorMessage("Erro ao realizar pedido! " + e.getMessage());
        }
    }

    public void carregarDadosPedido() {
        pedido.setDataCriacao(new Date());

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        pedido.setVendedor(UsuarioDAO.getInstance().getUserPorEmail(((User) authentication.getPrincipal()).getUsername()));
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
        FacesUtil.addInfoMessage("Item adicionado ao carrinho!");
    }

    public void remover(Item item) {
        int index = 0;
        for (Item t : itens) {
            if (t.getProduto().equals(item.getProduto())) {
                break;
            }
            index++;
        }
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

    public void atualizarValoresParciais(Item item) {
        BigDecimal valorAntigo = item.getValorUnitario();
        item.setValorUnitario(item.getProduto().getValorUnitario().multiply(new BigDecimal(item.getQuantidade())));

        if (item.getValorUnitario().compareTo(valorAntigo) == 1) {
            pedido.setValorTotal(pedido.getValorTotal().add(item.getValorUnitario().subtract(valorAntigo)));
        } else {
            pedido.setValorTotal(pedido.getValorTotal().subtract(valorAntigo.subtract(item.getValorUnitario())));
        }
    }

    public void pesquisar() {
        produtos = ProdutoBO.getInstance().getProdutosComEstoque();
    }

    public void limpar() {
        pedido = new Pedido();
        pesquisar();
        produtosAux = produtos;
        itens = new ArrayList<Item>();
    }

    //<editor-fold defaultstate="collapsed" desc="get and set">
    public Pedido getPedido() {
        if (pedido == null) {
            pedido = new Pedido();
        }
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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

    public List<Item> getItens() {
        if (itens == null) {
            itens = new ArrayList<>();
        }
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Produto> getProdutosAux() {
        return produtosAux;
    }

    public void setProdutosAux(List<Produto> produtosAux) {
        this.produtosAux = produtosAux;
    }
//</editor-fold>
}
