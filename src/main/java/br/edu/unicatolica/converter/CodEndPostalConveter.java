package br.edu.unicatolica.converter;

import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Danrley
 */
@FacesConverter(value = "cepConverter")
public class CodEndPostalConveter implements Converter, Serializable {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
        if (valor != null || valor != "") {
            valor = valor.toString().replaceAll("[- /.]", "");
            valor = valor.toString().replaceAll("[-()]", "");
        }
        return valor;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
        return valor.toString();
    }

}
