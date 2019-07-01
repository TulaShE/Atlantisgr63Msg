/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.model;

import com.gr63.atlantis.business.domain.User;
import com.gr63.atlantis.business.logic.UserServiceLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dev
 */
@Named(value = "userModel")
@SessionScoped
public class UserBean implements Serializable {

    private String firstname, lastname;
    private Long userId;

    private boolean isAdmin;
    private List<User> listUsers;
    
    @Inject
    private UserServiceLocal userService;
    private User user;
    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }
    
    //redirect to authentification page
    public String logIn() throws Exception{
        return "home";
    }
    
    //redirect to register page
    public String register() throws Exception{
        return "registration";
    }
    
    //redirect to home page after authentification
    public String authentication(){
        userService.authentication(firstname, lastname);
        return "home";
    }
    
    public String create(){
        System.out.println("User creation");
        
        userService.save(firstname, lastname, false);
        
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        
        return "index";
    }
    
    public String listUsers(){
        
        listUsers = userService.getAllUser();
        return "usersList";
    }
    
    public String detailUser(){
        return "userDetails";
    }
    
    
    public String getFirstname(){
        return firstname;
    }
    
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }
    
    public String getLastname(){
        return lastname;
    }
    
    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<User> listUsers) {
        this.listUsers = listUsers;
    }
    
}
