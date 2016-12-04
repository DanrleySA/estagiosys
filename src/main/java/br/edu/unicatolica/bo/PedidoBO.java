package br.edu.unicatolica.bo;

import br.edu.unicatolica.dao.PedidoDAO;
import br.edu.unicatolica.entity.Item;
import br.edu.unicatolica.entity.Pedido;
import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.enumeration.TipoPedido;
import br.edu.unicatolica.filter.PedidoFilter;
import br.edu.unicatolica.jsf.util.FacesUtil;
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
        for (Item t : pedido.getItens()) {
            t.getProduto().setQtdEstoque(t.getProduto().getQtdEstoque() + t.getQuantidade());
            ProdutoBO.getInstance().salvarOuAtualizar(t.getProduto());
        }
        PedidoDAO.getInstance().remover(pedido);
    }

    public List<Pedido> getPedidos() {
        return PedidoDAO.getInstance().getListaEntidade(Pedido.class);
    }

    public List<Pedido> getPedidosComFiltro(PedidoFilter pedidoFilter) {
        if (pedidoFilter.getDataFinal() != null && pedidoFilter.getDataInicial() != null) {
            if (pedidoFilter.getDataFinal().before(pedidoFilter.getDataInicial())) {
                FacesUtil.addErrorMessage("Data final (Até)' deve ser maior que a data inicial (De)!");
                return null;
            }
            if (pedidoFilter.getDataFinal() != null) {
                pedidoFilter.getDataFinal().setHours(23);
                pedidoFilter.getDataFinal().setMinutes(59);
                pedidoFilter.getDataFinal().setSeconds(59);
            }
        }

        return PedidoDAO.getInstance().getPedidos(pedidoFilter);
    }

}
