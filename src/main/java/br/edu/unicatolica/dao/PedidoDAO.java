package br.edu.unicatolica.dao;

import br.edu.unicatolica.entity.Pedido;
import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.enumeration.TipoPedido;
import br.edu.unicatolica.hibernate.util.HibernateUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Danrley
 */
public class PedidoDAO extends GenericoDAO<Pedido> implements Serializable {

    private static PedidoDAO instance;

    private PedidoDAO() {

    }

    public static PedidoDAO getInstance() {
        if (instance == null) {
            instance = new PedidoDAO();
        }
        return instance;
    }

    public List<Pedido> getPedidos(Usuario vendedor, TipoPedido tipo, Date inicio, Date fim) {
        Session session = HibernateUtil.geSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(Pedido.class);
            if (vendedor != null) {
                criteria.add(Restrictions.eq("vendedor", vendedor));
                criteria.addOrder(Order.desc("vendedor"));
            }

            if (tipo != null) {
                criteria.add(Restrictions.eq("tipo", tipo));
                criteria.addOrder(Order.desc("valorTotal"));
            }

            if (inicio != null && fim != null) {
                criteria.add(Restrictions.between("dataCriacao", inicio, fim));
                criteria.addOrder(Order.desc("dataCriacao"));
            }

            return criteria.list();
        } finally {
            session.close();
        }
    }

}
