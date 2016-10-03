package com.codingmentorteam3.controllers;

import com.codingmentorteam3.beans.UserBean;
import com.codingmentorteam3.interceptors.BeanValidation;
import com.codingmentorteam3.util.UtilSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author istvan.mosonyi
 */
@BeanValidation
@SessionScoped
@ManagedBean(name = "authController")
public class AuthController {

    private static final Logger LOG = Logger.getLogger(AuthController.class.getName());

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        LOG.info("AuthController.login()");
        HttpServletRequest request = UtilSession.getRequest();
        System.out.println(request.toString() + "asd");
        System.out.println(username + "asdasd" + password);
        try {
            request.login(username, password);
        } catch (ServletException e) {
            LOG.log(Level.SEVERE, "{0} failed to login", username);
//
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login failed."));
            return "login.xhtml";
        }
        UserBean userBean = new UserBean();
        userBean.setUsername(username);
        userBean.setPassword(password);
        UtilSession.getSession().setAttribute("user", userBean);
        return "home.xhtml";
    }

    public String logout() {
        LOG.info("AuthController.logout()");
        HttpServletRequest request = UtilSession.getRequest();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        username = null;
        password = null;
        return "logout";
    }

}
