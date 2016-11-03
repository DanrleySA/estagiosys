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

    public Long salvarPedido(Pedido pedido) {
        Long codigo = null;
        Session s = HibernateUtil.geSessionFactory().openSession();
        try {
            s.getTransaction().begin();
            codigo = (Long) s.save(pedido);
            System.out.println(codigo);
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return codigo;
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
