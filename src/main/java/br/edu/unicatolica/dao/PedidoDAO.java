package br.edu.unicatolica.dao;

import br.edu.unicatolica.entity.DataValor;
import br.edu.unicatolica.entity.Pedido;
import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.filter.PedidoFilter;
import br.edu.unicatolica.hibernate.util.HibernateUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

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

    public List<Pedido> getPedidos(PedidoFilter pedidoFilter) {
        Session session = HibernateUtil.geSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(Pedido.class);
            if (pedidoFilter.getVendedor() != null) {
                criteria.add(Restrictions.eq("vendedor", pedidoFilter.getVendedor()));
                criteria.addOrder(Order.desc("vendedor"));
            }

            if (pedidoFilter.getTipo() != null) {
                criteria.add(Restrictions.eq("tipo", pedidoFilter.getTipo()));
                criteria.addOrder(Order.desc("valorTotal"));
            }

            if (pedidoFilter.getDataInicial() != null && pedidoFilter.getDataFinal() != null) {
                criteria.add(Restrictions.between("dataCriacao", pedidoFilter.getDataInicial(), pedidoFilter.getDataFinal()));
                criteria.addOrder(Order.desc("dataCriacao"));
            }

            return criteria.list();
        } finally {
            session.close();
        }
    }

    public Map<Date, BigDecimal> consultaGrafico(Map<Date, BigDecimal> resultado, Usuario vendedor, Calendar dataInicial) {
        Session session = HibernateUtil.geSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(Pedido.class);

            criteria.setProjection(Projections.projectionList()
                    .add(Projections.sqlGroupProjection("date(dataCriacao) as data", "date(dataCriacao)",
                            new String[]{"data"},
                            new Type[]{StandardBasicTypes.DATE}))
                    .add(Projections.sum("valorTotal").as("valor"))
            )
                    .add(Restrictions.ge("dataCriacao", dataInicial.getTime()));

            if (vendedor != null) {
                criteria.add(Restrictions.eq("vendedor", vendedor));
            }

            List<DataValor> valoresPorData = criteria
                    .setResultTransformer(Transformers.aliasToBean(DataValor.class)).list();

            for (DataValor dataValor : valoresPorData) {
                resultado.put(dataValor.getData(), dataValor.getValor());
            }

            return resultado;
        } finally {
            session.close();
        }
    }
}
