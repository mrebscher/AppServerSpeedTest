/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rebscher.appservertest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mrebscher
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name = SmallPayload.findAll, query = "SELECT s FROM SmallPayload s")
public class SmallPayload {
    @Id
    @GeneratedValue
    private long id;
    
    private static final String PREFIX = "org.rebscher.appservertest.entity.SmallPayload";
    public static final String findAll = PREFIX + "all";

    @Column(name="PAYLOAD")
    private String payload;
    
    @Column(name="PAYLOAD2")
    private String payload2;
    
    public SmallPayload(String payload) {
        this.payload = payload;
    }

    public SmallPayload(long id) {
        this.id = id;
        this.payload = "dummy Payload";
        this.payload = "dummy Payload 2";
    }

    public SmallPayload() {
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPayload2() {
        return payload2;
    }

    public void setPayload2(String payload2) {
        this.payload2 = payload2;
    }
}
