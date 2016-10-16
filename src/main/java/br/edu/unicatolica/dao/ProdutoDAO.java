package br.edu.unicatolica.dao;

import br.edu.unicatolica.entity.Produto;
import br.edu.unicatolica.filter.ProdutoFilter;
import br.edu.unicatolica.hibernate.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Danrley
 */
public class ProdutoDAO extends GenericoDAO<Produto> implements Serializable {

    private static ProdutoDAO instance;

    private ProdutoDAO() {

    }

    public static ProdutoDAO getInstance() {
        if (instance == null) {
            instance = new ProdutoDAO();
        }
        return instance;
    }

    public List<Produto> getProdutos(ProdutoFilter produtoFilter) {
        Session session = HibernateUtil.geSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(Produto.class);

            if (StringUtils.isNotBlank(produtoFilter.getDescricao())) {
                criteria.add(Restrictions.ilike("descricao", produtoFilter.getDescricao(), MatchMode.ANYWHERE.ANYWHERE));
            }
            return criteria.addOrder(Order.asc("descricao")).list();
        } finally {
            session.close();
        }
    }
}
