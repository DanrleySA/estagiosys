/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unicatolica.controller;

import br.edu.unicatolica.bo.PedidoBO;
import br.edu.unicatolica.dao.UsuarioDAO;
import br.edu.unicatolica.entity.Pedido;
import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.enumeration.TipoPedido;
import br.edu.unicatolica.filter.PedidoFilter;
import br.edu.unicatolica.jsf.util.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Danrley
 */
@ManagedBean
@ViewScoped
public class ConsultaPedidoBean implements Serializable {

    private List<Pedido> pedidos;
    private Pedido pedidoSelecionado;
    private PedidoFilter pedidoFilter = new PedidoFilter();

    public void pesquisarPedidos() {
        pedidos = PedidoBO.getInstance().getPedidosComFiltro(pedidoFilter);
    }

    public void remover() {
        PedidoBO.getInstance().remover(pedidoSelecionado);
        FacesUtil.addInfoMessage("Pedido cancelado com sucesso!");
        pesquisarPedidos();
    }

    public List<Pedido> getPedidos() {
        if (pedidos == null) {
            pedidos = new ArrayList<>();
        }
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Usuario> getVendedores() {
        return UsuarioDAO.getInstance().getListaEntidade(Usuario.class);
    }

    public TipoPedido[] getTipos() {
        return TipoPedido.values();
    }

    public Pedido getPedidoSelecionado() {
        return pedidoSelecionado;
    }

    public void setPedidoSelecionado(Pedido pedidoSelecionado) {
        this.pedidoSelecionado = pedidoSelecionado;
    }

    public PedidoFilter getPedidoFilter() {
        return pedidoFilter;
    }

    public void setPedidoFilter(PedidoFilter pedidoFilter) {
        this.pedidoFilter = pedidoFilter;
    }

}
