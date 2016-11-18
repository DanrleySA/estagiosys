/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unicatolica.enumeration;

/**
 *
 * @author Danrley
 */
public enum TipoPedido {
    DELIVERY("Delivery"), MESA("Mesa");

    private String descricao;

    TipoPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
