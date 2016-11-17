/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unicatolica.controller;

import br.edu.unicatolica.jsf.util.FacesUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Danrley
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FacesContext facesContext;

    public LoginBean() {
        facesContext = FacesContext.getCurrentInstance();
        request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    private String email;

    public void acessar() throws IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/j_spring_security_check");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }

        facesContext.responseComplete();
    }

    public void preRender() {
        if ("true".equals(request.getParameter("invalid"))) {
            FacesUtil.addErrorMessage("Usuário ou senha inválido!");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String login) {
        this.email = login;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public FacesContext getFacesContext() {
        return facesContext;
    }

    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }

}
