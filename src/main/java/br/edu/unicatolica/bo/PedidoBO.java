package br.edu.unicatolica.bo;

import br.edu.unicatolica.dao.PedidoDAO;
import br.edu.unicatolica.entity.Pedido;
import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.enumeration.TipoPedido;
import java.io.Serializable;
import java.util.Date;
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

    public List<Pedido> getPedidos() {
        return PedidoDAO.getInstance().getListaEntidade(Pedido.class);
    }

    public List<Pedido> getPedidosComFiltro(Usuario vendedor, TipoPedido tipo, Date inicio, Date fim) {
        if (fim != null) {
            fim.setHours(23);
            fim.setMinutes(59);
            fim.setSeconds(59);
        }
        return PedidoDAO.getInstance().getPedidos(vendedor, tipo, inicio, fim);
    }

}
