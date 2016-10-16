package br.edu.unicatolica.dao;

import br.edu.unicatolica.entity.EntidadeBase;
import br.edu.unicatolica.entity.Produto;
import br.edu.unicatolica.hibernate.util.HibernateUtil;
import java.io.Serializable;
import org.hibernate.Session;

/**
 *
 * @author Danrley
 */
public class GenericoDAO<T extends EntidadeBase> implements Serializable {

    public void salvarOuAtualizar(T t) {
        Session s = HibernateUtil.geSessionFactory().openSession();
        try {
            s.getTransaction().begin();
            s.merge(t);
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
    }

    public void remover(T t) {
        Session s = HibernateUtil.geSessionFactory().openSession();
        try {
            s.getTransaction().begin();
            s.delete(t);
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
    }

    public T getCategoriaPorId(Class<T> classe, Long id) {
        Session s = HibernateUtil.geSessionFactory().openSession();
        T t = null;
        try {
            return t = (T) s.get(classe, id);
        } finally {
            s.close();
        }
    }

}
