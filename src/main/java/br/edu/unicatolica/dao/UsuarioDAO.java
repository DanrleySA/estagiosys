package br.edu.unicatolica.dao;

import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.hibernate.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Danrley
 */
public class UsuarioDAO extends GenericoDAO<Usuario> implements Serializable {

    private static UsuarioDAO instance;

    private UsuarioDAO() {

    }

    public static UsuarioDAO getInstance() {
        if (instance == null) {
            instance = new UsuarioDAO();
        }
        return instance;
    }

    public List<Usuario> getUsuarios() {
        Session session = HibernateUtil.geSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(Usuario.class);
            return criteria.list();
        } finally {
            session.close();
        }
    }

    public Usuario getUserPorEmail(String email) {
        Session session = HibernateUtil.geSessionFactory().openSession();
        Usuario usuario = null;

        try {
            Criteria criteria = session.createCriteria(Usuario.class);
            criteria.add(Restrictions.eq("email", email));
            usuario = (Usuario) criteria.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
        }
        return usuario;
    }
}
