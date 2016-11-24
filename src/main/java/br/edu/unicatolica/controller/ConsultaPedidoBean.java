/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unicatolica.controller;

import br.edu.unicatolica.bo.PedidoBO;
import br.edu.unicatolica.entity.Pedido;
import java.io.Serializable;
import java.util.ArrayList;
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
public class ConsultaPedidoBean implements Serializable {

    private List<Pedido> pedidos;

    @PostConstruct
    public void init() {
        pesquisarPedidos();
    }

    public void pesquisarPedidos() {
        pedidos = PedidoBO.getInstance().getPedidos();
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

}
