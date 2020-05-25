/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.resfulserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Nightm4re
 */
@Entity
@Table(name = "server")
public class Server {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    
    @Column(name = "servername")
    private String servername;

    @NotNull
    @Column(name = "ip", unique = true)
    private String ip;   
        
    @Column(name = "lastupdate")
    private float lastupdate;  

    public Server() {
    }  

    public Long getId() {
        return id;
    }

    public String getServername() {
        return servername;
    }

    public String getIp() {
        return ip;
    }

    public float getLastupdate() {
        return lastupdate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setServername(String servername) {
        this.servername = servername;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setLastupdate(float lastupdate) {
        this.lastupdate = lastupdate;
    }

    @Override
    public String toString() {
        return "Server{" + "id=" + id + ", servername=" + servername + ", ip=" + ip + ", lastupdate=" + lastupdate + '}';
    }
    
    
}
