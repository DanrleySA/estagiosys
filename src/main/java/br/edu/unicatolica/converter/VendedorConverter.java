package br.edu.unicatolica.converter;

import br.edu.unicatolica.dao.UsuarioDAO;
import br.edu.unicatolica.entity.Usuario;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Danrley
 */
@FacesConverter(forClass = Usuario.class)
public class VendedorConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Usuario usuario = null;
        if (value != null) {
            Long id = new Long(value);
            return UsuarioDAO.getInstance().getEntidadePorId(Usuario.class, id);
        }
        return usuario;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value != null) {
            Usuario usuario = (Usuario) value;
            return usuario.getId() == null ? null : usuario.getId().toString();
        }
        return "";
    }

}
