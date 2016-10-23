package br.edu.unicatolica.dao;

import br.edu.unicatolica.entity.Grupo;

/**
 *
 * @author Danrley
 */
public class GrupoDAO extends GenericoDAO<Grupo> {

    private static GrupoDAO instance;

    private GrupoDAO() {

    }

    public static GrupoDAO getInstance() {
        if (instance == null) {
            instance = new GrupoDAO();
        }
        return instance;
    }
}
