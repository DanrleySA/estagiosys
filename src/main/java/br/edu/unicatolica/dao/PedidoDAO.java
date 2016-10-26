package br.edu.unicatolica.dao;

import br.edu.unicatolica.entity.Pedido;
import br.edu.unicatolica.hibernate.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;

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

    public List<Pedido> getPedidos() {
        Session session = HibernateUtil.geSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(Pedido.class);
            return criteria.list();
        } finally {
            session.close();
        }
    }
}
