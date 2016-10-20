package br.edu.unicatolica.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author danrley
 */
@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

    public String logar(){
        return "principal.xhtml";
    }
}
