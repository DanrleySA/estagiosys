package br.edu.unicatolica.dao;

import br.edu.unicatolica.entity.EntidadeBase;
import br.edu.unicatolica.hibernate.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author Danrley
 */
public class GenericoDAO<T extends EntidadeBase> implements Serializable {

    public void salvarOuAtualizar(T t) {
        Session session = HibernateUtil.geSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.merge(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void remover(T t) {
        Session session = HibernateUtil.geSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.delete(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public T getEntidadePorId(Class<T> classe, Long id) {
        Session session = HibernateUtil.geSessionFactory().openSession();
        T t = null;
        try {
            return t = (T) session.get(classe, id);
        } finally {
            session.close();
        }
    }

    public List<T> getListaEntidade(Class<T> classe) {
        Session session = HibernateUtil.geSessionFactory().openSession();
        List<T> t;
        try {
            Criteria criteria = session.createCriteria(classe.getClass());
            t = criteria.list();
            return t;
        } finally {
            session.close();
        }
    }

}
