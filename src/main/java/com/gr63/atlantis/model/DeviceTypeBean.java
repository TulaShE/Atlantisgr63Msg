/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.model;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author dev
 */
@Named(value = "deviceTypeModel")
@SessionScoped
public class DeviceTypeBean implements Serializable {

    /**
     * Creates a new instance of DeviceTypeBean
     */
    public DeviceTypeBean() {
    }
    
}
