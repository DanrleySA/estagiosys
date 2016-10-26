package br.edu.unicatolica.bo;

import br.edu.unicatolica.dao.PedidoDAO;
import br.edu.unicatolica.entity.Pedido;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Danrley
 */
public class PedidoBO implements Serializable {

    private static PedidoBO instance;

    private PedidoBO() {

    }

    public static PedidoBO getInstance() {
        if (instance == null) {
            instance = new PedidoBO();
        }
        return instance;
    }

    public void salvarOuAtualizar(Pedido pedido) {
        PedidoDAO.getInstance().salvarOuAtualizar(pedido);
    }

    public void remover(Pedido pedido) {
        PedidoDAO.getInstance().remover(pedido);
    }

    public List<Pedido> getProdutos() {
        return PedidoDAO.getInstance().getPedidos();
    }

}