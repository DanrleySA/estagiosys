package br.edu.unicatolica.controller;

import br.edu.unicatolica.bo.PedidoBO;
import br.edu.unicatolica.bo.ProdutoBO;
import br.edu.unicatolica.dao.UsuarioDAO;
import br.edu.unicatolica.entity.EnderecoEntrega;
import br.edu.unicatolica.entity.Item;
import br.edu.unicatolica.entity.Pedido;
import br.edu.unicatolica.entity.Produto;
import br.edu.unicatolica.enumeration.FormaPagamento;
import br.edu.unicatolica.enumeration.TipoPedido;
import br.edu.unicatolica.enumeration.UnidadeFederacao;
import br.edu.unicatolica.filter.ProdutoFilter;
import br.edu.unicatolica.jsf.util.FacesUtil;
import br.edu.unicatolica.security.Seguranca;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FlowEvent;
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
public class CadastroPedidoBean implements Serializable {

    private ProdutoFilter produtoFilter;
    private List<Produto> produtos;
    private List<Item> itens;
    private List<Produto> produtosAux;
    private Pedido pedido;
    private boolean skip;

    @PostConstruct
    public void init() {
        pesquisarProdutos();
        produtosAux = produtos;
        pedido = new Pedido();
        itens = new ArrayList<>();
    }

    public void adicionarItem(Produto produto) {

        Item item = new Item();
        item.setProduto(produto);
        item.setQuantidade(1);
        item.setValorUnitario(produto.getValorUnitario());

        itens.add(item);
        produtosAux.remove(produto);
        produtos.remove(produto);

        pedido.setValorTotal((itens.size() == 1)
                ? produto.getValorUnitario()
                : pedido.getValorTotal().add(produto.getValorUnitario()));
        FacesUtil.addInfoMessage("Item adicionado ao carrinho!");
    }

    public void removerItem(Item item) {
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

    public void salvar() {
        try {
            for (Item t : itens) {
                t.setPedido(pedido);
                t.getProduto().setQtdEstoque(t.getProduto().getQtdEstoque() - t.getQuantidade());
                ProdutoBO.getInstance().salvarOuAtualizar(t.getProduto());
            }
            PedidoBO.getInstance().salvarOuAtualizar(pedido);

            limpar();

            FacesUtil.addInfoMessage("Pedido realizado com sucesso!");
        } catch (Exception e) {
            FacesUtil.addErrorMessage("Erro ao realizar pedido! " + e.getMessage());
        }
    }

    public void carregarDadosPedido() {
        pedido.setItens(itens);
        pedido.setEndereco(new EnderecoEntrega());
        pedido.setDataCriacao(new Date());
        pedido.setVendedor(new Seguranca().getUsuarioLogado().getUsuario());
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

        pedido.setValorTotal(item.getValorUnitario().compareTo(valorAntigo) == 1
                ? pedido.getValorTotal().add(item.getValorUnitario().subtract(valorAntigo))
                : pedido.getValorTotal().subtract(valorAntigo.subtract(item.getValorUnitario())));
    }

    public void atualizarValorTotal() {

    }

    public void pesquisarProdutos() {
        produtos = ProdutoBO.getInstance().getProdutosComEstoque();
    }

    public void limpar() {
        pedido = new Pedido();
        pesquisarProdutos();
        produtosAux = produtos;
        itens = new ArrayList<Item>();
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    public Pedido getPedido() {
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

    public TipoPedido[] getTipos() {
        return TipoPedido.values();
    }

    public UnidadeFederacao[] getUF() {
        return UnidadeFederacao.values();
    }

    public FormaPagamento[] getFormaPagamento() {
        return FormaPagamento.values();
    }

//</editor-fold>
    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }
}
