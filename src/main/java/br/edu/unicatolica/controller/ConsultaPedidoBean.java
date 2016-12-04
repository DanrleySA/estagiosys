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
import br.edu.unicatolica.jsf.util.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
    private Usuario vendedor;
    private Date dataInicial;
    private Date dataFinal;
    private TipoPedido tipoSelecionado;

    public void pesquisarPedidos() {
        pedidos = PedidoBO.getInstance().getPedidosComFiltro(vendedor, tipoSelecionado, dataInicial, dataFinal);
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

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public TipoPedido[] getTipos() {
        return TipoPedido.values();
    }

    public TipoPedido getTipoSelecionado() {
        return tipoSelecionado;
    }

    public void setTipoSelecionado(TipoPedido tipoSelecionado) {
        this.tipoSelecionado = tipoSelecionado;
    }

    public Pedido getPedidoSelecionado() {
        return pedidoSelecionado;
    }

    public void setPedidoSelecionado(Pedido pedidoSelecionado) {
        this.pedidoSelecionado = pedidoSelecionado;
    }

}
